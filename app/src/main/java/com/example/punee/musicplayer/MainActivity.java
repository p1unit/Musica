package com.example.punee.musicplayer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Info> listOfSongs=new ArrayList<Info>();;
    private MusicAdapter adapter;
    private ListView songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songList = (ListView)findViewById(R.id.songList);

        checkPermission();
    }
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                return;
            }
            loadSongs();

        }
        else{
            loadSongs();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    loadSongs();
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void loadSongs(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Context context=null;

        Cursor c = getApplicationContext().getContentResolver().query(uri, null, MediaStore.Audio.Media.IS_MUSIC + " != 0", null, null);
      //  ArrayList<Info>  = new ArrayList<Info>();
        c.moveToFirst();
        while(!c.isAfterLast()){
            Info songData = new Info();

            String title = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            long duration = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String data = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
            long albumId = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            String composer = c.getString(c.getColumnIndex(MediaStore.Audio.Media.COMPOSER));

            songData.setTitle(title);
            songData.setAlbum(album);
            songData.setArtist(artist);
            songData.setDuration(duration);
            songData.setPath(data);
            songData.setAlbumId(albumId);
            songData.setComposer(composer);
            listOfSongs.add(songData);

            c.moveToNext();
        }
        c.close();
        Log.d("SIZE", "SIZE: " + listOfSongs.size());
        //return listOfSongs;
        adapter = new MusicAdapter(this,listOfSongs,R.layout.custom_music_item);
        songList.setAdapter(adapter);
    }
}
