package trimber.entity.attributes;

import trimber.entity.interfaces.IMoveable;
import trimber.math.Vector3D;
import trimber.object.hitboxes.EntitiyHitbox;
import trimber.object.Hitbox;

public class HitboxAttribute extends Attribute implements IMoveable {
    public Hitbox hitbox;

    public HitboxAttribute(float width, float height, float depth) {
        this.hitbox = new EntitiyHitbox(width, height, depth);

    }

    public HitboxAttribute(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    @Override
    public void onEntityLoad() {

        //
        applyMove(entity.position);
    }

    @Override
    public void applyMove(Vector3D offset) {
        hitbox.moveHitbox(offset);

    }


}
