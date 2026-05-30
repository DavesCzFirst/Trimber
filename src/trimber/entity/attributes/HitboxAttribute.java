package trimber.entity.attributes;

import trimber.entity.interfaces.IMoveable;
import trimber.math.Vector3D;
import trimber.object.EntitiyHitbox;
import trimber.object.Hitbox;

public class HitboxAttribute extends Attribute implements IMoveable {
    Hitbox hitbox;

    public HitboxAttribute(float width, float height, float depth) {
        this.hitbox = new EntitiyHitbox(width, height, depth);
        applyMove(entity.position);
    }

    @Override
    public void applyMove(Vector3D offset) {
        hitbox.moveHitbox(offset);

    }
}
