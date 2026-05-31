package trimber.entity.attributes;

import trimber.entity.interfaces.IMoveable;
import trimber.math.Vector3D;

public class MovementAttribute extends Attribute implements IMoveable {


    public Vector3D rotationOffset = new Vector3D(0,0,0);

    @Override
    public void applyMove(Vector3D offset) {
        Vector3D.add(entity.position, offset);

    }

    @Override

    public void onEntityLoad() {
        MoveAttribute a = entity.getAttribute(MoveAttribute.class);
        if(a == null){
            entity.addAttribute(new MoveAttribute());
            a = entity.getAttribute(MoveAttribute.class);
        }
        a.attributes.add(this);

    }
}
