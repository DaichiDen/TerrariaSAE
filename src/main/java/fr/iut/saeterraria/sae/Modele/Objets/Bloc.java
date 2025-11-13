package fr.iut.saeterraria.sae.Modele.Objets;

public class Bloc extends Item{
    private int resistance; // Selon le type, la résistance évolue avec le integer

    public Bloc (String nom, int resistance, int codeobjet) {
        super(nom,1,codeobjet);
        this.resistance = resistance;
    }

    public int getResistance() { return resistance; }

    @Override
    public int getType() {
        return super.getType();
    }
}
