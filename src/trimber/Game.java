/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import trimber.graphics.Color;
import trimber.input.Controller;
import trimber.math.Mat4;
import trimber.math.Vector3D;
import trimber.object.Cube;
import trimber.object.Plane;

/**
 *
 * @author david
 */
public class Game {
    public int time;
    public Controller controls;
    public Player player;
    public List<Object> objects;
    public GamePhysics engine;

    
    public Game(Player player){
        controls = new Controller();
        this.objects = new ArrayList<Object>();
        this.player = player;
        engine = new GamePhysics();

    }


    
    public void tick(boolean key[]){
        time++;
        boolean forward = key[KeyEvent.VK_W];
        boolean back = key[KeyEvent.VK_S];
        boolean left = key[KeyEvent.VK_A];
        boolean right = key[KeyEvent.VK_D];
        boolean turnLeft = key[KeyEvent.VK_LEFT];
        boolean turnRight = key[KeyEvent.VK_RIGHT];
        
        controls.tick(this, forward, back, left, right, turnLeft, turnRight);
        engine.doStuff(this);
        player.applyMove();

        
    }

    public void loadObjects(){
        this.objects.add(new Cube(new Vector3D(10f,10f,10f) , Color.randomColor()));
        this.objects.add(new Cube(new Vector3D(-10f,10f,10f) , Color.randomColor()));
        this.objects.add(new Cube(new Vector3D(1f,0f,1f), Color.RED));
        this.objects.add(new Cube(new Vector3D(1f,1f,1f), Color.BLUE));
        this.objects.add(new Cube(new Vector3D(0f,1f,100f), Color.BLUE));
        this.objects.add(new Plane(10,10));
    }

}
