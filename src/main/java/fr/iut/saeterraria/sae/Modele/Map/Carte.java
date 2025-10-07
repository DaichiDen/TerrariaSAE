package fr.iut.saeterraria.sae.Modele.Map;

public class Carte {

    private int[][] carte;

    public Carte() {
        JSONMapLoader.MapData mapData = JSONMapLoader.loadMap("/MapRéduite.tmj");

        int largeur = mapData.width;
        int hauteur = mapData.height;
        int[] tiles = mapData.layers.get(0);

        carte = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                int index = y * largeur + x;
                carte[y][x] = tiles[index];
            }
        }
        rechargeMap();
    }

    public void rechargeMap(){
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[0].length; j++) {
                switch (carte[i][j]){
                    case 1:
                        carte[i][j] = 3;
                        break;
                    case 2:
                        carte[i][j] = 7;
                        break;
                    case 3:
                        carte[i][j] = 0;
                        break;
                    case 4:
                        carte[i][j] = 10;
                        break;
                    case 5:
                        carte[i][j] = 1;
                        break;
                    case 6:
                        carte[i][j] = 19;
                        break;
                    case 7:
                        carte[i][j] = 6;
                        break;
                    case 8:
                        carte[i][j] = 4;
                        break;
                    case 9:
                        carte[i][j] = 9;
                        break;
                    case 10:
                        carte[i][j] = 2;
                        break;
                    case 11:
                        carte[i][j] = 5;
                        break;
                    case 12:
                        carte[i][j] = 13;
                        break;
                    case 13:
                        carte[i][j] = 12;
                        break;
                    case 14:
                        carte[i][j] = 17;
                        break;
                    case 15:
                        carte[i][j] = 8;
                        break;
                    case 16:
                        carte[i][j] = 20;
                        break;
                    case 17:
                        carte[i][j] = 14;
                        break;
                    case 18:
                        carte[i][j] = 15;
                        break;
                    case 19:
                        carte[i][j] = 16;
                        break;
                    case 20:
                        carte[i][j] = 18;
                        break;
                }
            }
        }
    }
    public int[][] getMap(){
        return carte;
    }

    public int recupColonneTaille(){
        return carte[0].length;
    }
    public int recupLigneTaille(){
        return carte.length;
    }

    public int getCase(int x, int y){ return carte[x][y];}
    public void setCase(int x, int y, int c){
        carte[x][y] = c;
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
            carte[y][x]=10;
        }
        else {
            carte[y][x]=0;
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
        carte[y][x]=val;
    }
}
