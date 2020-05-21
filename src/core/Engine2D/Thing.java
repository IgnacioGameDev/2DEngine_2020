package core.Engine2D;

import core.Data_Management.Serializable;
import processing.core.PApplet;

//Name Object was already taken in the Java namespace
public abstract class Thing implements Serializable {

    public PApplet parent;
    public String name;

    public Thing(){ parent = EngineMaster.parent; }

    public abstract void Update();

}
