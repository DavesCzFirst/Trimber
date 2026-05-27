/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.input;

import trimber.Game;
import trimber.math.Vector3D;

/**
 *
 * @author david
 */
public class Controller {
    public double x, z, rotation, xa, za, rotationa;
    
    public void tick(Game game, boolean forward, boolean back, boolean left, boolean right, boolean turnLeft, boolean turnRight){
        float rotationSpeed = 1f ;
        float walkSpeed = 0.1f;
        float xMove = 0;
        float zMove = 0;
        if (forward){
            xMove += (float) Math.sin(-game.player.yaw) * walkSpeed;
            zMove += (float) Math.cos(-game.player.yaw) * walkSpeed;
        }
        if (back){
            xMove -= (float) Math.sin(-game.player.yaw) * walkSpeed;
            zMove -= (float) Math.cos(-game.player.yaw) * walkSpeed;
        }
        if (left){
            xMove -= (float) Math.sin(-game.player.yaw + (Math.PI / 2)) * walkSpeed;
            zMove -= (float) Math.cos(-game.player.yaw + (Math.PI / 2)) * walkSpeed;

        }
        if (right){
            xMove += (float) Math.sin(-game.player.yaw + (Math.PI / 2)) * walkSpeed;
            zMove += (float) Math.cos(-game.player.yaw + (Math.PI / 2)) * walkSpeed;
        }
        if (turnLeft){
            rotationa -= rotationSpeed;
        }
        if (turnRight){
            rotationa += rotationSpeed;
        }
        rotationa -= InputHandler.MouseXMove * rotationSpeed;

        game.player.moveX = xMove;
        game.player.moveZ = zMove;
        game.player.rotateX(InputHandler.MouseYMove);
        game.player.rotateY(-InputHandler.MouseXMove);
        InputHandler.MouseXMove = 0.0f;
        InputHandler.MouseYMove = 0.0f;


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
                
    }
}
