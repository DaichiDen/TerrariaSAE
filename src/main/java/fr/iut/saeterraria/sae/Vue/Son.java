package fr.iut.saeterraria.sae.Vue;

import javafx.scene.media.MediaPlayer;

public class Son extends CreateSound{
    MediaPlayer fichier;
    public Son(String path) {
        fichier = super.creerSon(path);
    }

    public void play() {
        super.jouerSon(this.fichier);
    }
}
