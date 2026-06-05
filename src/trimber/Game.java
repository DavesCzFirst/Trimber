/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import trimber.entity.Entity;
import trimber.entity.attributes.*;
import trimber.entity.entities.Player;
import trimber.input.Controller;
import trimber.input.InputHandler;

/**
 *
 * @author david
 */
public class Game {
    private EntityLoader eLoader = new EntityLoader();
    public int time;
    public Controller controls;
    public OldPlayer player;
    public CameraViewAttribute renderEntity;
    public List<Object> objects;
    public GamePhysics engine;
    public GameMechanics mechanics;
    public List<Entity> entities = new ArrayList<>();
    public List<HitboxAttribute> collidables = new ArrayList<>();
    public List<HitboxAttribute> collideMoveables = new ArrayList<>();
    public List<TextureAttribute> renderables = new ArrayList<>();
    public List<MoveAttribute> moveables = new ArrayList<>();
    public List<ControlAttribute> controlables = new ArrayList<>();
    public List<ClickAttribute> clickables = new ArrayList<>();

    
    public Game(OldPlayer player){
        controls = new Controller();
        this.objects = new ArrayList<>();
        this.player = player;
        engine = new GamePhysics();
        mechanics = new GameMechanics();

    }


    
    public void tick(InputHandler inputHandler){
        time++;

        
        controls.tick(this, inputHandler);
        engine.doStuff(this);
        engine.applyMove(this);


        
    }

    public void loadObjects(){
        addEntity(new Player());
        renderEntity = entities.getLast().getAttribute(CameraViewAttribute.class);
        eLoader.loadObjects(this);
    }



    public void addEntity(Entity e){
        entities.add(e);
        HitboxAttribute hitbox = e.getAttribute(HitboxAttribute.class);
        MoveAttribute move = e.getAttribute(MoveAttribute.class);
        if (hitbox != null) {
            if(move != null){
                collideMoveables.add(hitbox);
            }
            else{
                collidables.add(hitbox);
            }

        }

        TextureAttribute texture = e.getAttribute(TextureAttribute.class);
        if (texture != null) {
            renderables.add(texture);
        }


        if(move != null){
            moveables.add(move);
        }

        ControlAttribute control = e.getAttribute(ControlAttribute.class);
        if(control != null){
            controlables.add(control);
        }

        ClickAttribute click = e.getAttribute(ClickAttribute.class);
        if(click != null){
            clickables.add(click);
        }


    }

}
