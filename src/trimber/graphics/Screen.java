/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.graphics;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import trimber.Game;
import trimber.math.Mat4;
import trimber.math.Mesh;


public class Screen extends Render {
    
    private Render test;
    private Render3D render;

    
    public Screen(int width, int height) {
        Random random = new Random();
        super(width, height);
        test = new Render(256,256);
        render = new Render3D(width, height);
        for( int i = 0; i < 256*256; i++){
            test.pixels[i] = random.nextInt();
        }
    }
    public void clearScreen(){
        for(int i = 0; i < height*width; i++){
            pixels[i] = 0;

            zBuffer[i] = Float.MAX_VALUE;
        }
    }

    public void render(Game game){
        clearScreen();
        //draw(test, 2,2);
        render.drawObjects(this, game);
        //System.out.println(Arrays.toString(pixels));
        //drawTriangle(new Point(10,10), new Point(300, 50),new Point(59,700));
        //render.floor(game);
        //render.renderDistanceLimiter();
        //draw(render, 0 , 0 );
        
    }
    
}
