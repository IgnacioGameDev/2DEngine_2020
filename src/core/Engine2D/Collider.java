package core.Engine2D;

import processing.core.PVector;

public interface Collider {
    public void onCollision(Collider c);
    public GameObject parentObject();
    public float radius();
    default void iniCol(){ EngineMaster.addCollider(this); }
}
