package fr.iut.saeterraria.sae.Modele.Map;

public class Map {

    private int[][] map;

    public Map() {
        JSONMapLoader.MapData mapData = JSONMapLoader.loadMap("/MapRéduite.tmj");

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
        reloadMap();
    }

    public void reloadMap(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]){
                    case 1:
                        map[i][j] = 3;
                        break;
                    case 2:
                        map[i][j] = 7;
                        break;
                    case 3:
                        map[i][j] = 0;
                        break;
                    case 4:
                        map[i][j] = 10;
                        break;
                    case 5:
                        map[i][j] = 1;
                        break;
                    case 6:
                        map[i][j] = 19;
                        break;
                    case 7:
                        map[i][j] = 6;
                        break;
                    case 8:
                        map[i][j] = 4;
                        break;
                    case 9:
                        map[i][j] = 9;
                        break;
                    case 10:
                        map[i][j] = 2;
                        break;
                    case 11:
                        map[i][j] = 5;
                        break;
                    case 12:
                        map[i][j] = 13;
                        break;
                    case 13:
                        map[i][j] = 12;
                        break;
                    case 14:
                        map[i][j] = 17;
                        break;
                    case 15:
                        map[i][j] = 8;
                        break;
                    case 16:
                        map[i][j] = 20;
                        break;
                    case 17:
                        map[i][j] = 14;
                        break;
                    case 18:
                        map[i][j] = 15;
                        break;
                    case 19:
                        map[i][j] = 16;
                        break;
                    case 20:
                        map[i][j] = 18;
                        break;
                }
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
    public void setCase(int x, int y, int c){
        map[x][y] = c;
    }

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
        if (y>20){
            map[y][x]=10;
        }
        else {
            map[y][x]=0;
        }

        switch (blocRecup[0]){
            case 1:
                blocRecup[0] = 2;
                break;
            case 4:
                blocRecup[0] = 21;
                blocRecup[1] = 4;
                break;
            case 20:
                blocRecup[0] = 72;
                break;
            default:
                break;

        }
        return blocRecup;
    }
    public void poserBloc(int x,int y,int val){
        map[y][x]=val;
    }
}
