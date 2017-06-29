package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.objects.BrickProperties;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kacper on 29.06.2017.
 */
public class FileHandler {
    void saveBricksPositions(int level, List<Vector2> positions){
        String fileName = "level" + Integer.toString(level) + ".json";
        String jsonString = convertBrickPositionsToJson(positions);
        saveToFile(jsonString, fileName);
    }

    void saveBricksProperties(int level, List<BrickProperties> bricksProperties){
        String fileName = "level" + Integer.toString(level) + "bricksProperties.json";
        String jsonString = convertBrickPropertiesToJson(bricksProperties);
        saveToFile(jsonString, fileName);
    }

    private String convertBrickPropertiesToJson(List<BrickProperties> brickProperties){
        Json jsonParser = new Json();
        return jsonParser.toJson(brickProperties);
    }

    public List<Vector2> loadBricksPositions(int level) throws IOException{
        Json jsonParser = new Json();
        try{
            String jsonString = readJsonFromFile("level" + Integer.toString(level) + ".json");

            List<Vector2> positions = new LinkedList<Vector2>();
            positions.clear();
            positions.addAll(jsonParser.fromJson(positions.getClass(), jsonString));
            return positions;
        }catch(IOException e){
            throw e;
        }
    }

    public List<BrickProperties> loadBricksProperties(int level) throws IOException{
        Json jsonParser = new Json();
        try{
            String jsonString = readJsonFromFile("level" + Integer.toString(level) + "bricksProperties.json");

            List<BrickProperties> properties = new LinkedList<>();
            properties.addAll(jsonParser.fromJson(properties.getClass(), jsonString));
            return properties;
        }catch(IOException e){
            throw e;
        }

    }

    private String convertBrickPositionsToJson(List<Vector2> positions){
        Json jsonParser = new Json();
        return jsonParser.toJson(positions);
    }

    private void saveToFile(String jsonString, String filename){
        FileHandle handle = Gdx.files.local(filename);
        handle.writeString(jsonString, false);
    }

    private String readJsonFromFile(String fileName) throws IOException{
        try{
            FileHandle handle = Gdx.files.internal( fileName);
            return handle.readString();
        }catch(Exception e){
            System.out.println("dsd");
            throw new IOException("File does not exist.");
        }
    }
}
