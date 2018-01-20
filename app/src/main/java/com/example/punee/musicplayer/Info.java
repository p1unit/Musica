package com.example.punee.musicplayer;

/**
 * Created by puneet on 10/6/2017.
 */

public class Info {
    String title;
    String artist;
    String album;
    String path;
    long duration;
    long albumId;
    String composer;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.length()<=17) {
            this.title = title;
        }
        else{
            this.title=title.substring(0,16)+"...";
        }
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if(artist.length()<=15) {
            this.artist = artist;
        }
        else{
            this.artist=artist.substring(0,14)+"...";
        }
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}