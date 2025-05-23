package fr.iut.saeterraria.sae.Vue;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.HashMap;

public class VueSon {

    private HashMap<Integer,MediaPlayer> listSon;

    public VueSon() {
        listSon = new HashMap<>();
        initializeSon();
    }

    public MediaPlayer Sonore(String path) {
        URL resource = getClass().getResource(path);
        Media media = new Media(resource.toString());
        return new MediaPlayer(media);
    }

    public void initializeSon() {
        listSon.put(1,Sonore("/Sound/Lumière.mp3"));
        listSon.put(2,Sonore("/Sound/damage1.wav"));
        listSon.put(3,Sonore("/Sound/damage2.wav"));
        listSon.put(4,Sonore("/Sound/burp.wav"));
    }

    public void play(int id) {
        this.listSon.get(id).play();
    }

    public void stop(int id) {
        this.listSon.get(id).stop();
    }
}
