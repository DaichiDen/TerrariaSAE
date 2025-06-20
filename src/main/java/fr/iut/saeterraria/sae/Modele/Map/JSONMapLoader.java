package fr.iut.saeterraria.sae.Modele.Map;

import java.io.*;
import java.util.*;

public class JSONMapLoader {

    public static MapData loadMap(String filePath) {
        try {
            StringBuilder jsonBuilder = new StringBuilder();
            InputStream is = JSONMapLoader.class.getResourceAsStream("/MapRéduite.tmj");
            if (is == null) {
                System.out.println("Fichier /Map..tmj non trouvé dans resources.");
                return new MapData(0, 0, 0, 0, new ArrayList<>());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }
            br.close();
            String json = jsonBuilder.toString();

            int width = extractInt(json, "\"width\"");
            int height = extractInt(json, "\"height\"");
            int tileWidth = extractInt(json, "\"tilewidth\"");
            int tileHeight = extractInt(json, "\"tileheight\"");

            List<int[]> layers = new ArrayList<>();

            String[] layerChunks = json.split("\\{");
            for (String chunk : layerChunks) {
                if (chunk.contains("\"type\":\"tilelayer\"") && chunk.contains("\"data\":[")) {
                    String dataStr = chunk.substring(chunk.indexOf("\"data\":[") + 8);
                    dataStr = dataStr.substring(0, dataStr.indexOf("]"));
                    String[] tileValues = dataStr.split(",");
                    int[] tiles = new int[tileValues.length];
                    for (int i = 0; i < tileValues.length; i++) {
                        tiles[i] = Integer.parseInt(tileValues[i].trim());
                    }
                    layers.add(tiles);
                }
            }

            return new MapData(width, height, tileWidth, tileHeight, layers);

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du fichier JSON : " + e.getMessage());
            return new MapData(0, 0, 0, 0, new ArrayList<>());
        }
    }

    private static int extractInt(String json, String key) {
        int index = json.indexOf(key);
        if (index == -1) return -1;
        int start = json.indexOf(":", index) + 1;
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);
        return Integer.parseInt(json.substring(start, end).trim());
    }

    public static class MapData {
        public final int width, height, tileWidth, tileHeight;
        public final List<int[]> layers;

        public MapData(int width, int height, int tileWidth, int tileHeight, List<int[]> layers) {
            this.width = width;
            this.height = height;
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;
            this.layers = layers;
        }
    }
}