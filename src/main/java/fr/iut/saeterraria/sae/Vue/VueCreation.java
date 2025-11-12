package fr.iut.saeterraria.sae.Vue;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.*;
import fr.iut.saeterraria.sae.Modele.Objets.Recette;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class VueCreation extends SpriteItem {
    private ScrollPane craftSansBlocConstruction,craftEtabli,craftForge, four;
    private VBox caseRecetteSansBloc,caseRecetteEtabli,caseRecetteForge, caseRecetteFour;
    private HashMap<Integer, Recette> recetteSansBloc,recetteEtabli,recetteForge, recetteFour;

    private BlocConstructionSansBloc blocConstructionSansBloc;
    private BlocCraft blocCraft;
    private BlocForge blocForge;
    private BlocFour blocFour;

    public VueCreation(ScrollPane craftSansBlocConstruction, ScrollPane craftEtabli, ScrollPane craftForge, VBox caseRecetteSansBloc, VBox caseRecetteEtabli, VBox caseRecetteForge,
                       HashMap<Integer,Recette> recetteSansBloc, HashMap<Integer, Recette> recetteEtabli, HashMap<Integer, Recette> recetteForge,
                       VBox caseRecetteFour, HashMap<Integer, Recette> recetteFour, BlocConstructionSansBloc blocConstructionSansBloc, BlocCraft blocCraft, BlocForge blocForge, BlocFour blocFour) {
        this.craftSansBlocConstruction = craftSansBlocConstruction;
        this.craftEtabli = craftEtabli;
        this.craftForge = craftForge;
        this.caseRecetteSansBloc = caseRecetteSansBloc;
        this.caseRecetteEtabli = caseRecetteEtabli;
        this.caseRecetteForge = caseRecetteForge;
        this.recetteSansBloc = recetteSansBloc;
        this.recetteEtabli = recetteEtabli;
        this.recetteForge = recetteForge;
        this.caseRecetteFour = caseRecetteFour;
        this.recetteFour = recetteFour;
        this.blocConstructionSansBloc = blocConstructionSansBloc;
        this.blocCraft = blocCraft;
        this.blocForge = blocForge;
        this.blocFour = blocFour;

        craftSansBlocConstruction.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        craftEtabli.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        craftForge.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        initialize();
    }

    public void afficherCraft(HashMap<Integer, Recette> recette, VBox caseRecette, BlocConstruction blocConstruction) {
        for (Integer codeObjet : recette.keySet()) {
            HBox blocRecette = new HBox();
            blocRecette.prefWidthProperty().bind(caseRecette.widthProperty());
            blocRecette.setStyle("-fx-border-color: black; -fx-border-width: 2px");
            blocRecette.setPadding(new Insets(40));

            VBox sectionItemConstruit = new VBox();
            VBox sectionItemsNecessaires = new VBox();
            sectionItemConstruit.getChildren().add(super.createImageView(super.getHmap().get(codeObjet), 45, 45));
            sectionItemConstruit.getChildren().add(super.createLabelNom(blocConstruction.creerItem(codeObjet).getName()));

            sectionItemConstruit.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));
            sectionItemsNecessaires.prefWidthProperty().bind(blocRecette.widthProperty().divide(2));

            sectionItemConstruit.setAlignment(Pos.CENTER);

            for (int j = 0; j < recette.get(codeObjet).getRecette().size(); j++) {
                VBox vbox = new VBox();
                HBox elementRecette = new HBox();

                vbox.setAlignment(Pos.CENTER);
                elementRecette.setAlignment(Pos.CENTER);
                elementRecette.getChildren().add(super.createImageView(super.getHmap().get(recette.get(codeObjet).getRecette().get(j).getIdItem()), 20, 20));
                elementRecette.getChildren().add(createLabelQuantite(recette.get(codeObjet).getRecette().get(j).getQuantite()));

                vbox.getChildren().add(elementRecette);
                vbox.getChildren().add(super.createLabelNom((recette.get(codeObjet).getRecette().get(j).getNom())));
                sectionItemsNecessaires.getChildren().add(vbox);
            }

            blocRecette.getChildren().add(sectionItemConstruit);
            blocRecette.getChildren().add(sectionItemsNecessaires);
            caseRecette.getChildren().add(blocRecette);
        }
    }

    public void initialize() {
        afficherCraft(recetteSansBloc, caseRecetteSansBloc,blocConstructionSansBloc);
        afficherCraft(recetteEtabli, caseRecetteEtabli,blocCraft);
        afficherCraft(recetteForge, caseRecetteForge,blocForge);
        afficherCraft(recetteFour, caseRecetteFour,blocFour);
    }

    public String getCodeObjetLigne(int numeroLigne, int typeConstruction) {
        HBox hBox;
        Label label = new Label(null);
        VBox vBox;
        switch (typeConstruction) {
            case 0 :
                 hBox = (HBox) caseRecetteSansBloc.getChildren().get(numeroLigne); // Récupère la hbox qui représente la ligne
                 vBox = (VBox) hBox.getChildren().get(0);
                 label = (Label) vBox.getChildren().get(1);
                break;
            case 1 :
                hBox = (HBox) caseRecetteEtabli.getChildren().get(numeroLigne);
                vBox = (VBox) hBox.getChildren().get(0);
                label = (Label) vBox.getChildren().get(1);
                break;
            case 2 :
                hBox = (HBox) caseRecetteForge.getChildren().get(numeroLigne);
                vBox = (VBox) hBox.getChildren().get(0);
                label = (Label) vBox.getChildren().get(1);
                break;
            case 3 :
                hBox = (HBox) caseRecetteFour.getChildren().get(numeroLigne);
                vBox = (VBox) hBox.getChildren().get(0);
                label = (Label) vBox.getChildren().get(1);
                break;
            default:
                break;
        }
        return label.getText();
    }
}
