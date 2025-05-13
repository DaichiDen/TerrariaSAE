package fr.iut.saeterraria.sae.Modele.Map;

public class Map {
    private int[][] map;
    private int width;
    private int height;
    //private HashMap<Integer, Tiles> tiles;


    public Map(int width, int height){
        this.width = width;
        this.height = height;
        map = new int[width][height];
        //tiles = new HashMap<>();

    }

    public void remplirMap(int[][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                map[i][j] = (int) (Math.random()*3);
            }
        }
    }

    public int[][] getMap(){
        return map;
    }
}
