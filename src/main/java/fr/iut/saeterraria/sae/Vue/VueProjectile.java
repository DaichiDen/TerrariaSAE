    package fr.iut.saeterraria.sae.Vue;

    import fr.iut.saeterraria.sae.Modele.Jeu;
    import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
    import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.Pane;

    import javafx.beans.value.ChangeListener;
    import java.util.HashMap;
    import java.util.Map;

    public class VueProjectile extends CreateRessourceVisuel {
        private Pane screen;
        private Jeu jeu;
        private int width, height;
        private Map<Projectile, ImageView> sprites = new HashMap<>();


        private ImageView spriteActuel = new ImageView();

        public VueProjectile(Jeu jeu, Pane screen) {
            this.jeu = jeu;
            this.screen = screen;
            this.width = 10;
            this.height = 10;

            for (int i = 0; i < jeu.getListe_projectiles().size(); i++) {
                int finalI = i;
                jeu.getListe_projectiles().get(i).xProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteProjectile(jeu.getListe_projectiles().get(finalI)));
                jeu.getListe_projectiles().get(i).yProperty().addListener((obs, oldVal, newVal) -> mettreAJourSpriteProjectile(jeu.getListe_projectiles().get(finalI)));
            }
        }

        public void mettreAJourSpriteProjectile(Projectile projectile) {
            // Vérifier si un sprite existe déjà pour ce projectile
            ImageView sprite = sprites.get(projectile);

            if (sprite == null) {
                // Si pas existant, créer et configurer le sprite une fois
                if (projectile.getType().equals("fleche")) {
                    sprite = createImageView("/Sprite_objets/Flèche.png", width, height);
                } else if (projectile.getType().equals("balle")) {
                    sprite = createImageView("/Sprite_objets/Balle.png", width, height);
                }

                sprite.setId(projectile.getNom());
                sprite.translateXProperty().bind(projectile.xProperty());
                sprite.translateYProperty().bind(projectile.yProperty());

                screen.getChildren().add(sprite);
                sprites.put(projectile, sprite);
            }
            // Sinon, le sprite existe déjà, il est déjà lié aux propriétés : pas besoin de recréer ni d'ajouter
        }

    }
