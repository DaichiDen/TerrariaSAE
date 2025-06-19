package fr.iut.saeterraria.sae.Modele.Map;

public class Map {

    private int[][] map;

    public Map() {
        JSONMapLoader.MapData mapData = JSONMapLoader.loadMap("/resources/Map..tmj");

        int largeur = mapData.width;
        int hauteur = mapData.height;
        int[] tiles = mapData.layers.get(0);

        map = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                int index = y * largeur + x;
                map[y][x] = tiles[index];
            }
        }
    }
    public int[][] getMap(){
        return map;
    }

    public int getColonne(){
        return map[0].length;
    }
    public int getLigne(){
        return map.length;
    }

    public int getCase(int x, int y){ return map[x][y];}

    public int getCoordonnéesX(int x){
        return x*32;
    }
    public int getCoordonnéesY(int y){
        return y*32;
    }

    public int[] detruireBloc(int x,int y){
        int[] blocRecup = new int[2];
        blocRecup[0] = getCase(y,x);
        blocRecup[1] = 1;
        map[y][x]=0;
        switch (blocRecup[0]){
            case 1:
                blocRecup[0] = 2;
                break;
            case 4:
                blocRecup[0] = 5;
                blocRecup[1] = 4;
                break;
            case 9:
                blocRecup[0] = 10;
            default:
                break;
        }
        return blocRecup;
    }
    public void poserBloc(int x,int y,int val){
        map[y][x]=val;
    }
}
