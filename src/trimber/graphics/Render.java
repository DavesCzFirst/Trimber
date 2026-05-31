/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.graphics;

import trimber.Object;
import trimber.entity.attributes.TextureAttribute;
import trimber.math.Vector3D;

import java.awt.*;

/**
 *
 * @author david
 */
public class Render {
    public final int width;
    public final int height;
    public final int[] pixels;
    public float[] zBuffer;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        zBuffer = new  float[width * height];
    }




    public void draw(Render render, int xOffset, int yOffset){
        for (int y = 0; y < render.height; y++){
            int yPix = y + yOffset;
            if(yPix < 0 || yPix >= height)continue;
            for (int x = 0; x < render.width; x++){
                int xPix = x + xOffset;
                if(xPix < 0 || xPix >= width)continue;
                
                /*int alpha = render.pixels[x+y*render.width];
                if (alpha > 0){
                    pixels[xPix + yPix * width] = alpha;
                }*/
                pixels[xPix + yPix * width] = render.pixels[x+y*render.width];
            }
            
        }
            
    }


    public void drawLine(Point a, Point b, int color){
        int xa = a.x;
        int ya = a.y;
        int xb = b.x;
        int yb = b.y;

        int dx = Math.abs(xb - xa);
        int dy = Math.abs(yb - ya);

        int stepX = -1;
        if (xa < xb){
            stepX = 1;
        }
        int stepY = -1;
        if (ya < yb){
            stepY = 1;
        }
        int error = dx - dy;

        while (true){
            if (xa >= 0 && xa < width && ya >= 0 && ya < height) {
                pixels[xa + ya * width] = Color.WHITE;
            }
            if (xa == xb && ya == yb){
                break;
            }
            int error2 = error * 2;
            if (error2 > -dy) {
                error = error - dy;
                xa = xa + stepX;
            }
            if (error2 < dx) {
                error = error + dx;
                ya = ya + stepY;
            }
        }
    }

    public void drawTriangle(Vector3D a, Vector3D b, Vector3D c, TextureAttribute at) {
        /*drawLine(render,a,b);
        drawLine(render,b,c);
        drawLine(render,a,c);*/
        //System.out.println("A: "+ a + " B: "+ b+ " C: "+ c);
        Texture texture = at.texture;
        if (texture == null) {
            texture = new Texture(at.color);
        }

        int minX = (int) Math.min(Math.min(a.x, b.x), c.x);
        int minY = (int) Math.min(Math.min(a.y, b.y), c.y);
        int maxX = (int) Math.max(Math.max(a.x, b.x), c.x);
        int maxY = (int) Math.max(Math.max(a.y, b.y), c.y);
        minX = Math.max(minX, 0);
        minY = Math.max(minY, 0);
        maxX = Math.min(maxX, width - 1);
        maxY = Math.min(maxY, height - 1);

        //System.out.println("min:" + minX + minY + maxX + maxY);

        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                float ta = (b.x - a.x) * (y - a.y) - (b.y - a.y) * (x - a.x);
                float tb = (c.x - b.x) * (y - b.y) - (c.y - b.y) * (x - b.x);
                float tc = (a.x - c.x) * (y - c.y) - (a.y - c.y) * (x - c.x);

                if ((ta >= 0 && tb >= 0 && tc >= 0) || (ta <= 0 && tb <= 0 && tc <= 0)) {
                    float totalArea = ta + tb + tc;
                    if (totalArea != 0) {
                        float wA = tb / totalArea;
                        float wB = tc / totalArea;
                        float wC = ta / totalArea;


                        // If a Z value is exactly 0, the math explodes. Just lock it to a tiny number
                        float zA = a.z == 0.0f ? 0.0001f : a.z;
                        float zB = b.z == 0.0f ? 0.0001f : b.z;
                        float zC = c.z == 0.0f ? 0.0001f : c.z;

                        // Instead of interpolating Z directly, we interpolate the INVERSE of Z!
                        float invZA = 1.0f / zA;
                        float invZB = 1.0f / zB;
                        float invZC = 1.0f / zC;

                        float interpolatedInvZ = (wA * invZA) + (wB * invZB) + (wC * invZC);
                        float pixelDepth = 1.0f / interpolatedInvZ;

                        int index = x + y * width;
                        // It is closer! Draw it!
                        //pixels[index] = pixelColor;

                        if (pixelDepth < zBuffer[index]) {

                            // 5. PERSPECTIVE CORRECT UV MATH!
                            // We multiply U and V by the inverse Z, interpolate them,
                            // and then multiply them by the true pixel depth at the very end!
                            float texU = ((wA * (a.u * invZA)) + (wB * (b.u * invZB)) + (wC * (c.u * invZC))) * pixelDepth;
                            float texV = ((wA * (a.v * invZA)) + (wB * (b.v * invZB)) + (wC * (c.v * invZC))) * pixelDepth;

                            // 6. Convert to Image Pixels
                            int texX = (int) (texU * (texture.image.width - 1));
                            int texY = (int) (texV * (texture.image.height - 1));

                            // Optional constraint to prevent array crashing if math rounds weirdly:
                            texX = Math.max(0, Math.min(texX, texture.image.width - 1));
                            texY = Math.max(0, Math.min(texY, texture.image.height - 1));

                            // 7. Draw it!
                            int texIndex = texX + (texY * texture.image.width);
                            pixels[index] = texture.image.pixels[texIndex];
                            zBuffer[index] = pixelDepth;
                        }

                    }
                }

            }

        }

    }
            
    
    
    
}
