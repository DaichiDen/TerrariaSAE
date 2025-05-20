package fr.iut.saeterraria.sae.Vue;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class CreateSound {
    public MediaPlayer creerSon(String path) {
        URL resource = getClass().getResource(path);
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }
    public void jouerSon(MediaPlayer mediaPlayer){
        mediaPlayer.play();
    }
}
