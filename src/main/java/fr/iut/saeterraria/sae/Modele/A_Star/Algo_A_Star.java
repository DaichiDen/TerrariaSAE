package fr.iut.saeterraria.sae.Modele.A_Star;

import fr.iut.saeterraria.sae.Modele.Map.Map;

import java.util.*;

public class Algo_A_Star {

    private Map map;

    public Algo_A_Star(Map map) {
        this.map = map;
    }

    public List<Node> trouverchemin(int x1,int y1,int x2,int y2){
        Node start = new Node(x1,y1);

        start.atteignable = isWalkable(x1, y1);

        Node goal = new Node(x2,y2);

        goal.atteignable = true;

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        HashSet<Node> closedList = new HashSet<>();

        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current.x == goal.x && current.y == goal.y) {
                return reconstructPath(current);
            }

            closedList.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (!neighbor.walkable || closedList.contains(neighbor)) continue;

                int tentativeG = current.gCost + 1;

                boolean inOpenList = openList.contains(neighbor);
                if (!inOpenList || tentativeG < neighbor.gCost) {
                    neighbor.gCost = tentativeG;
                    neighbor.hCost = heuristic(neighbor, goal);
                    neighbor.parent = current;

                    if (!inOpenList) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(); // Aucun chemin trouvé
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        for (int[] dir : dirs) {
            int newX = node.x + dir[0];
            int newY = node.y + dir[1];

            if (inBounds(newX, newY)) {
                Node neighbor = new Node(newX,newY);
                neighbor.x = newX;
                neighbor.y = newY;
                neighbor.walkable = isWalkable(newX, newY);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }
    private boolean inBounds(int x, int y) { // à refaire, quand on fera des collisions au bord de la map
        return x >= 0 && x < map.getColonne() && y >= 0 && y < map.getLigne();
    }
    public boolean isWalkable(int x, int y) {
        // On s'assure que les coordonnées sont dans les limites de la carte
        if (y < 0 || y >= map.getLigne() || x < 0 || x >= map.getColonne()) {
            return false;
        }

        int val = map.getCase(y, x); // attention, map[y][x] est l'ordre ligne-colonne
        return val == 0; // Seul le ciel est considéré comme marchable
    }
    private int heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan
    }
    private List<Node> reconstructPath(Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }






}




