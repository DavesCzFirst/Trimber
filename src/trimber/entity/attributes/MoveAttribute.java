package trimber.entity.attributes;

import trimber.entity.interfaces.IMoveable;
import trimber.math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class MoveAttribute extends Attribute implements IMoveable{
    public float walkSpeed;
    public List<IMoveable> attributes = new ArrayList<IMoveable>();


    public Vector3D moveOffset;

    public MoveAttribute(float walkSpeed) {
        this.walkSpeed = walkSpeed;
        this.moveOffset = new Vector3D(0,0,0);
    }

    public MoveAttribute() {
        this.walkSpeed = 0.1f;
        this.moveOffset = new Vector3D(0,0,0);

    }

    @Override
    public void applyMove(Vector3D offset) {
        Vector3D.add(offset, entity.position);

    }

    public void doMovement(Vector3D offset){
        for(IMoveable a: attributes){
            a.applyMove(offset);
        }
    }

    public void addOffset(Vector3D offset){
        Vector3D.add(offset, this.moveOffset);

    }

}
