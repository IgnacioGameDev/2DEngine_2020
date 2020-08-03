package core.Engine2D.Components;

import core.Engine2D.*;
import processing.core.PVector;
import processing.data.JSONObject;

public class UIButton extends Component implements InputModule {

    Coroutine buttonPress;

    //Instantiate by serializing 1 gameobject with 1 reference
    public UIButton(GameObject g){
        super(g);
        startInput();
    }

    @Override
    protected void update() {
        if (!gameObject.isActive)
            stopInput();
    }

    @Override
    public void mouseEvent(int mouseButton) {
        if (gameObject.transform.inBounds(new PVector(parent.mouseX, parent.mouseY))){
            buttonPress.waitForSeconds(0.3f);
            System.out.println("pressed");
        }

    }

    @Override
    public void keyEvent(char key) {

    }

    public void setBoundsToSprite(){
        if (gameObject.hasComponent("Sprite")){
            Sprite s = (Sprite) gameObject.getComponent("Sprite");
            gameObject.transform.setBounds(s.getPixelSize());
        }
        System.out.println(gameObject.transform.bounds());
    }

    @Override
    public JSONObject serializeToJSON() {
        return null;
    }

    @Override
    public void loadFromJSON(JSONObject jO) {

    }

    public void setButtonPress(Coroutine buttonPress) {
        this.buttonPress = buttonPress;
    }
}
