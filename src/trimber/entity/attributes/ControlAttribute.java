package trimber.entity.attributes;

import trimber.math.Vector3D;

public class ControlAttribute extends Attribute {
    public Vector3D offset = new Vector3D(0,0,0);

    @Override
    public void onEntityLoad() {
        entity.addAttribute(new MoveAttribute());
    }

    public void setOffset(Vector3D offset) {
        MoveAttribute a = entity.getAttribute(MoveAttribute.class);
        if(a != null){
            a.addOffset(offset);
        }
    }

}
