package trimber.entity.attributes;

import trimber.entity.interfaces.IMoveable;
import trimber.math.Vector3D;
import trimber.object.Hitbox;

public class ClickAttribute extends Attribute implements IMoveable {

    public Hitbox hitbox;

    public ClickAttribute(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public void doStuff(){

    }

    @Override
    public void applyMove(Vector3D offset) {
        hitbox.moveHitbox(offset);
    }
}
