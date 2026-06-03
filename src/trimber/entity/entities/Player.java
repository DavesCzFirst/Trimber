package trimber.entity.entities;

import trimber.entity.Entity;
import trimber.entity.attributes.*;
import trimber.math.Vector3D;


public class Player extends Entity {


    public Player() {
        super(new Vector3D(0,0,0));
        addAttribute(new MoveAttribute());
        addAttribute(new HitboxAttribute(0.8f, 1.96f, 0.8f));
        addAttribute(new CameraViewAttribute());
        addAttribute(new ControlAttribute());
        addAttribute(new RotationAttribute());

    }
}
