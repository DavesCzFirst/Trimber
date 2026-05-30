package trimber.object;

import trimber.math.Vector3D;

public class Hitbox {
    public float minX, minY, minZ, maxX, maxY, maxZ;


    public Hitbox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public Hitbox rayCast(float x, float y, float z){
        return new Hitbox(Math.min(this.minX, this.minX + x), Math.min(this.minY, this.minY + y), Math.min(this.minZ, this.minZ + z),
                Math.max(this.maxX, this.maxX + x), Math.max(this.maxY, this.maxY + y), Math.max(this.maxZ, this.maxZ + z));
    }

    public void moveHitbox(float x, float y, float z){
        this.minX += x;
        this.maxX += x;
        this.minY += y;
        this.maxY += y;
        this.minZ += z;
        this.maxZ += z;
    }

    public void moveHitbox(Vector3D offset){
        this.minX += offset.x;
        this.maxX += offset.x;
        this.minY += offset.y;
        this.maxY += offset.y;
        this.minZ += offset.z;
        this.maxZ += offset.z;
    }

    public Hitbox getSweptBox(float moveX, float moveY, float moveZ) {
        return new Hitbox(
                Math.min(this.minX, this.minX + moveX),
                Math.min(this.minY, this.minY + moveY),
                Math.min(this.minZ, this.minZ + moveZ),
                Math.max(this.maxX, this.maxX + moveX),
                Math.max(this.maxY, this.maxY + moveY),
                Math.max(this.maxZ, this.maxZ + moveZ)
        );
    }

    @Override
    public String toString() {
        return "Hitbox{" +
                "minX=" + minX +
                ", minY=" + minY +
                ", minZ=" + minZ +
                ", maxX=" + maxX +
                ", maxY=" + maxY +
                ", maxZ=" + maxZ +
                '}';
    }

    public boolean intersect(Hitbox a){
        return (this.minX <= a.maxX && this.maxX >= a.minX) &&
                (this.minY <= a.maxY && this.maxY >= a.minY) &&
                (this.minZ <= a.maxZ && this.maxZ >= a.minZ);
    }

    public boolean moveIntersect(Hitbox a, Vector3D u){
        return (this.minX <= a.maxX + u.x && this.maxX >= a.minX + u.x) &&
                (this.minY <= a.maxY + u.y && this.maxY >= a.minY + u.y) &&
                (this.minZ <= a.maxZ + u.z && this.maxZ >= a.minZ + u.z);
    }
    public boolean moveIntersect(Hitbox a, float x, float y, float z){
        return (this.minX <= a.maxX + x && this.maxX >= a.minX + x) &&
                (this.minY <= a.maxY + y && this.maxY >= a.minY + y) &&
                (this.minZ <= a.maxZ + z && this.maxZ >= a.minZ + z);
    }

    public void moveIntersectX(Hitbox a, float x){
        if (this.minX <= a.maxX + x && this.maxX >= a.minX + x){

        }
    }

}
