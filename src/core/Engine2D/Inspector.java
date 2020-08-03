package core.Engine2D;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Inspector {

    private static Color insClr = new Color(0xC0C1C1);
    private static Color gcClr = new Color(0x180506);
    private static Color compClr = new Color(0xFF253D);
    private static Color fieldClr = new Color(0xB3241D);
    private static int fieldLinSpc = 30;
    private static int compLinSpc = 60;
    private static int gcLinSpc = 90;

    public GameObject currentObj;
    public PVector origin;
    public static PVector dimensions = new PVector(400, 1000);
    public boolean right;
    private PApplet p;
    private Scanner sc;

    private ArrayList<Component> comps;
    private int textRef;

    private ArrayList<ArrayList<Field>> goFields;
    private ArrayList<Integer> clickPoints;

    public Inspector(PApplet parent, GameObject currentObj){
        sc = new Scanner(System.in);
        right = true;
        if (currentObj != null)
        setCurrentObj(currentObj);
        p = parent;
        origin = new PVector(p.width - dimensions.x, 0);
        textRef = 0;
    }

    public void Display() {
        p.fill(insClr.getRGB());
        p.rect(origin.x, origin.y, dimensions.x, dimensions.y);
        if (currentObj != null){
            insPrint(currentObj.name, 50, gcLinSpc, gcClr);
            for (Component c : comps) {
                //System.out.println(c.getClass().getSimpleName());
                insPrint(c.getClass().getSimpleName(), 40, compLinSpc, compClr);
                Field[] allFields = c.getClass().getDeclaredFields();
                for (Field f : allFields){
                    //Add condition for only public fields (Modifier.isPublic)
                    if (Modifier.isPublic(f.getModifiers())){
                        insPrint(f.getName(), 20, fieldLinSpc, fieldClr);
                        clickPoints.add(textRef);
                    }
                }

            }
            textRef = 0;
            //Debug for the inspector
            for (int i = 0; i < clickPoints.size(); i++){
                p.fill(255, 0, 0);
                p.circle(1400, clickPoints.get(i)-5, 30);
            }
        }
    }

    public void switchSides(){
        if (right){
            origin = new PVector(0, 0);
            right = false;
        }
        else{
            origin = new PVector(p.width - dimensions.x, 0);
            right = true;
        }
    }

    public void setCurrentObj(GameObject currentObj) {
        this.currentObj = currentObj;
        comps = currentObj.getAllComponenets();
        goFields = new ArrayList<ArrayList<Field>>();
        clickPoints = new ArrayList<>();
        for (Component c : comps){
            ArrayList<Field> compF = new ArrayList<>();
            Field[] allFields = c.getClass().getDeclaredFields();
            for (Field f : allFields){
                if (Modifier.isPublic(f.getModifiers()))
                    compF.add(f);
            }
            goFields.add(compF);
        }
    }

    public void getNearestObj(PVector pos, ArrayList<GameObject> sceneObjs){
        float xMin = 20000;
        float yMin = 20000;
        GameObject output = null;
        for (GameObject g : sceneObjs){
            PVector p = new PVector(xMin, yMin);
            if (g.transform.position().dist(pos) < p.dist(pos)){
                xMin = g.transform.position().x;
                yMin = g.transform.position().y;
                output = g;
            }
        }
        setCurrentObj(output);
    }

    private void insPrint(String input, int size, int spacing, Color c){
        textRef += spacing;
        p.fill(c.getRGB());
        p.textSize(size);
        p.textAlign(PConstants.LEFT);
        p.text(input, origin.x + 20, origin.y + textRef);
    }

    public void fetchInspectorField(int y) throws IllegalAccessException {
        int min = 1200;
        int result = 0;
        for (int i = 0; i < clickPoints.size(); i++){
            if (Math.abs(y - clickPoints.get(i)) < min){
                min = Math.abs(y - clickPoints.get(i));
                result = i;
            }
        }
        int pointer = 0;
        for (int i = 0; i < goFields.size(); i++){
            if (result < goFields.get(i).size() + pointer){
                changeVariable(comps.get(i), goFields.get(i).get(result-pointer));
            }
            else{
                pointer += goFields.get(i).size();
            }
        }
    }

    public void changeVariable(Object o, Field f) throws IllegalAccessException {
        System.out.println(f.getType() + "  " + f.getName());
        if (f.getType().equals(boolean.class))
            f.setBoolean(o, sc.nextBoolean());
        else if (f.getType().equals(float.class))
            f.setFloat(o, sc.nextFloat());
        else if (f.getType().equals(int.class))
            f.setInt(o, sc.nextInt());
        else if (f.getType().equals(String.class))
            f.set(o, sc.nextLine());
        else if (f.getType().equals(PVector.class)){
            float x = sc.nextFloat();
            System.out.println("Success setting " + x);
            float y = sc.nextFloat();
            System.out.println("Success setting " + y);
            f.set(o, new PVector(x, y));
        }
    }
}
