package core.Engine2D.Components;

import core.Engine2D.Component;
import core.Engine2D.EngineMaster;
import core.Engine2D.GameObject;
import core.Engine2D.InputModule;
import processing.core.PVector;
import processing.data.JSONObject;

public class CustomCompExample extends Component implements InputModule {

    public CustomCompExample(GameObject g){
        super(g);
        startInput();
    }

    @Override
    protected void update() {
        if (!gameObject.isActive)
            stopInput();
    }

    @Override
    public void mouseEvent(int button) {
        if (button == EngineMaster.leftMouse){
            gameObject.transform.setScale(new PVector(gameObject.transform.scale().x + 0.05f, gameObject.transform.scale().y + 0.05f));
        }
        else if (button == EngineMaster.rightMouse){
            gameObject.transform.setScale(new PVector(gameObject.transform.scale().x - 0.05f, gameObject.transform.scale().y - 0.05f));
        }
        else{
            gameObject.transform.setScale(new PVector(1, 1));
        }
    }

    @Override
    public void keyEvent(char key) {
        if (key == 'w') {
            gameObject.transform.setPosition(new PVector(parent.mouseX, parent.mouseY));

        }
    }

    @Override
    public JSONObject serializeToJSON() {
        JSONObject j = new JSONObject();
        j.setString("type", getClass().getSimpleName());
        return j;
    }

    @Override
    public void loadFromJSON(JSONObject jO) {

    }
}
