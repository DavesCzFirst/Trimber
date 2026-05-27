package trimber;

import trimber.graphics.Render;
import trimber.graphics.Texture;
import trimber.math.Mesh;
import trimber.math.Vector3D;
import trimber.object.Collidable;
import trimber.object.Hitbox;
import trimber.object.ObjectCollideProperties;

public class Object implements Collidable{
    public Vector3D position;
    public Vector3D rotation;
    public Mesh mesh;
    public float scale;
    public int color;
    public Texture texture;
    public Hitbox hitbox;
    public ObjectCollideProperties properties;


    public Object(Mesh mesh) {
        this.mesh = mesh;

    }
        
    public Object(Vector3D position, Mesh mesh) {
        this.position = position;
        this.mesh = mesh;
    }

    public Object(Mesh mesh, Vector3D position, Vector3D rotation, float scale) {
        this.mesh = mesh;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Object(Mesh mesh, Vector3D position, Vector3D rotation, float scale, int color, Texture texture, ObjectCollideProperties properties) {
        this.mesh = mesh;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.color = color;
        this.texture = texture;
        this.properties = properties;
    }


    @Override
    public Hitbox getHitbox() {
        if(properties == ObjectCollideProperties.NONE){
            return null;

        }
        return hitbox;
    }
}

