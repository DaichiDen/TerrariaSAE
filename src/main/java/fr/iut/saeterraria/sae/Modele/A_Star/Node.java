package fr.iut.saeterraria.sae.Modele.A_Star;

public class Node {
    public boolean walkable;
    int x,y;
    Node parent;
    int gCost,hCost;
    boolean atteignable;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.atteignable = false;
    }
    public int getCost(){
        return gCost+hCost;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) return false;
        Node other = (Node) o;
        return this.x == other.x && this.y == other.y;
    }






}
