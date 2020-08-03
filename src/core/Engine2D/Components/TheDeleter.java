package core.Engine2D.Components;

import core.Engine2D.*;
import processing.core.PVector;
import processing.data.JSONObject;

import java.awt.*;

public class TheDeleter extends CircleCollider implements InputModule {

    private boolean bing;
    private boolean bong;
    public int tst;

    public TheDeleter(GameObject g){ super(g); startInput(); bing = true; bong = true; tst = 1; }

    @Override
    protected void update() {
        if (!gameObject.isActive)
            stopInput();
        super.update();
    }

    @Override
    public void onCollision(Collider c) {
        if (c.parentObject().name.equals("Poop"))
            c.parentObject().setActive(false);
        else if (c.parentObject().name.equals("Bing")){
            if (bong){
                changeText(c);
                bong = false;
            }
        }
        else{
            if (bing){
                gameOver(c);
                bing = false;
            }
        }
    }

    private void changeText(Collider c){
        DisplayText h = (DisplayText) c.parentObject().getComponent("DisplayText");
        EngineMaster.currentScene.switchLayer(c.parentObject(), 2);
        Coroutine go = new Coroutine(){
            @Override
            public void execute() {
                h.setFontSize(60);
                h.setTextColor(new Color(36, 165, 32));
                h.setText("Coroutines work, heck yeah");
            }
        };
        go.waitForSeconds(3);
    }

    private void gameOver(Collider c){
        System.out.println("GameOver");
        Coroutine t = new Coroutine(){
            @Override
            public void execute() {
                System.out.println("meme");
                c.parentObject().transform.setScale(new PVector(2, 2));
            }
        };
        t.waitForSeconds(3);
    }

    Coroutine j = new Coroutine(){
        @Override
        public void execute() {
            System.out.println("qqqqq");
        }
    };

    @Override
    public GameObject parentObject() {
        return super.parentObject();
    }

    @Override
    public float radius() {
        return super.radius();
    }

    @Override
    public void mouseEvent(int mouseButton) {

    }

    @Override
    public void keyEvent(char key) {
        if (key == 't')
            gameObject.transform.setPosition(new PVector(parent.mouseX, parent.mouseY));
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
