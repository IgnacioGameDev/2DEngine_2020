package core.Engine2D;

import processing.core.PVector;

public class CustomCompExample extends Component implements InputModule {

    public CustomCompExample(GameObject g){
        super(g);
        initialize();
    }

    @Override
    protected void update() {

    }

    @Override
    public void mouseEvent(int mouseButton) {

    }

    @Override
    public void keyEvent(char key) {
        if (key == 'w')
        gameObject.transform.setPosition(new PVector(parent.mouseX, parent.mouseY));
    }
}
