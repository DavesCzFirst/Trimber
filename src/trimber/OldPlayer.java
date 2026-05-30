package trimber;

import trimber.math.Mat4;
import trimber.object.Collidable;
import trimber.object.Hitbox;

public class OldPlayer implements Collidable {
    public float posX = 0;
    public float posY = 0;
    public float posZ = 0;
    public float moveZ = 0;
    public float moveX = 0;
    public float moveY = 0;
    public Mat4 mat;
    public float yaw = 0.0f;
    public float pitch = 0.0f;
    public float height;
    public float width;
    public float length;
    public Hitbox hitbox;


    public OldPlayer(float height) {
        this.height = height;
        mat = Mat4.makeProjectionMatrix(90.0f, (float) Display.WIDTH /Display.HEIGHT, 0.1f, 1000.0f);
        this.width = 0.8f;
        this.length = 0.8f;
        this.hitbox = new Hitbox(-width/2, 0f, -length/2, width/2, height, length/2);
    }

    public OldPlayer() {
        mat = Mat4.makeProjectionMatrix(90.0f, (float) Display.WIDTH /Display.HEIGHT, 0.1f, 1000.0f);
        this.height = 0;
        this.width = 0.8f;
        this.length = 0.8f;
    }

    public void rotateX(float angleX){
        //System.out.println("pitch: "+ this.pitch);
        if((float) (this.pitch + angleX) > 1.55f){
            this.pitch = (float) 1.55f;
        } else if ((float) (this.pitch + angleX) < (float)-1.55f) {
            this.pitch = (float) - 1.55f;
        } else{
            this.pitch = (float) ( (this.pitch + angleX));
        }
        //System.out.println("pitch22: "+ this.pitch);
    }

    public void rotateY(float angleY){
        this.yaw = (float) ( (this.yaw + angleY) % (2*Math.PI));
    }

    public void applyMove(){
        this.posX += this.moveX;
        this.posZ += this.moveZ;
        this.hitbox.moveHitbox(moveX, moveY, moveZ);


    }


    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }
    public Hitbox getHitboxShrinkY(){

        return new Hitbox(hitbox.minX,
                hitbox.minY +0.02f,
                hitbox.minZ,
                hitbox.maxX,
                hitbox.maxY - 0.02f,
                hitbox.maxZ);
    }
}
