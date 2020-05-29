package core.Engine2D;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashSet;

public class EngineMaster {

    public static EngineMaster engineMaster;

    public static PApplet parent;
    public static int numObjects;
    public static Scene currentScene;

    private static ArrayList<InputModule> inputObjects;
    public static HashSet<Character> keysDown;

    public EngineMaster(PApplet p){ parent = p; inputObjects = new ArrayList<>(); keysDown = new HashSet<>(); }

    public static EngineMaster getInstance(PApplet p)
    {
        if (engineMaster == null){
            engineMaster = new EngineMaster(p);
        }
        return engineMaster;
    }

    public static void setCurrentScene(Scene currentScene) {
        EngineMaster.currentScene = currentScene;
    }

    public static void addInput(InputModule im){
        inputObjects.add(im);
    }

    public static void pressKey(char key){
        keysDown.add(key);
    }

    public static void unpressKey(char key){
        keysDown.remove(key);
    }

    public static void executeInputs(){
        for (InputModule im : inputObjects){
            for(char c : keysDown){
                im.keyEvent(c);
            }
        }

    }
}
