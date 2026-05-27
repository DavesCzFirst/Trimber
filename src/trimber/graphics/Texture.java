/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author david
 */
public class Texture {
    public Render image;

    public Texture() {
        this.image = loadBitMap("/textures/floor.png");
    }


    public Texture(int color){
        image = new Render(1,1);
        image.pixels[0] = color;
    }



    public Render loadBitMap(String fileName){
        try {
            BufferedImage image = ImageIO.read(Texture.class.getResourceAsStream(fileName));
            int height = image.getHeight();
            int width = image.getWidth();
            Render result = new Render(width, height);
            image.getRGB(0, 0, width, height, result.pixels, 0, width);
            return result;
        } catch (Exception e) {
            System.out.println("CRASH!");
            throw new RuntimeException(e);
        }
    }
}
