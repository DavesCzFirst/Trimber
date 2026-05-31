/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.graphics;

import trimber.Game;
import trimber.Object;
import trimber.entity.Entity;
import trimber.entity.attributes.TextureAttribute;
import trimber.math.Mat4;
import trimber.math.Triangle3D;
import trimber.math.Vector3D;


public class Render3D extends Render {
    
    public double[] zBuffer;
    private final double renderDistance = 10000;


    private Vector3D[] transVertices = {new Vector3D(), new Vector3D(), new Vector3D()};
    private Vector3D[] viewedVertices = {new Vector3D(), new Vector3D(), new Vector3D()};
    private Triangle3D projectedTri = new Triangle3D(new Vector3D(), new Vector3D(), new Vector3D());


    public Render3D(int width, int height) {
        super(width, height);
        zBuffer = new double[width * height] ;
    }


    public int getShadedColor(int r, int g, int b, float illumination) {
        int shadedR = (int)(r * illumination);
        int shadedG = (int)(g * illumination);
        int shadedB = (int)(b * illumination);

        // Combine back into a single ARGB integer
        return (255 << 24) | (shadedR << 16) | (shadedG << 8) | shadedB;
    }




    public void drawObjects(Render render, Game game){
        Vector3D lightDirection = new Vector3D(0.0f, 0.0f, -1.0f);
        lightDirection.normalize();
        Vector3D cameraPos = new Vector3D(game.renderEntity.entity.position.x, game.renderEntity.entity.position.y + game.renderEntity.height, game.renderEntity.entity.position.z); // 1 unit up


        //start pos
        Vector3D lookDirection = new Vector3D(0.0f, 0.0f, 1.0f);

        // Looking forward
        Mat4 matCameraPitch = Mat4.rotationX(game.renderEntity.entity.rotation.y);
        Mat4 matCameraYaw = Mat4.rotationY(game.renderEntity.entity.rotation.z);
        matCameraPitch.multiplyVec3D(lookDirection, lookDirection);
         matCameraYaw.multiplyVec3D(lookDirection, lookDirection);

        Vector3D target = cameraPos.add(lookDirection);

        Vector3D up = new Vector3D(0.0f, 1.0f, 0.0f);     // The Y axis is "up"


        Mat4 matCamera = Mat4.makePointAt(cameraPos, target, up);

// 2. Invert it to create the VIEW MATRIX!
        Mat4 matView = Mat4.makeQuickInverse(matCamera);

        for (TextureAttribute texture : game.renderables) {

            // 1. Build the Translation, Rotation, and Scale matrices for THIS object
            Mat4 matScale = Mat4.makeScale(texture.entity.scale.x);
            Mat4 matRotX = Mat4.rotationX(texture.entity.rotation.x);
            Mat4 matRotY = Mat4.rotationY(texture.entity.rotation.y);
            Mat4 matRotZ = Mat4.rotationZ(texture.entity.rotation.z);
            Mat4 matTrans = Mat4.makeTranslation(texture.entity.position.x, texture.entity.position.y, texture.entity.position.z);

            // 2. Combine them into the MODEL MATRIX! (Scale -> Rotate -> Translate)
            Mat4 matModel = matScale.multiplyMat(matRotX);
            matModel = matModel.multiplyMat(matRotY);
            matModel = matModel.multiplyMat(matRotZ);
            matModel = matModel.multiplyMat(matTrans);

            // 3. Now loop through the triangles of this specific object
            for (Triangle3D tri : texture.mesh.triangles) {



                for (int i = 0; i < 3; i++) {


                    matModel.multiplyVec3D(tri.cor[i], transVertices[i]);

                    // Move from World Space -> View Space (The Camera)
                    matView.multiplyVec3D(transVertices[i], viewedVertices[i]);


                }
                if (viewedVertices[0].z < 0.1f || viewedVertices[1].z < 0.1f || viewedVertices[2].z < 0.1f) {
                    continue; // Skip the rest of the loop and move to the next triangle
                }
                Vector3D line1 = viewedVertices[1].subtract(viewedVertices[0]);
                Vector3D line2 = viewedVertices[2].subtract(viewedVertices[0]);

                Vector3D normal = line1.crossProduct(line2);
                normal.normalize();

                Vector3D cameraRay = viewedVertices[0];

                // If it's facing the camera...
                if (normal.dotProduct(cameraRay) < 0.0f) {
                    float illumination = normal.dotProduct(lightDirection);

    // If the light is behind the triangle, the dot product will be negative.
    // We don't want negative light, so clamp it to a minimum of 0.0!
                    if (illumination < 0.0f) {
                        illumination = 0.0f;
                    }

                    for (int i = 0; i < 3; i++) {
                        game.renderEntity.cameraMat.multiplyVec3D(viewedVertices[i], projectedTri.cor[i]);

                        projectedTri.cor[i].x = (projectedTri.cor[i].x + 1.0f) * 0.5f * render.width;
                        projectedTri.cor[i].y = (1.0f - projectedTri.cor[i].y) * 0.5f * render.height;
                        projectedTri.cor[i].z = viewedVertices[i].z;


                    }

                    //drawing
                    render.drawTriangle(projectedTri.cor[0], projectedTri.cor[1], projectedTri.cor[2], texture);
                    //render.drawLine(new Point((int) projectedTri.cor[0].x, (int) projectedTri.cor[0].y), new Point((int) projectedTri.cor[1].x, (int) projectedTri.cor[1].y), Color.BLACK);
                    //render.drawLine(new Point((int) projectedTri.cor[1].x, (int) projectedTri.cor[1].y), new Point((int) projectedTri.cor[2].x, (int) projectedTri.cor[2].y), Color.RED);
                    //render.drawLine(new Point((int) projectedTri.cor[2].x, (int) projectedTri.cor[2].y), new Point((int) projectedTri.cor[0].x, (int) projectedTri.cor[0].y), Color.BLUE);
                }
            }


    }}


    public void floor(Game game){
        
        double floorPosition = 10;
        double ceilingHeight = 5;
        double forward = game.controls.z;
        double right = game.controls.x;
        
        double rotation = game.controls.rotation;
        double cosine = Math.cos(rotation);
        double sine = Math.sin(rotation);
        
        for (int y = 0; y < height; y++){
            double ceiling = (y - height /2.0)/ height;
            double z = floorPosition/ ceiling;
            if (ceiling < 0){
                z = ceilingHeight / -ceiling;
            }
            
            for (int x = 0; x < width; x++) {
                double xDepth = (x - width/2.0)/height;
                xDepth *= z;
                double xx = xDepth * cosine + z * sine + right;
                double yy = z * cosine - xDepth * sine + forward;
                int xPix = (int) (xx);
                int yPix = (int) (yy);
                zBuffer[x + y* width] = z;
                //pixels[x + y* width] = Texture.floor.pixels[(xPix & 15) + (yPix& 15)* Texture.floor.width];
                if(z > 500){
                    pixels[x + y* width] = 0;
                } 
            }
            
        }
    }
    
    public void renderDistanceLimiter(){
        for ( int i = 0; i < width * height; i++){
            int color = pixels[i];
            int brightness = (int) (renderDistance/(zBuffer[i]));
            
            if (brightness < 0)
                brightness = 0;
            if (brightness > 255){
                brightness = 255;
               
            }
            int r = (color >> 16) & 0xff;
            int g = (color >> 8) & 0xff;
            int b = (color) & 0xff;
            
            r = r * brightness/255;
            g = g * brightness/255;
            b = b * brightness/255;
            pixels[i] = r << 16 | g << 8 | b;
        }
    }
    
}
