package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Recette;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableRow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class VueCraft extends SpriteItem {
    private ScrollPane craftSansBlocConstruction,craftEtabli,craftForge;
    private VBox caseRecetteSansBloc,caseRecetteEtabli,caseRecetteForge;
    private HashMap<Integer, Recette> recetteSansBloc,recetteEtabli,recetteForge;
    private HashMap<Integer, Item> items;

    public VueCraft(ScrollPane craftSansBlocConstruction,ScrollPane craftEtabli,ScrollPane craftForge,VBox caseRecetteSansBloc,VBox caseRecetteEtabli,VBox caseRecetteForge, HashMap<Integer,Recette> recetteSansBloc,HashMap<Integer, Recette> recetteEtabli,HashMap<Integer, Recette> recetteForge, HashMap<Integer,Item> items) {
        this.craftSansBlocConstruction = craftSansBlocConstruction;
        this.craftEtabli = craftEtabli;
        this.craftForge = craftForge;
        this.caseRecetteSansBloc = caseRecetteSansBloc;
        this.caseRecetteEtabli = caseRecetteEtabli;
        this.caseRecetteForge = caseRecetteForge;
        this.recetteSansBloc = recetteSansBloc;
        this.recetteEtabli = recetteEtabli;
        this.recetteForge = recetteForge;
        this.items = items;

        craftSansBlocConstruction.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        craftEtabli.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        craftForge.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        initialize();
    }

    public void afficherCraftEtabli() {
        for (Integer codeObjet : recetteEtabli.keySet()) {
            HBox blocRecette = new HBox();
            blocRecette.prefWidthProperty().bind(caseRecetteEtabli.widthProperty());
            blocRecette.setStyle("-fx-border-color: black; -fx-border-width: 2px");
            blocRecette.setPadding(new Insets(50));
            VBox sectionItemConstruit = new VBox();
            VBox sectionItemsNecessaires = new VBox();

            sectionItemConstruit.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));
            sectionItemsNecessaires.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));

            sectionItemConstruit.setAlignment(Pos.CENTER);

            System.out.println("keyset craft etabli" + codeObjet);
            System.out.println(super.getHmap().get(codeObjet));

            sectionItemConstruit.getChildren().add(super.createImageView(super.getHmap().get(codeObjet), 70, 70));

            for (int j = 0; j < recetteEtabli.get(codeObjet).getRecette().size(); j++) {
                HBox elementRecette = new HBox();
                elementRecette.setAlignment(Pos.CENTER);
                elementRecette.getChildren().add(super.createImageView(super.getHmap().get(recetteEtabli.get(codeObjet).getRecette().get(j).getItem().getCodeObjet()), 30, 30));
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
            blocRecette.prefWidthProperty().bind(caseRecetteForge.widthProperty());
            blocRecette.setStyle("-fx-border-color: black; -fx-border-width: 2px");
            blocRecette.setPadding(new Insets(25));

            VBox sectionItemConstruit = new VBox();
            VBox sectionItemsNecessaires = new VBox();

            sectionItemConstruit.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));
            sectionItemsNecessaires.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));

            sectionItemConstruit.setAlignment(Pos.CENTER);

            System.out.println("keyset craft forge" + codeObjet);
            System.out.println(super.getHmap().get(codeObjet));

            sectionItemConstruit.getChildren().add(super.createImageView(super.getHmap().get(codeObjet), 45, 45));
            sectionItemConstruit.getChildren().add(super.createLabelNom(items.get(codeObjet).getName()));

            for (int j = 0; j < recetteForge.get(codeObjet).getRecette().size(); j++) {
                HBox elementRecette = new HBox();
                elementRecette.setAlignment(Pos.CENTER);
                elementRecette.getChildren().add(super.createImageView(super.getHmap().get(recetteForge.get(codeObjet).getRecette().get(j).getItem().getCodeObjet()), 25, 25));
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
            blocRecette.prefWidthProperty().bind(caseRecetteSansBloc.widthProperty());
            blocRecette.setStyle("-fx-border-color: black; -fx-border-width: 2px");
            blocRecette.setPadding(new Insets(40));

            VBox sectionItemConstruit = new VBox();
            VBox sectionItemsNecessaires = new VBox();

            sectionItemConstruit.getChildren().add(super.createImageView(super.getHmap().get(codeObjet), 45, 45));

            sectionItemConstruit.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));
            sectionItemsNecessaires.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));

            sectionItemConstruit.setAlignment(Pos.CENTER);

            for (int j = 0; j < recetteSansBloc.get(codeObjet).getRecette().size(); j++) {
                HBox elementRecette = new HBox();
                elementRecette.setAlignment(Pos.CENTER);
                elementRecette.getChildren().add(super.createImageView(super.getHmap().get(recetteSansBloc.get(codeObjet).getRecette().get(j).getItem().getCodeObjet()), 15, 15));
                elementRecette.getChildren().add(createLabelQuantite(recetteSansBloc.get(codeObjet).getRecette().get(j).getQuantite()));
                sectionItemsNecessaires.getChildren().add(elementRecette);
            }

            blocRecette.getChildren().add(sectionItemConstruit);
            blocRecette.getChildren().add(sectionItemsNecessaires);
            caseRecetteSansBloc.getChildren().add(blocRecette);
            System.out.println(caseRecetteSansBloc.getChildren());
        }
    }

    public void initialize() {
        afficherCraftSansBlocConstruction();
        afficherCraftEtabli();
        afficherCraftForge();
    }
}
