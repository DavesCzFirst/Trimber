/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.input;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import trimber.Display;



/**
 *
 * @author david
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, FocusListener{
    
    public boolean[] key = new boolean[68836];
    public static float MouseXMove = 0;
    public static float MouseYMove = 0;
    private Point perviousMousePos = new Point(0,0);
    private float SENSITIVITY = (float) 0.01;
    private Display display;
    private Point MID = new Point(display.WIDTH/2, display.HEIGHT/2);
    public boolean ignoreNextMove = false;

    public InputHandler(Display display) {
        
        this.display = display;
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode > 0 && keyCode < key.length){
            key[keyCode] = true;
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode > 0 && keyCode < key.length){
            key[keyCode] = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        /*Point currentMousePos = e.getPoint();

        // 1. THE TRAP DOOR: If the mouse is exactly in the middle,
        // it means the Robot put it there. DO NOTHING!
        if (currentMousePos.x == MID.x && currentMousePos.y == MID.y) {
            // Reset the move variables so we don't keep spinning when we stop Moving the mouse
            MouseXMove = 0;
            MouseYMove = 0;
            return;
        }

        try {
            Robot robot = new Robot();

            // 2. Calculate the delta (how far the mouse moved from the center)
            MouseXMove = (MID.x - currentMousePos.x) * SENSITIVITY;
            MouseYMove = (MID.y - currentMousePos.y) * SENSITIVITY;

            // 3. Move the mouse back to the absolute screen center
            int centerX = display.getLocationOnScreen().x + (display.getWidth() / 2);
            int centerY = display.getLocationOnScreen().y + (display.getHeight() / 2);

            robot.mouseMove(centerX, centerY);

        } catch (AWTException er) {
            System.out.println(er);
        }*/
        if (ignoreNextMove) {
            ignoreNextMove = false; // We caught it! Reset the flag.
            return; // STOP running! Ignore this event completely!
        }

        try {
            Robot robot = new Robot();
            Point currentMousePos = e.getPoint();

            // 3. Calculate movement (Notice I flipped them to current - MID for standard FPS feel)
            MouseXMove = (currentMousePos.x - MID.x) * SENSITIVITY;
            MouseYMove = (currentMousePos.y - MID.y) * SENSITIVITY;

            int centerX = display.getLocationOnScreen().x + (display.getWidth() / 2);
            int centerY = display.getLocationOnScreen().y + (display.getHeight() / 2);

            // 4. CRITICAL: We are about to use the Robot.
            // Tell the trap door to activate for the next frame!
            ignoreNextMove = true;

            robot.mouseMove(centerX, centerY);

        } catch (AWTException er) {
            System.out.println(er);
        }
        /*
        Point currentMousePos = e.getPoint();
        
        
        MouseXMove = (perviousMousePos.x - currentMousePos.x) * SENSITIVITY;
        MouseYMove = (perviousMousePos.y - currentMousePos.y) * SENSITIVITY;
        
        perviousMousePos = currentMousePos;
        
        */
                
    }

    @Override
    public void focusGained(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void focusLost(FocusEvent e) {
        for (int i = 0; i < key.length; i++)
        {
            key[i] = false;
        }
    }
    
}
