package core.Engine2D;

import processing.core.PVector;

public class Transform {

    private PVector position;
    private float rotation;
    private PVector scale;

    public Transform(){
        position = new PVector(0, 0);
        rotation = 0;
        scale = new PVector(1, 1);
    }

    public PVector position() { return position; }
    public float rotation() { return rotation; }
    public PVector scale() { return scale; }
    public void setPosition(PVector position) { this.position = position; }
    public void setRotation(float rotation) { this.rotation = rotation; }
    public void setScale(PVector scale) { this.scale = scale; }
}
