package core.Engine2D;

import processing.core.PApplet;

public class EngineMaster {

    public static EngineMaster engineMaster;
    public static PApplet parent;
    public static int numObjects;
    public static Scene currentScene;

    public EngineMaster(PApplet p){ parent = p; }

    public static EngineMaster getInstance(PApplet p)
    {
        if (engineMaster == null){
            engineMaster = new EngineMaster(p);
        }
        return engineMaster;
    }
}
