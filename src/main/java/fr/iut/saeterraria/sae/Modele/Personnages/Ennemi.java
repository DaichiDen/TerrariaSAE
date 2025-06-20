package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.A_Star.Algo_A_Star;
import fr.iut.saeterraria.sae.Modele.A_Star.Node;
import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Objets.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Ennemi extends EntiteVivante {
    private long dernièreAttaque=60;
    private long cooldown=60;
    private int rangeVue;
    private int rangeAttaque;

    private ArrayList<Item> listDrops;

    public Ennemi(String nom, int vieMax,int energieMax, int x, int y, int def,Jeu jeu, int attaque, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {

        super(nom,vieMax,  energieMax, 20, x, y, def, 5,jeu,attaque, tailleL, tailleH);
        listDrops = new ArrayList<>();
        this.rangeVue = rangeVue;
        this.rangeAttaque = rangeAttaque;
    }

    public int getRangeVue() {
        return rangeVue;
    }
    public int getRangeAttaque() {
        return rangeAttaque;
    }

    public ArrayList<Item> getListDrops() {
        return this.listDrops;
    }
    public int dropItem(){
        return (int) (Math.random()*11);
    }


    public abstract void action(int x, int y, int range);


    @Override
    public void mettreAJour(){
        if(!detecterJoueur()){
            comportementPasVu();
            super.mettreAJour();
        }else{
            comportementVu();
            super.mettreAJour();

        }

    }
    public void comportementVu(){
        Algo_A_Star pathfinding = new Algo_A_Star(super.getJeu().getCarte());
        List<Node> path = pathfinding.trouverchemin(this.getX()/32, this.getY()/32, super.getJeu().getJoueur().getX()/32, super.getJeu().getJoueur().getY()/32);


        if (!path.isEmpty() && path.size()>1) {
            Node nextStep = path.get(1); // [0] = position actuelle
            int dx = nextStep.x - (this.getX() / 32);
            int dy = nextStep.y - (this.getY() / 32);

            if (dx < 0) {
                setMarcheGauche(true);
                setMarcheDroite(false);
            } else if (dx > 0) {
                setMarcheGauche(false);
                setMarcheDroite(true);
            }
            if(dy < 0) {
                this.sauter();
            }

            if(peutEtreAtteint(super.getJeu().getJoueur().getX()/32, super.getJeu().getJoueur().getY()/32, getRangeVue())){
                action(super.getJeu().getJoueur().getX(), super.getJeu().getJoueur().getY(), 2);
            }

            // Tu peux gérer dy si les ennemis sautent ou volent
        }
        else{
            setMarcheGauche(false);
            setMarcheDroite(false);
            action(super.getJeu().getJoueur().getX(),super.getJeu().getJoueur().getY(), getRangeAttaque());

        }
    }

    public void comportementPasVu(){
        int aleaComp = (int) (Math.random()*11);
        if(aleaComp < 3){
            setMarcheDroite(true);
            setMarcheGauche(false);
        } else if (aleaComp < 6) {
            setMarcheDroite(false);
            setMarcheGauche(true);
        } else if (aleaComp < 9) {
            setMarcheDroite(false);
            setMarcheGauche(false);

        }

    }

    public boolean detecterJoueur() {// À définir la distance où il détecte le joueur
        boolean aVuJoueur = false;
        if (peutEtreAtteint(super.getJeu().getJoueur().getX()/32, super.getJeu().getJoueur().getY()/32, 5)) {
            aVuJoueur = true;
        }
        return aVuJoueur;
    }
    public long getDernièreAttaque(){
        return dernièreAttaque;
    }
    public long getCooldown(){
        return cooldown;
    }
    public void setDernièreAttaque(long val){
        this.dernièreAttaque=val;

    }
    public void setCooldown(long val){
        this.cooldown=val;
    }
}
