/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.input;

import trimber.Game;
import trimber.entity.Entity;
import trimber.entity.attributes.ClickAttribute;
import trimber.entity.attributes.ControlAttribute;
import trimber.entity.attributes.MoveAttribute;
import trimber.entity.attributes.RotationAttribute;
import trimber.math.Vector3D;
import trimber.object.Hitbox;

/**
 *
 * @author david
 */
public class Controller {

    private Vector3D rotationOffset = new Vector3D();
    private Vector3D movementOffset = new Vector3D();
    
    public void tick(Game game, InputHandler input){
        for(ControlAttribute a: game.controlables){
            float xMove = 0;
            float zMove = 0;
            //rotation first
            RotationAttribute a1 = a.entity.getAttribute(RotationAttribute.class);
            if(a1!= null){
                float rotationSpeed = a1.rotationSpeed;
                rotationOffset.x = 0;
                rotationOffset.y = InputHandler.MouseYMove;
                rotationOffset.z = -InputHandler.MouseXMove;
                a1.doRotation(rotationOffset);

            }


            float walkSpeed = a.entity.getAttribute(MoveAttribute.class).walkSpeed;
            if (input.key[Keybinds.FORWARD]){
                xMove += (float) Math.sin(-a.entity.rotation.z) * walkSpeed;
                zMove += (float) Math.cos(-a.entity.rotation.z) * walkSpeed;
            }
            if (input.key[Keybinds.BACK]){
                xMove -= (float) Math.sin(-a.entity.rotation.z) * walkSpeed;
                zMove -= (float) Math.cos(-a.entity.rotation.z) * walkSpeed;
            }
            if (input.key[Keybinds.LEFT]){
                xMove -= (float) Math.sin(-a.entity.rotation.z + (Math.PI / 2)) * walkSpeed;
                zMove -= (float) Math.cos(-a.entity.rotation.z + (Math.PI / 2)) * walkSpeed;

            }
            if (input.key[Keybinds.RIGHT]){
                xMove += (float) Math.sin(-a.entity.rotation.z + (Math.PI / 2)) * walkSpeed;
                zMove += (float) Math.cos(-a.entity.rotation.z + (Math.PI / 2)) * walkSpeed;
            }


            movementOffset.x = xMove;
            movementOffset.y = 0;
            movementOffset.z = zMove;
            //game.player.moveX = xMove;
            //game.player.moveZ = zMove;
            a.setOffset(movementOffset);
            InputHandler.MouseXMove = 0.0f;
            InputHandler.MouseYMove = 0.0f;
            /*System.out.println(movementOffset.x);
            System.out.println(movementOffset.y);
            System.out.println(movementOffset.z);*/




            //actions---------------------------------------------------------------
            if(input.rightClick){

                for(ClickAttribute cA: game.clickables){

                }
            }
        }


        /*
        //System.out.println(game.player.posX);
        //System.out.println(game.cam.m);
        za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;
        //System.out.println(InputHandler.MouseXMove * rotationSpeed);
        //System.out.println("X: "+ game.player.posX + " Z: " + game.player.posZ);
        //System.out.println("XX: "+ game.player.pitch + " YY: " + game.player.yaw);

        x += xa;
        z += za;
        xa *= 0.1;
        za *= 0.1;
        rotation += rotationa;
        rotationa *= 0.8;
        //System.out.println(rotation);
                */
    }

    private Hitbox getSeeVision(){
        return new Hitbox(0,0,0,0,0,0);
    }



}
