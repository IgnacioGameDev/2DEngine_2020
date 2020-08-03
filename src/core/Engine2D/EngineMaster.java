package core.Engine2D;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

//Singleton class, handles inputs, collisions, coroutines, scene switching and foundational engine data
public class EngineMaster {

    public static EngineMaster engineMaster;

    //Engine colors
    public static Color defBckClr = new Color(100, 100, 0);
    public static Color defTxtClr = new Color(0x1C2C7A);
    public static Color defObjClr = new Color(15);

    public static String deFilePath = "src/core/Engine2D/Engine_Data/";

    public static PApplet parent;
    public static int numObjects;

    public static Scene currentScene;
    public static int colRate, cicle;

    public static boolean timer = false;
    public static float time;

    //ArrayList allows duplicate objects and HashSet doesn't
    private static ArrayList<InputModule> inputObjects;
    public static HashSet<Character> keysDown;
    public static HashSet<Integer> mouseDown;

    public static int leftMouse = 37;
    public static int rightMouse = 39;
    public static int middleMouse = 3;
    public static int extraMouse = 0;

    private static ArrayList<Collider> colliderObjects;

    private static ArrayList<Coroutine> coroutines;

    public EngineMaster(PApplet p){
        parent = p;
        colRate = 0;
        cicle = 0;
        inputObjects = new ArrayList<>();
        keysDown = new HashSet<>();
        mouseDown = new HashSet<>();
        colliderObjects = new ArrayList<>();
        coroutines = new ArrayList<>();
    }

    public static EngineMaster getInstance(PApplet p)
    {
        if (engineMaster == null){
            engineMaster = new EngineMaster(p);
        }
        return engineMaster;
    }

    public static boolean waitForSeconds(float s){
        if (!timer){
            time = parent.millis();
            timer = true;
        }
        if (parent.millis()-time > (s*1000f)){
            timer = false;
            return true;
        }
        else
            return false;
    }

    public static void loadScene(Scene currentScene) {
        EngineMaster.currentScene = currentScene; currentScene.refresh();
    }

    public static void layerChange(){ EngineMaster.currentScene.sortLayers(); }

    public static void Update(){
        executeInputs();
        if (cicle == 0)
        executeCollisions();
        cicle++;
        if (cicle >= colRate)
            cicle = 0;
        executeRoutines();
        currentScene.Update();
    }

    public static void executeRoutines(){
        ArrayList<Coroutine> toRemove = new ArrayList<>();
        for (Coroutine c : coroutines){
            if (!c.isActive){
                c.time = parent.millis();
                c.isActive = true;
            }
            if (parent.millis()-c.time > (c.timeDelay *1000f)){
                c.execute();
                toRemove.add(c);
            }
        }
        coroutines.removeAll(toRemove);
    }

    public static void addRoutine(Coroutine r){ coroutines.add(r); }

    //Inputs are added into an array that is passed onto components that implement the imput module interface
    public static void addInput(InputModule im){
        inputObjects.add(im);
    }

    public static void removeInput(InputModule im){ inputObjects.remove(im); }

    public static void pressKey(char key){
        keysDown.add(key);
    }

    public static void unpressKey(char key){
        keysDown.remove(key);
    }

    public static void pressMouse(int button){ mouseDown.add(button); }

    public static void unpressMouse(int button){ mouseDown.remove(button); }

    public static void executeInputs(){
        for (InputModule im : inputObjects){
            for(char c : keysDown){
                im.keyEvent(c);
            }
            for(int i : mouseDown){
                im.mouseEvent(i);
            }
        }
    }

    private static boolean hasCollided(Collider a, Collider b){
        //System.out.println(a.parentObject().transform.position().dist(b.parentObject().transform.position()) + "   " + (a.radius()+b.radius()));
        return a.parentObject().transform.position().dist(b.parentObject().transform.position()) < (a.radius()+b.radius());
    }

    public static void addCollider(Collider c) { colliderObjects.add(c); }

    public static void removeCollider(Collider c){ colliderObjects.remove(c); }

    public static void executeCollisions(){
        for (int i = 0; i < colliderObjects.size(); i++){
            for (int j = 0; j < colliderObjects.size(); j++){
                if (i != j && colliderObjects.get(i).parentObject().isActive && colliderObjects.get(j).parentObject().isActive)
                    if (hasCollided(colliderObjects.get(i), colliderObjects.get(j))){
                        colliderObjects.get(i).onCollision(colliderObjects.get(j));
                        colliderObjects.get(j).onCollision(colliderObjects.get(i));
                    }
            }
        }
    }

    public static void saveScene(){
        parent.saveJSONObject(currentScene.serializeToJSON(), deFilePath + currentScene.name + ".json");
    }

    public static void loadSceneFromFile(String fileName){
        Scene s = new Scene("default", 60);
        JSONObject j = parent.loadJSONObject(deFilePath + fileName);
        s.loadFromJSON(j);
        loadScene(s);
    }

}
