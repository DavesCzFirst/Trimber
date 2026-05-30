package trimber.entity.attributes;

import trimber.math.Vector3D;

public class RotationAttribute extends Attribute{
    
    //public Vector3D rotationOffset = new Vector3D(0,0,0);
    
    public void doRotation(Vector3D rotationOffset){
        rotateY(rotationOffset.y);
        rotateZ(rotationOffset.z);
        entity.rotation.x += rotationOffset.x;
    }

    private void rotateY(float angleY){
        //System.out.println("pitch: "+ entity.rotation.y);
        if((float) (entity.rotation.y + angleY) > 1.55f){
            entity.rotation.y = (float) 1.55f;
        } else if ((float) (entity.rotation.y + angleY) < (float)-1.55f) {
            entity.rotation.y = (float) - 1.55f;
        } else{
            entity.rotation.y = (float) ( (entity.rotation.y + angleY));
        }
        //System.out.println("pitch22: "+ entity.rotation.x);
    }

    private void rotateZ(float angleY){
        entity.rotation.z = (float) ( (entity.rotation.z + angleY) % (2*Math.PI));
    }
    
    
}
