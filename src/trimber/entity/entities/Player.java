package trimber.entity.entities;

import trimber.entity.Entity;
import trimber.entity.attributes.CameraViewAttribute;
import trimber.entity.attributes.HitboxAttribute;
import trimber.entity.attributes.MovementAttribute;


public class Player extends Entity {


    public Player() {
        addAttribute(new MovementAttribute());
        addAttribute(new HitboxAttribute(0.8f, 1.96f, 0.8f));
        addAttribute(new CameraViewAttribute());
    }
}
