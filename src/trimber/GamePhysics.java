package trimber;

import trimber.object.Hitbox;

public class GamePhysics {
    float security = 0.001f;


    public void doStuff(Game game) {
        checkForCollision(game);
    }

    void checkForCollision(Game game) {
        Hitbox h;
        h= game.player.getHitboxShrinkY();
        
        
        for (Object ob : game.objects) {
            Hitbox edited = h.rayCast(game.player.moveX, 0, 0);
            float time;
            if (edited.intersect(ob.hitbox)) {

                if (game.player.moveX > 0) {
                    time = (ob.hitbox.minX - game.player.hitbox.maxX - security) / game.player.moveX;
                } else if (game.player.moveX < 0) {
                    time = (ob.hitbox.maxX - game.player.hitbox.minX + security) / game.player.moveX;
                }
                else{
                    time = 1.0f;
                }
                if (time < 0){
                    time = 0;
                }

                if (time <= 1) {
                    game.player.moveX = game.player.moveX * time;

                }
            }

            edited = h.rayCast(game.player.moveX, game.player.moveY, 0);
            if (edited.intersect(ob.hitbox)) {
                if (game.player.moveY > 0) {
                    time = (ob.hitbox.minY - game.player.hitbox.maxY - security) / game.player.moveY;
                } else if (game.player.moveY < 0) {
                    time = (ob.hitbox.maxY - game.player.hitbox.minY + security) / game.player.moveY;
                }
                else{
                    time = 1.0f;
                }
                if (time < 0){
                    time = 0;
                }
                if (time <= 1) {
                    game.player.moveY = game.player.moveY * time;
                }
            }

            edited = h.rayCast(game.player.moveX, game.player.moveY, game.player.moveZ);
            if (edited.intersect(ob.hitbox)) {

                if (game.player.moveZ > 0) {
                    time = (ob.hitbox.minZ - game.player.hitbox.maxZ - security) / game.player.moveZ;
                } else if (game.player.moveZ < 0) {
                    time = (ob.hitbox.maxZ - game.player.hitbox.minZ + security) / game.player.moveZ;
                }else{
                    time = 1.0f;
                }
                if (time < 0){
                    time = 0;
                }
                if (time <= 1) {
                    game.player.moveZ = game.player.moveZ * time;

                }


            }
        }

    }



}
