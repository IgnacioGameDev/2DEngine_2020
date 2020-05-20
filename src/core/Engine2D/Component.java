package core.Engine2D;

import processing.core.PApplet;

public abstract class Component {

    public PApplet parent;
    public GameObject gameObject;

    public Component(GameObject g){ gameObject = g; g.addComponent(this); parent = EngineMaster.parent; }

    protected abstract void update();

}
