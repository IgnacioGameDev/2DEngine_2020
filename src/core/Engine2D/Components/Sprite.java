package core.Engine2D.Components;


import core.Engine2D.Component;
import core.Engine2D.EngineMaster;
import core.Engine2D.GameObject;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.beans.PropertyVetoException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sprite extends Component {

    private PGraphics layer;
    private PImage img;
    private String path;
    private String defaultPath = EngineMaster.deFilePath + "Bird_Sprite.png";
    private PVector imgSize;
    private PVector pixelSize;

    public Sprite(GameObject g, String newPath){
        super(g);
        layer = EngineMaster.currentScene.uniqueLayer;
        imgSize = g.transform.scale();
        if (newPath == null || !Files.exists(Paths.get(newPath)) ){ path = defaultPath; }
        else { path = newPath; }
        img = parent.loadImage(path);
        pixelSize = new PVector(img.pixelWidth, img.pixelHeight);
    }

    @Override protected void update() {
        layer.beginDraw();
        if (imgSize.x != gameObject.transform.scale().x || imgSize.y != gameObject.transform.scale().y){
            int x = (int)(img.pixelWidth*(gameObject.transform.scale().x/imgSize.x));
            int y = (int)(img.pixelHeight*(gameObject.transform.scale().y/imgSize.y));
            img = parent.loadImage(path);
            img.resize(x, y);
            imgSize = gameObject.transform.scale();
            pixelSize = new PVector(img.pixelWidth*gameObject.transform.scale().x, img.pixelHeight*gameObject.transform.scale().y);
        }
        layer.image(img, gameObject.transform.position().x-img.width/2, gameObject.transform.position().y-img.height/2);
        layer.endDraw();
    }

    public void setLayer(PGraphics layer) { this.layer = layer; }

    public void setImg(String newPath){
        if (newPath == null || !Files.exists(Paths.get(newPath)) ){ path = defaultPath; }
        else { path = newPath; }
        img = parent.loadImage(path);
    }

    public PVector getPixelSize() { return pixelSize; }

}
