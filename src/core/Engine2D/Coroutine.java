package core.Engine2D;

public class Coroutine {

    public boolean isActive;
    public float timeDelay;
    public float time;

    public Coroutine(){
        isActive = false;
    }

    public void waitForSeconds(float seconds){
        timeDelay = seconds;
        EngineMaster.addRoutine(this);
    }

    public void execute(){}

}
