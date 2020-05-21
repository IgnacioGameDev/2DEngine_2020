package core.Engine2D;

import processing.core.PGraphics;
import processing.data.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

public class GameObject extends Thing {

    public Transform transform;
    private PGraphics layer;
    private ArrayList<Component> componentList;
    protected boolean isActive;

    public GameObject(String newName, PGraphics layer){
        componentList = new ArrayList<>();
        if (newName == null){ name = "GameObject" + EngineMaster.numObjects; }
        else { name = newName; }
        transform = new Transform();
        isActive = true;
        this.layer = layer;
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

    @Override public void Update() {
        for (Component c : componentList){ c.update(); }
    }
    @Override public JSONObject serializeToJSON() { return null; }
    @Override public void loadJSONObject(JSONObject jsonObject) { }
    public void setActive(boolean active) { isActive = active; }

    public PGraphics getLayer() { return layer; }
    public void setLayer(PGraphics layer) { this.layer = layer; passLayer(this.layer); }
    public void passLayer(PGraphics l){
        if (this.hasComponent("Sprite"))
            ((Sprite) this.getComponent("Sprite")).setLayer(l);
    }
}
