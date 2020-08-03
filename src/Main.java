import core.Engine2D.*;
import core.Engine2D.Components.*;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.Scanner;

public class Main extends PApplet {

    Scene scene1;
    Scene scene2;
    GameObject ref;
    GameObject b;
    GameObject p;
    GameObject h;
    GameObject k;
    GameObject bing;
    DisplayText ting;
    Scanner sc;
    UIButton u;
    Coroutine c;
    Inspector i;

    public static void main(String args[]) { PApplet.main("Main"); }

    public void settings() { size(1600, 1000); }

    public void setup() {
        sc = new Scanner(System.in);
        EngineMaster.getInstance(this);
        scene1 = new Scene("Scene01", 144);
        scene2 = new Scene("Scene02", 60);
        EngineMaster.loadScene(scene1);
        b = scene1.createGameObject("Banana", 0);
        p = scene1.createGameObject("Poop", 1);
        bing = scene1.createGameObject("Bing", 1);
        bing.transform.setPosition(new PVector(700, 100));
        new CircleCollider(bing);
        ting = new DisplayText(bing);
        b.transform.setPosition(new PVector(900, 700));
        Sprite s1 = new Sprite(b);
        s1.setImg(EngineMaster.deFilePath + "Banana_Sprite.jpg");
        new Sprite(p);
        new CircleCollider(b);
        new CircleCollider(p);
        p.transform.setScale(new PVector(0.3f, 0.3f));
        p.transform.setPosition(new PVector(100, 100));
        h = scene1.createGameObject("Chomper", 2);
        h.transform.setPosition(new PVector(200, 800));
        Sprite s2 = new Sprite(h);
        s2.setImg(EngineMaster.deFilePath + "Banana_Sprite.jpg");
        new TheDeleter(h);
//        u = new UIButton(h);
//        u.setBoundsToSprite();
        c = new Coroutine(){
            @Override
            public void execute() {
                s2.setImg(EngineMaster.deFilePath + sc.nextLine());
            }
        };
        //new CustomCompExample(b);
        //b.transform.setVelocity(new PVector(1, 0));

        k = scene2.createGameObject("newPoop", 1);
        Sprite s3 = new Sprite(k);
        s3.setImg(EngineMaster.deFilePath + "Banana_Sprite.jpg");
        k.transform.setPosition(new PVector(800, 300));
        i = new Inspector(this, null);

    }

    public void draw() {
        EngineMaster.Update();
        fill(0, 0, 255);
        textSize(30);
        text(frameRate, 900, 40);
        i.Display();
        //p.transform.setPosition(new PVector(mouseX, mouseY));



    }

    public void mousePressed(){
        EngineMaster.pressMouse(mouseButton);
        if (key == 'i')
            i.getNearestObj(new PVector(mouseX, mouseY), EngineMaster.currentScene.getWorldObjects());
        if (key == 'r') {
            try {
                i.fetchInspectorField(mouseY);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void mouseReleased(){ EngineMaster.unpressMouse(mouseButton); }

    public void keyPressed(){
        EngineMaster.pressKey(key);
    }

    public void keyReleased(){
        EngineMaster.unpressKey(key);
        if (key == 'l'){
            EngineMaster.loadScene(scene1);
        }
        if (key == 'j'){
            u.setButtonPress(c);
        }
        if (key == 'v')
            EngineMaster.saveScene();
        if (key == 'n')
            EngineMaster.loadSceneFromFile(sc.nextLine());
        if (key == 'o')
            i.switchSides();
    }
}
