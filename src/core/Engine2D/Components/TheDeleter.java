package core.Engine2D.Components;

import core.Engine2D.*;
import processing.core.PVector;

import java.util.Scanner;

public class TheDeleter extends CircleCollider implements InputModule {

    private boolean bing;

    public TheDeleter(GameObject g){ super(g); initialize(); bing = true; }

    @Override
    protected void update() {
        super.update();
    }

    @Override
    public void onCollision(Collider c) {
        if (c.parentObject().name.equals("Poop"))
            c.parentObject().setActive(false);
        else{
            if (bing){
                gameOver(c);
                bing = false;
            }
        }
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
        t.waitForSeconds(1);
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
}
