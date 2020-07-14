import core.Engine2D.*;
import core.Engine2D.Components.CircleCollider;
import core.Engine2D.Components.CustomCompExample;
import core.Engine2D.Components.Sprite;
import core.Engine2D.Components.TheDeleter;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {

    Scene scene1;
    GameObject ref;
    GameObject b;
    GameObject p;
    GameObject h;

    public static void main(String args[]) { PApplet.main("Main"); }

    public void settings() { size(1200, 1000); }

    public void setup() {
        EngineMaster.getInstance(this);
        scene1 = new Scene("Scene01", 144);
        scene1.load();
        EngineMaster.setCurrentScene(scene1);
        b = scene1.createGameObject("Banana", 0);
        p = scene1.createGameObject("Poop", 1);
        b.transform.setPosition(new PVector(900, 700));
        new Sprite(b, EngineMaster.deFilePath + "Banana_Sprite.jpg");
        new Sprite(p,null);
        new CircleCollider(b);
        new CircleCollider(p);
        p.transform.setScale(new PVector(0.3f, 0.3f));
        p.transform.setPosition(new PVector(100, 100));
        h = scene1.createGameObject("Chomper", 2);
        h.transform.setPosition(new PVector(200, 800));
        new Sprite(h,EngineMaster.deFilePath + "Banana_Sprite.jpg");
        new TheDeleter(h);
        //new CustomCompExample(b);
        //b.transform.setVelocity(new PVector(1, 0));
    }

    public void draw() {
        EngineMaster.Update();
        scene1.Update();
        fill(0, 0, 255);
        textSize(30);
        text(frameRate, 750, 40);
        //p.transform.setPosition(new PVector(mouseX, mouseY));

    }

    public void mousePressed(){ EngineMaster.pressMouse(mouseButton); }

    public void mouseReleased(){ EngineMaster.unpressMouse(mouseButton); }

    public void keyPressed(){
        EngineMaster.pressKey(key);
    }

    public void keyReleased(){
        EngineMaster.unpressKey(key);
    }
}
