package trimber.entity.interfaces;

import trimber.entity.Entity;
import trimber.math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public interface IMoveable {

    public void applyMove(Vector3D offset);

    /*private List checkForMoveAttribute(Entity e){
        return e.getAllAttributesByInterface(IMoveable.class);
    }*/
}
