/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber.graphics;

import trimber.Game;
import trimber.Object;
import trimber.math.Mat4;
import trimber.math.Triangle3D;
import trimber.math.Vector3D;


public class Render3D extends Render {
    
    public double[] zBuffer;
    private final double renderDistance = 10000;


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
        Vector3D cameraPos = new Vector3D(game.player.posX, game.player.posY + game.player.height, game.player.posZ); // 1 unit up

// Define where it is looking
        //Vector3D target = new Vector3D(0.0f, 0.0f, 1.0f);

        Vector3D lookDirection = new Vector3D(0.0f, 0.0f, 1.0f);

        // Looking forward
        Mat4 matCameraPitch = Mat4.rotationX(game.player.pitch);
        Mat4 matCameraYaw = Mat4.rotationY(game.player.yaw);
        lookDirection = matCameraPitch.multiplyVec3D(lookDirection);
        lookDirection = matCameraYaw.multiplyVec3D(lookDirection);

        Vector3D target = cameraPos.add(lookDirection);

        Vector3D up = new Vector3D(0.0f, 1.0f, 0.0f);     // The Y axis is "up"


        Mat4 matCamera = Mat4.makePointAt(cameraPos, target, up);

// 2. Invert it to create the VIEW MATRIX!
        Mat4 matView = Mat4.makeQuickInverse(matCamera);

        for (Object obj : game.objects) {

            // 1. Build the Translation, Rotation, and Scale matrices for THIS object
            Mat4 matScale = Mat4.makeScale(obj.scale);
            Mat4 matRotX = Mat4.rotationX(obj.rotation.x);
            Mat4 matRotY = Mat4.rotationY(obj.rotation.y);
            Mat4 matRotZ = Mat4.rotationZ(obj.rotation.z);
            Mat4 matTrans = Mat4.makeTranslation(obj.position.x, obj.position.y, obj.position.z);

            // 2. Combine them into the MODEL MATRIX! (Scale -> Rotate -> Translate)
            Mat4 matModel = matScale.multiplyMat(matRotX);
            matModel = matModel.multiplyMat(matRotY);
            matModel = matModel.multiplyMat(matRotZ);
            matModel = matModel.multiplyMat(matTrans);

            // 3. Now loop through the triangles of this specific object
            for (Triangle3D tri : obj.mesh.triangles) {

                Vector3D[] transVertices = new Vector3D[3];
                Vector3D[] viewedVertices = new Vector3D[3];

                for (int i = 0; i < 3; i++) {

                    // A. Move from Object Space -> World Space
                    // THIS REPLACES YOUR OLD MANUAL ROTATION AND TRANSLATION!
                    transVertices[i] = matModel.multiplyVec3D(tri.cor[i]);

                    // B. Move from World Space -> View Space (The Camera)
                    viewedVertices[i] = matView.multiplyVec3D(transVertices[i]);


                }
                if (viewedVertices[0].z < 0.1f || viewedVertices[1].z < 0.1f || viewedVertices[2].z < 0.1f) {
                    continue; // Skip the rest of the loop and move to the next triangle
                }
                // ==========================================
                // PHASE 2: VISIBILITY (CULLING)
                // ==========================================
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
                    // ==========================================
                    // PHASE 3: PROJECTION (Only if visible!)
                    // ==========================================
                    Triangle3D projectedTri = new Triangle3D();

                    for (int i = 0; i < 3; i++) {
                        Vector3D projectedVertex = game.player.mat.multiplyVec3D(viewedVertices[i]);

                        projectedVertex.x = (projectedVertex.x + 1.0f) * 0.5f * render.width;
                        projectedVertex.y = ( 1.0f - projectedVertex.y) * 0.5f * render.height;
                        projectedVertex.z = viewedVertices[i].z;

                        projectedTri.cor[i] = projectedVertex;
                    }

                    // ==========================================
                    // PHASE 4: DRAWING
                    // ==========================================
                    // Notice how these are now INSIDE the "if" statement!
                    render.drawTriangle(projectedTri.cor[0], projectedTri.cor[1], projectedTri.cor[2], obj);
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
