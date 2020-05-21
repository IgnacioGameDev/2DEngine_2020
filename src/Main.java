import core.Engine2D.*;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends PApplet {

    Scene scene1;
    GameObject ref;
    GameObject b;
    GameObject p;

    public static void main(String args[]) { PApplet.main("Main"); }

    public void settings() { size(1200, 1000); }

    public void setup() {
        EngineMaster.getInstance(this);
        scene1 = new Scene("Scene01", 144);
        b = scene1.createGameObject("Banana", 1);
        p = scene1.createGameObject("Poop", 1);
        new Sprite(b,"src/core/Engine2D/Engine_Data/Banana_Sprite.jpg");
        new Sprite(p,null);
    }

    public void draw() {
        background(100, 100, 0);
        fill(0, 0, 255);
        textSize(30);
        text(frameRate, 750, 40);
        p.transform.setPosition(new PVector(mouseX, mouseY));
        scene1.Update();
    }

    public void mouseClicked(){
        p.transform.setScale(new PVector(0.3f, 0.3f));
        b.transform.setPosition(new PVector(500, 500));
        System.out.println(p.transform.scale());
    }

    public void keyPressed(){
        scene1.switchLayer(b, 3);
    }
}
