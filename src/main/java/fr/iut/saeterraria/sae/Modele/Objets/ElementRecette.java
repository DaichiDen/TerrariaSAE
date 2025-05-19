package fr.iut.saeterraria.sae.Modele.Objets;

public class ElementRecette {
    private Item item;
    private int quantite;

    public ElementRecette(Item item, int quantite) {
        this.item = item;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public Item getItem() {
        return item;
    }
}
