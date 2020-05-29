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
        scene1.load();
        b = scene1.createGameObject("Banana", 0);
        p = scene1.createGameObject("Poop", 1);
        new Sprite(b,"src/core/Engine2D/Engine_Data/Banana_Sprite.jpg");
        new Sprite(p,null);
        new CustomCompExample(p);
        //b.transform.setVelocity(new PVector(1, 0));
    }

    public void draw() {
        background(100, 100, 0);
        fill(0, 0, 255);
        textSize(30);
        text(frameRate, 750, 40);
        //p.transform.setPosition(new PVector(mouseX, mouseY));
        System.out.println(EngineMaster.keysDown);
        EngineMaster.executeInputs();
        scene1.Update();
    }

    public void mouseClicked(){

    }

    public void keyPressed(){
        EngineMaster.pressKey(key);
    }

    public void keyReleased(){
        EngineMaster.unpressKey(key);
    }
}
