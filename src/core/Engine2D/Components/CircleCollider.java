package core.Engine2D.Components;

import core.Engine2D.Collider;
import core.Engine2D.Component;
import core.Engine2D.GameObject;
import processing.core.PVector;

public class CircleCollider extends Component implements Collider {

    private PVector center;
    private float radius;
    private float colSize;

    public CircleCollider(GameObject g){
        super(g);
        iniCol();
        center = new PVector(g.transform.position().x, g.transform.position().y);
        if (g.hasComponent("Sprite"))
            radius = (((Sprite)g.getComponent("Sprite")).getPixelSize().x/2);
        else
            radius = 1000;
        colSize = gameObject.transform.scale().x;
        radius *= colSize;
    }

    @Override
    protected void update() {
        if (colSize != gameObject.transform.scale().x){
            radius = radius*gameObject.transform.scale().x/colSize;
            colSize = gameObject.transform.scale().x;
        }
        parent.fill(0, 0, 255);
        parent.circle(gameObject.transform.position().x, gameObject.transform.position().y, radius*2);
    }

    @Override
    public void onCollision(Collider c){

    }

    @Override
    public GameObject parentObject() {
        return gameObject;
    }

    @Override
    public float radius() {
        return radius;
    }
}
