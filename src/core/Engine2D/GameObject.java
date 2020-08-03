package core.Engine2D;

import core.Engine2D.Components.Sprite;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GameObject extends Thing {

    public Transform transform;
    private int layerNum;
    private ArrayList<Component> componentList;

    //Deactivating an object will only work if there is at least one object being updated
    public boolean isActive;

    public GameObject(String newName, int layerNum){
        this.layerNum = layerNum;
        componentList = new ArrayList<>();
        if (newName == null){ name = "GameObject" + EngineMaster.numObjects; }
        else { name = newName; }
        transform = new Transform();
        isActive = true;
    }

    public void addComponent(Component c){ componentList.add(c); c.gameObject = this; }

    public boolean hasComponent(String name){
        for (Component c: componentList) {
            if (name.equals(c.getClass().getSimpleName())){ return true; }
        }
        return false;
    }

    public Component getComponent(String name){
        for (Component c: componentList) {
            if (name.equals(c.getClass().getSimpleName())){ return c; }
        }
        return null;
    }

    public ArrayList<Component> getAllComponenets() {
        return componentList;
    }

    @Override public void Update() {
        transform.Update();
        for (Component c : componentList){ c.update(); }
    }

    public void setActive(boolean active) {
        if (!active){
            for (Component c : componentList){ c.update(); }
        }
        isActive = active;
    }

    public int getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(int layerNum) {
        this.layerNum = layerNum;
    }

    public void passLayer(PGraphics l){
        if (this.hasComponent("Sprite"))
            ((Sprite) this.getComponent("Sprite")).setLayer(l);
    }



    @Override public JSONObject serializeToJSON() {
        JSONObject objData = new JSONObject();
        objData.setString("name", name);
        objData.setFloat("xPos", transform.position().x);
        objData.setFloat("yPos", transform.position().y);
        objData.setFloat("rotation", transform.rotation());
        objData.setFloat("xScl", transform.scale().x);
        objData.setFloat("yScl", transform.scale().y);
        objData.setInt("layerNum", layerNum);
        JSONArray objComps = new JSONArray();
        for (int i = 0; i < componentList.size(); i++)
            objComps.setJSONObject(i, componentList.get(i).serializeToJSON());
        objData.setJSONArray("compList", objComps);
        System.out.println("componenets saved  " + objComps.size());
        return objData;
    }

    @Override public void loadFromJSON(JSONObject jO) {
        name = jO.getString("name");
        layerNum = jO.getInt("layerNum");
        JSONArray comps = jO.getJSONArray("compList");
        for (int i = 0; i < comps.size(); i++){
            JSONObject j = comps.getJSONObject(i);
            Class c = null;
            Component comp = null;
            try {
                c = Class.forName("core.Engine2D.Components." + j.getString("type"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(-1);
            }
            try{
                comp = (Component) c.getConstructor(GameObject.class).newInstance(this);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            comp.loadFromJSON(comps.getJSONObject(i));
            //creating a component already adds it onto the list
            //Doesn't seem functional, look at it down the line
            //componentList.add(comp);
        }
        transform.setPosition(new PVector(jO.getFloat("xPos"), jO.getFloat("yPos")));
        transform.setRotation(jO.getFloat("rotation"));
        transform.setScale(new PVector(jO.getFloat("xScl"), jO.getFloat("yScl")));
    }

}
