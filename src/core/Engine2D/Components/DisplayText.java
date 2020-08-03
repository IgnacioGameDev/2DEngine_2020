package core.Engine2D.Components;

import core.Engine2D.Component;
import core.Engine2D.EngineMaster;
import core.Engine2D.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.data.JSONObject;

import java.awt.*;

public class DisplayText extends Component {

    public String activeTextSetter;

    private PGraphics layer;
    private String activeText;
    private int fontSize;
    private Color textColor;

    public DisplayText(GameObject g){
        super(g);
        layer = EngineMaster.currentScene.uniqueLayer;
        activeText = "text";
        activeTextSetter = "text";
        fontSize = 50;
        textColor = EngineMaster.defTxtClr;
    }

    @Override
    protected void update() {
        if (activeText != activeTextSetter)
            setText(activeTextSetter);
        layer.beginDraw();
        layer.fill(textColor.getRGB());
        layer.textSize(fontSize);
        layer.textAlign(PConstants.CENTER);
        layer.text(activeText, gameObject.transform.position().x, gameObject.transform.position().y);
        layer.endDraw();
    }

    @Override
    public JSONObject serializeToJSON() {
        JSONObject j = new JSONObject();
        j.setString("type", getClass().getSimpleName());
        j.setString("activeText", activeText);
        j.setInt("fontSize", fontSize);
        j.setInt("r", textColor.getRed());
        j.setInt("g", textColor.getGreen());
        j.setInt("b", textColor.getBlue());
        j.setInt("a", textColor.getAlpha());
        return j;
    }

    @Override
    public void loadFromJSON(JSONObject jO) {
        setText(jO.getString("activeText"));
        setFontSize(jO.getInt("fontSize"));
        Color c = new Color(jO.getInt("r"), jO.getInt("g"), jO.getInt("b"), jO.getInt("a"));
        setTextColor(c);
    }

    public void setText(String text) {
        this.activeText = text;
        activeTextSetter = activeText;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
