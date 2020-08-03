package core.Engine2D;

import processing.core.PVector;

public interface Collider {
    public void onCollision(Collider c);
    public GameObject parentObject();
    public float radius();
    default void startCollisions(){ EngineMaster.addCollider(this); }
    default void stopCollisions(){ EngineMaster.removeCollider(this); }
}
