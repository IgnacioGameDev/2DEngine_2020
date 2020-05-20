package core.Engine2D;


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
    private String defaultPath = "src/core/Engine2D/Engine_Data/Bird_Sprite.png";
    private PVector imgSize;

    public Sprite(GameObject g, String newPath){
        super(g);
        imgSize = g.transform.scale();
        layer = parent.createGraphics(800, 800);
        if (newPath == null || !Files.exists(Paths.get(newPath)) ){ path = defaultPath; }
        else { path = newPath; }
        img = parent.loadImage(path);
    }

    @Override protected void update() {
        if (gameObject.isActive){
            layer.beginDraw();
            if (imgSize.x != gameObject.transform.scale().x || imgSize.y != gameObject.transform.scale().y){
                int x = (int)(img.pixelWidth*(gameObject.transform.scale().x/imgSize.x));
                int y = (int)(img.pixelHeight*(gameObject.transform.scale().y/imgSize.y));
                img.resize(x, y);
                imgSize = gameObject.transform.scale();
            }
            layer.image(img, gameObject.transform.position().x, gameObject.transform.position().y);
            layer.endDraw();
        }
    }

    public void setLayer(PGraphics layer) { this.layer = layer; }

}
