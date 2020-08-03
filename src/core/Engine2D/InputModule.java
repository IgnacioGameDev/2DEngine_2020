package core.Engine2D;

import processing.core.PApplet;

public interface InputModule {
    public void mouseEvent(int mouseButton);
    public void keyEvent(char key);
    default void startInput(){ EngineMaster.addInput(this); }
    default void stopInput(){ EngineMaster.removeInput(this); }
}
