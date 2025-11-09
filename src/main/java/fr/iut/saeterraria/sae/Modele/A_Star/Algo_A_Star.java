package fr.iut.saeterraria.sae.Modele.A_Star;

import fr.iut.saeterraria.sae.Modele.Map.Carte;

import java.util.*;

public class Algo_A_Star {

    private Carte carte;

    public Algo_A_Star(Carte carte) {
        this.carte = carte;
    }

    public List<Node> trouverchemin(int x1,int y1,int x2,int y2){
        Node debut = new Node(x1,y1);

        debut.atteignable = isWalkable(x1, y1);

        Node but = new Node(x2,y2);

        but.atteignable = true;

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        HashSet<Node> closedList = new HashSet<>();

        openList.add(debut);

        while (!openList.isEmpty()) {
            Node actuel = openList.poll();

            if (actuel.x == but.x && actuel.y == but.y) {
                return reconstructPath(actuel);
            }

            closedList.add(actuel);

            for (Node voisin : getVoisin(actuel)) {
                if (!voisin.walkable || closedList.contains(voisin)) continue;

                int tentativeG = actuel.gCost + 1;

                boolean inOpenList = openList.contains(voisin);
                if (!inOpenList || tentativeG < voisin.gCost) {
                    voisin.gCost = tentativeG;
                    voisin.hCost = heuristique(voisin, but);
                    voisin.parent = actuel;

                    if (!inOpenList) {
                        openList.add(voisin);
                    }
                }
            }
        }

        return new ArrayList<>(); // Aucun chemin trouvé
    }

    private List<Node> getVoisin(Node node) {
        List<Node> voisins = new ArrayList<>();
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        for (int[] dir : dirs) {
            int newX = node.x + dir[0];
            int newY = node.y + dir[1];

            if (dansLaMap(newX, newY)) {
                Node voisin = new Node(newX,newY);
                voisin.x = newX;
                voisin.y = newY;
                voisin.walkable = isWalkable(newX, newY);
                voisins.add(voisin);
            }
        }

        return voisins;
    }
    private boolean dansLaMap(int x, int y) { // à refaire, quand on fera des collisions au bord de la map
        return x >= 0 && x < carte.recupColonneTaille() && y >= 0 && y < carte.recupLigneTaille();
    }
    public boolean isWalkable(int x, int y) {
        // On s'assure que les coordonnées sont dans les limites de la carte
        if (y < 0 || y >= carte.recupLigneTaille() || x < 0 || x >= carte.recupColonneTaille()) {
            return false;
        }

        int val = carte.getCase(y, x); // attention, map[y][x] est l'ordre ligne-colonne
        return val == 0; // Seul le ciel est considéré comme marchable
    }
    private int heuristique(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan
    }
    private List<Node> reconstructPath(Node endNode) {
        List<Node> chemin = new ArrayList<>();
        Node actuel = endNode;
        while (actuel != null) {
            chemin.add(actuel);
            actuel = actuel.parent;
        }
        Collections.reverse(chemin);
        return chemin;
    }






}




