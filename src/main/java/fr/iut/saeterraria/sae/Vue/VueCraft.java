package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Objets.Recette;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class VueCraft extends SpriteItem {
    private ScrollPane craftSansBlocConstruction,craftEtabli,craftForge;
    private VBox caseRecetteSansBloc,caseRecetteEtabli,caseRecetteForge;
    private HashMap<Integer, Recette> recetteSansBloc,recetteEtabli,recetteForge;

    public VueCraft(ScrollPane craftSansBlocConstruction,ScrollPane craftEtabli,ScrollPane craftForge,VBox caseRecetteSansBloc,VBox caseRecetteEtabli,VBox caseRecetteForge, HashMap<Integer,Recette> recetteSansBloc,HashMap<Integer, Recette> recetteEtabli,HashMap<Integer, Recette> recetteForge) {
        this.craftSansBlocConstruction = craftSansBlocConstruction;
        this.craftEtabli = craftEtabli;
        this.craftForge = craftForge;
        this.caseRecetteSansBloc = caseRecetteSansBloc;
        this.caseRecetteEtabli = caseRecetteEtabli;
        this.caseRecetteForge = caseRecetteForge;
        this.recetteSansBloc = recetteSansBloc;
        this.recetteEtabli = recetteEtabli;
        this.recetteForge = recetteForge;
    }

    public void afficherCraftEtabli() {
        for (Integer codeObjet : recetteEtabli.keySet()) {
            HBox blocRecette = new HBox();
            VBox sectionItemConstruit = new VBox();
            VBox sectionItemsNecessaires = new VBox();
            sectionItemConstruit.getChildren().add(super.createImageView(super.getHmap().get(codeObjet), 30, 30));

            for (int j = 0; j < recetteEtabli.get(codeObjet).getRecette().size(); j++) {
                VBox elementRecette = new VBox();
                elementRecette.getChildren().add(super.createImageView(super.getHmap().get(recetteEtabli.get(codeObjet).getRecette().get(j).getItem().getCodeObjet()), 15, 15));
                elementRecette.getChildren().add(createLabelQuantite(recetteEtabli.get(codeObjet).getRecette().get(j).getQuantite()));
                sectionItemsNecessaires.getChildren().add(elementRecette);
            }

            blocRecette.getChildren().add(sectionItemConstruit);
            blocRecette.getChildren().add(sectionItemsNecessaires);
            caseRecetteEtabli.getChildren().add(blocRecette);
        }
    }

    public void afficherCraftForge() {
        for (Integer codeObjet : recetteForge.keySet()) {
            HBox blocRecette = new HBox();
            VBox sectionItemConstruit = new VBox();
            VBox sectionItemsNecessaires = new VBox();
            sectionItemConstruit.getChildren().add(super.createImageView(super.getHmap().get(codeObjet), 30, 30));

            for (int j = 0; j < recetteForge.get(codeObjet).getRecette().size(); j++) {
                VBox elementRecette = new VBox();
                elementRecette.getChildren().add(super.createImageView(super.getHmap().get(recetteForge.get(codeObjet).getRecette().get(j).getItem().getCodeObjet()), 15, 15));
                elementRecette.getChildren().add(createLabelQuantite(recetteForge.get(codeObjet).getRecette().get(j).getQuantite()));
                sectionItemsNecessaires.getChildren().add(elementRecette);
            }

            blocRecette.getChildren().add(sectionItemConstruit);
            blocRecette.getChildren().add(sectionItemsNecessaires);
            caseRecetteForge.getChildren().add(blocRecette);
        }
    }

    public void afficherCraftSansBlocConstruction() {
        for (Integer codeObjet : recetteSansBloc.keySet()) {
            HBox blocRecette = new HBox();
            VBox sectionItemConstruit = new VBox();
            VBox sectionItemsNecessaires = new VBox();
            sectionItemConstruit.getChildren().add(super.createImageView(super.getHmap().get(codeObjet), 30, 30));

            for (int j = 0; j < recetteSansBloc.get(codeObjet).getRecette().size(); j++) {
                VBox elementRecette = new VBox();
                elementRecette.getChildren().add(super.createImageView(super.getHmap().get(recetteSansBloc.get(codeObjet).getRecette().get(j).getItem().getCodeObjet()), 15, 15));
                elementRecette.getChildren().add(createLabelQuantite(recetteSansBloc.get(codeObjet).getRecette().get(j).getQuantite()));
                sectionItemsNecessaires.getChildren().add(elementRecette);
            }

            blocRecette.getChildren().add(sectionItemConstruit);
            blocRecette.getChildren().add(sectionItemsNecessaires);
            caseRecetteSansBloc.getChildren().add(blocRecette);
        }
    }

    public void initialize() {
        afficherCraftSansBlocConstruction();
        afficherCraftEtabli();
        afficherCraftForge();
    }
}
