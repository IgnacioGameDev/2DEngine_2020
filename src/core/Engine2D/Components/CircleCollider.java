package core.Engine2D.Components;

import core.Engine2D.Collider;
import core.Engine2D.Component;
import core.Engine2D.GameObject;
import processing.core.PVector;
import processing.data.JSONObject;

public class CircleCollider extends Component implements Collider {

    private PVector center;
    private float radius;
    private float colSize;

    public CircleCollider(GameObject g){
        super(g);
        startCollisions();
        center = new PVector(g.transform.position().x, g.transform.position().y);
        if (g.hasComponent("Sprite"))
            radius = (((Sprite)g.getComponent("Sprite")).getPixelSize().x/2);
        else
            radius = 100;
        colSize = gameObject.transform.scale().x;
        radius *= colSize;
    }

    @Override
    protected void update() {
        if (!gameObject.isActive)
            stopCollisions();
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

    @Override
    public JSONObject serializeToJSON() {
        JSONObject j = new JSONObject();
        j.setString("type", getClass().getSimpleName());
        j.setFloat("radius", radius);
        j.setFloat("colSize", colSize);
        return j;
    }

    @Override
    public void loadFromJSON(JSONObject jO) {
        radius = jO.getFloat("radius");
        colSize = jO.getFloat("colSize");
    }
}
