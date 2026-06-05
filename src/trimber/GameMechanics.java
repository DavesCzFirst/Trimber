package trimber;

import trimber.entity.Entity;
import trimber.entity.attributes.CameraViewAttribute;
import trimber.entity.attributes.ClickAttribute;
import trimber.entity.attributes.HitboxAttribute;

public class GameMechanics {

    public Entity doRayCast(Game game, Entity en) {
        float maxReach = 15.0f;
        int steps = 100;
        float stepSize = maxReach / steps;

        float currentX = en.position.x;
        float currentY = en.position.y+ en.getAttribute(CameraViewAttribute.class).height;
        float currentZ = en.position.z;
        float yaw = -en.rotation.z;
        float pitch = en.rotation.y;
        float dirX = (float) (Math.sin(yaw) * Math.cos(pitch));
        float dirY = (float) Math.sin(-pitch);
        float dirZ = (float) (Math.cos(yaw) * Math.cos(pitch));

        for (int i = 0; i <= steps; i++) {
            currentX += dirX * stepSize;
            currentY += dirY * stepSize;
            currentZ += dirZ * stepSize;
            for (HitboxAttribute ob : game.collidables) {
                if (ob.entity == en) {
                    continue;
                }
                boolean inX = currentX >= ob.hitbox.minX && currentX <= ob.hitbox.maxX;
                boolean inY = currentY >= ob.hitbox.minY && currentY <= ob.hitbox.maxY;
                boolean inZ = currentZ >= ob.hitbox.minZ && currentZ <= ob.hitbox.maxZ;
                if (inX && inY && inZ) {
                    return ob.entity;
                }
            }
        }
        return null;


    }
}
