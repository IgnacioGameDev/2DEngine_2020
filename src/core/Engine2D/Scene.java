package core.Engine2D;

import processing.core.PGraphics;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class Scene extends Thing {

    private int freq;
    public PGraphics uniqueLayer;
    private ArrayList<GameObject> worldObjects;
    private ArrayList<GameObject> UIObjects;

    public Scene(String newName, int newFreq){
        if (newName == null){ name = "DefaultScene"; }
        else { name = newName; }
        if (newFreq == 0 || newFreq < 0){ freq = 60;}
        else { freq = newFreq; }
        parent.frameRate(freq);
        worldObjects = new ArrayList<>();
        UIObjects = new ArrayList<>();
//        for (int i = 0; i < 3; i++){
//            PGraphics pg;
//            pg = parent.createGraphics(parent.width, parent.height);
//            pg.beginDraw();
//            pg.endDraw();
//            layers.add(pg);
//        }
        uniqueLayer = parent.createGraphics(parent.width, parent.height);
        uniqueLayer.beginDraw();
        uniqueLayer.endDraw();
    }

    @Override
    public void Update(){
        //worldObjects.sort(Comparator.comparingInt(GameObject::getLayerNum));
        //UIObjects.sort(Comparator.comparingInt(GameObject::getLayerNum));
        for (GameObject g : worldObjects){ g.Update(); }
        for (GameObject g : UIObjects){ g.Update(); }
        uniqueLayer.clear();
        parent.image(uniqueLayer, 0, 0);
    }

    @Override
    public JSONObject serializeToJSON() {
        return null;
    }

    @Override
    public void loadJSONObject(JSONObject jsonObject) {

    }

    public void setFPS(int fps) { this.freq = fps; }

    public GameObject createGameObject(String objectName, int layerNum){
        GameObject g = new GameObject(objectName, layerNum);
        worldObjects.add(g);
        worldObjects.sort(Comparator.comparingInt(GameObject::getLayerNum));
        return g;
    }

    public void switchLayer(GameObject g, int layerNum){
        g.setLayerNum(layerNum);
        worldObjects.sort(Comparator.comparingInt(GameObject::getLayerNum));
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

    public void load(){ EngineMaster.setCurrentScene(this); }

}
