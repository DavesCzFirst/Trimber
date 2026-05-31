package trimber;

import trimber.entity.attributes.Attribute;
import trimber.entity.attributes.HitboxAttribute;
import trimber.entity.attributes.MoveAttribute;
import trimber.entity.interfaces.IMoveable;
import trimber.object.Hitbox;

public class GamePhysics {
    float security = 0.001f;


    public void doStuff(Game game) {
        checkForCollision(game);
    }




    void checkForCollision(Game game) {
        



        Hitbox shrinkHitbox;

        for(HitboxAttribute hm : game.collideMoveables) {
            shrinkHitbox = hm.hitbox.getHitboxShrinkY();
            MoveAttribute moveA = hm.entity.getAttribute(MoveAttribute.class);
            for (HitboxAttribute ob : game.collidables) {
                Hitbox edited = shrinkHitbox.rayCast(moveA.moveOffset.x, 0, 0);
                float time;
                if (edited.intersect(ob.hitbox)) {

                    if (moveA.moveOffset.x > 0) {
                        time = (ob.hitbox.minX - hm.hitbox.maxX - security) / moveA.moveOffset.x;
                    } else if (moveA.moveOffset.x < 0) {
                        time = (ob.hitbox.maxX - hm.hitbox.minX + security) / moveA.moveOffset.x;
                    } else {
                        time = 1.0f;
                    }
                    if (time < 0) {
                        time = 0;
                    }

                    if (time <= 1) {
                        moveA.moveOffset.x = moveA.moveOffset.x * time;

                    }
                }

                edited = shrinkHitbox.rayCast(moveA.moveOffset.x, moveA.moveOffset.y, 0);
                if (edited.intersect(ob.hitbox)) {
                    if (moveA.moveOffset.y > 0) {
                        time = (ob.hitbox.minY - hm.hitbox.maxY - security) / moveA.moveOffset.y;
                    } else if (moveA.moveOffset.y < 0) {
                        time = (ob.hitbox.maxY - hm.hitbox.minY + security) / moveA.moveOffset.y;
                    } else {
                        time = 1.0f;
                    }
                    if (time < 0) {
                        time = 0;
                    }
                    if (time <= 1) {
                        moveA.moveOffset.y = moveA.moveOffset.y * time;
                    }
                }

                edited = shrinkHitbox.rayCast(moveA.moveOffset.x, moveA.moveOffset.y, moveA.moveOffset.z);
                if (edited.intersect(ob.hitbox)) {

                    if (moveA.moveOffset.z > 0) {
                        time = (ob.hitbox.minZ - hm.hitbox.maxZ - security) / moveA.moveOffset.z;
                    } else if (moveA.moveOffset.z < 0) {
                        time = (ob.hitbox.maxZ - hm.hitbox.minZ + security) / moveA.moveOffset.z;
                    } else {
                        time = 1.0f;
                    }
                    if (time < 0) {
                        time = 0;
                    }
                    if (time <= 1) {
                        moveA.moveOffset.z = moveA.moveOffset.z * time;

                    }


                }
            }
        }

    }


    public void applyMove(Game game) {
        for(MoveAttribute a: game.moveables){
            for(IMoveable b: a.entity.getAllAttributesByInterface(IMoveable.class)){
                b.applyMove(a.moveOffset);
            }
            a.moveOffset.x = 0;
            a.moveOffset.y = 0;
            a.moveOffset.z = 0;

        }
    }

}
