package com.example.punee.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**`
 * Created by puneet on 10/6/2017.
 */

public class MusicAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Info> arrayList;
    private MediaPlayer mediaPlayer;
    private int layout;
    private boolean playing = true;
    private ImageView currentSongStartImage = null;
    private ImageView currentSongStopImage = null;

    public MusicAdapter(Context context, ArrayList<Info> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    class ViewHolder{
        TextView txtName,txtSinger;
        ImageView ivplay,ivstop;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();

            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =layoutInflater.inflate(layout,null);
            viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
            viewHolder.txtSinger = (TextView) view.findViewById(R.id.txtSinger);
            viewHolder.ivplay = (ImageView) view.findViewById(R.id.ivPlay);
            viewHolder.ivstop = (ImageView) view.findViewById(R.id.ivStop);

            view.setTag(viewHolder);
        }
        else{
            viewHolder =(ViewHolder) view.getTag();
        }

        final Info info=arrayList.get(i);

        viewHolder.txtName.setText(info.getTitle());
        viewHolder.txtSinger.setText(info.getArtist());

        viewHolder.ivplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO change this
                if(playing){
                    mediaPlayer=MediaPlayer.create(context, Uri.parse(info.getPath()));
                    playing=false;
                }
                if(mediaPlayer.isPlaying()){
                    if(currentSongStartImage == viewHolder.ivplay){
                        mediaPlayer.pause();
                        viewHolder.ivplay.setImageResource(R.drawable.play);
                        playing = false;
                    }else{
                        mediaPlayer.pause();
                        currentSongStartImage.setImageResource(R.drawable.play);
                        viewHolder.ivplay.setImageResource(R.drawable.pause);
                        currentSongStartImage = viewHolder.ivplay;
                        currentSongStopImage = viewHolder.ivstop;
                        mediaPlayer=MediaPlayer.create(context, Uri.parse(info.getPath()));
                        mediaPlayer.start();
                        playing=false;
                    }

                }
                else {
                    mediaPlayer.start();
                    viewHolder.ivplay.setImageResource(R.drawable.pause);
                    currentSongStartImage = viewHolder.ivplay;
                    currentSongStopImage = viewHolder.ivstop;
                }
            }
        });
        viewHolder.ivstop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!playing){


                    if(currentSongStopImage == viewHolder.ivstop){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        playing=true;

                    }
                }
                viewHolder.ivplay.setImageResource(R.drawable.play);
            }
        });
        return view;
    }
}
