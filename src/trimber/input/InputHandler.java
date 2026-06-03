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
    public boolean leftClick = false;
    public boolean leftPressed = false;
    public boolean rightClick = false;
    public boolean rightPressed = false;
    public boolean isMouseLocked = true;
    public Point currentMousePos = new Point();





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
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightClick = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightClick = false;
        }
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
        try {
            Robot robot = new Robot();
            currentMousePos = e.getPoint();
            if (isMouseLocked) {
                if (ignoreNextMove) {
                    ignoreNextMove = false; // We caught it! Reset the flag.
                    return; // STOP running! Ignore this event completely!
                }


                MouseXMove = (currentMousePos.x - MID.x) * SENSITIVITY;
                MouseYMove = (currentMousePos.y - MID.y) * SENSITIVITY;
                int centerX = display.getLocationOnScreen().x + (display.getWidth() / 2);
                int centerY = display.getLocationOnScreen().y + (display.getHeight() / 2);
                ignoreNextMove = true;
                robot.mouseMove(centerX, centerY);

            }
            else{
                if(currentMousePos.x<0){
                    robot.mouseMove(display.getLocationOnScreen().x, currentMousePos.y);
                } else if (currentMousePos.x > display.getWidth()) {
                    robot.mouseMove(display.getWidth()+display.getLocationOnScreen().x, currentMousePos.y);
                }
                currentMousePos = e.getPoint();
                if(currentMousePos.y<0){
                    robot.mouseMove(currentMousePos.x, display.getLocationOnScreen().y);
                } else if (currentMousePos.y > display.getHeight()) {
                    robot.mouseMove(currentMousePos.x, display.getHeight()+display.getLocationOnScreen().y);
                }
            }
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
