import core.Engine2D.*;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends PApplet {

    Scene scene1;
    GameObject ref;

    public static void main(String args[]) { PApplet.main("Main"); }

    public void settings() { size(800, 800); }

    public void setup() {
        EngineMaster.getInstance(this);
        scene1 = new Scene("Scene01", 60);
        scene1.createGameObject("Banana");
        scene1.createGameObject("Poop");
        new Sprite(scene1.getObject(0),"src/core/Engine2D/Engine_Data/Banana_Sprite.jpg");
        new Sprite(scene1.getObject(1),null);
        scene1.getObjectByName("Poop").setLayerNum(2);
        scene1.createLayers();
    }

    public void draw() {
        background(100, 100, 0);
        fill(0, 0, 255);
        textSize(30);
        text(frameRate, 750, 40);
        scene1.getObjectByName("Poop").transform.setPosition(new PVector(mouseX, mouseY));
        scene1.Update();
    }

    public void mouseClicked(){
        scene1.getObjectByName("Poop").transform.setScale(new PVector(0.6f, 0.3f));
        System.out.println(scene1.getObjectByName("Poop").transform.scale());
    }

    public void keyPressed(){
        scene1.getObjectByName("Poop").transform.setScale(new PVector(0.3f, 0.3f));
        System.out.println(scene1.getObjectByName("Poop").transform.scale());
    }
}
