package core.Engine2D;

import processing.core.PGraphics;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class Scene extends Thing {

    private int freq;
    ArrayList<PGraphics> layers;
    private ArrayList<GameObject> worldObjects;
    private ArrayList<GameObject> UIObjects;

    public Scene(String newName, int newFreq){
        if (newName == null){ name = "DefaultScene"; }
        else { name = newName; }
        if (newFreq == 0 || newFreq < 0){ freq = 60;}
        else { freq = newFreq; }
        layers = new ArrayList<>();
        worldObjects = new ArrayList<>();
        UIObjects = new ArrayList<>();
    }

    @Override
    public void Update(){
        //worldObjects.sort(Comparator.comparingInt(GameObject::getLayerNum));
        //UIObjects.sort(Comparator.comparingInt(GameObject::getLayerNum));
        for (GameObject g : worldObjects){ g.Update(); }
        for (GameObject g : UIObjects){ g.Update(); }
        for (PGraphics pg : layers){
            pg.clear();
            parent.image(pg, 0, 0);
        }
    }

    @Override
    public JSONObject serializeToJSON() {
        return null;
    }

    @Override
    public void loadJSONObject(JSONObject jsonObject) {

    }

    public void setFPS(int fps) { this.freq = fps; }

    public void createGameObject(String objectName){
        GameObject g = new GameObject(objectName);
        worldObjects.add(g);
    }

    public GameObject getObject(int listNum){
        return worldObjects.get(listNum);
    }

    public GameObject getObjectByName(String name){
        for (GameObject g : worldObjects){
            if (g.name.equals(name))
                return g;
        }
        return null;
    }

    public void createLayers(){
        worldObjects.sort(Comparator.comparingInt(GameObject::getLayerNum));
        for (int i = 0; i < worldObjects.get(worldObjects.size() - 1).getLayerNum(); i++){
            PGraphics pg;
            pg = parent.createGraphics(800, 800);
            layers.add(pg);
        }
        for (GameObject g : worldObjects){
            if (g.hasComponent("Sprite"))
                ((Sprite) g.getComponent("Sprite")).setLayer(layers.get(g.getLayerNum() - 1));
        }
    }
}
