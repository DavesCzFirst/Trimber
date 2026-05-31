/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.input;

import trimber.Game;
import trimber.entity.Entity;
import trimber.entity.attributes.ControlAttribute;
import trimber.entity.attributes.MoveAttribute;
import trimber.entity.attributes.RotationAttribute;
import trimber.math.Vector3D;

/**
 *
 * @author david
 */
public class Controller {
    public double x, z, rotation, xa, za, rotationa;
    private Vector3D rotationOffset = new Vector3D();
    private Vector3D movementOffset = new Vector3D();
    
    public void tick(Game game, boolean forward, boolean back, boolean left, boolean right, boolean turnLeft, boolean turnRight){
        for(ControlAttribute a: game.controlables){
            float xMove = 0;
            float zMove = 0;
            //rotation first
            RotationAttribute a1 = a.entity.getAttribute(RotationAttribute.class);
            if(a1!= null){
                float rotationSpeed = a1.rotationSpeed;
                if (turnLeft){
                    rotationa -= rotationSpeed;
                }
                if (turnRight){
                    rotationa += rotationSpeed;
                }
                //rotationa -= InputHandler.MouseXMove * rotationSpeed;
                rotationOffset.x = 0;
                rotationOffset.y = InputHandler.MouseYMove;
                rotationOffset.z = -InputHandler.MouseXMove;
                a1.doRotation(rotationOffset);

            }


            float walkSpeed = a.entity.getAttribute(MoveAttribute.class).walkSpeed;
            if (forward){
                xMove += (float) Math.sin(-a.entity.rotation.z) * walkSpeed;
                zMove += (float) Math.cos(-a.entity.rotation.z) * walkSpeed;
            }
            if (back){
                xMove -= (float) Math.sin(-a.entity.rotation.z) * walkSpeed;
                zMove -= (float) Math.cos(-a.entity.rotation.z) * walkSpeed;
            }
            if (left){
                xMove -= (float) Math.sin(-a.entity.rotation.z + (Math.PI / 2)) * walkSpeed;
                zMove -= (float) Math.cos(-a.entity.rotation.z + (Math.PI / 2)) * walkSpeed;

            }
            if (right){
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
}
