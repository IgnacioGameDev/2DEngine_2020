package core.Engine2D;

import processing.core.PVector;

public class CustomObjExample implements InputModule {

    GameObject g;

    public CustomObjExample(GameObject g){
        this.g = g;
        initialize();
    }

    @Override
    public void keyEvent(char key) {
        if (key == 'w'){
            g.transform.setPosition(new PVector(500, 500));
        }
    }

    @Override
    public void mouseEvent(int mouseButton) {

    }
}
