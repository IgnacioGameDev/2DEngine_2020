package core.Engine2D;

import java.util.Scanner;

public class EngineCommands {

    private Scanner sc;

    public EngineCommands(){
        sc = new Scanner(System.in);
    }

    public String consWaitInput(){
        return sc.nextLine().toLowerCase();
    }

    public void executeCommand(){
        String s = sc.nextLine().toLowerCase();
        switch (s){
            case "peepeedick":
                break;
            case "bing bong":
                break;
            default:
                break;
        }
    }
}
