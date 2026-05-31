package trimber.entity.attributes;

import trimber.Display;
import trimber.math.Mat4;

public class CameraViewAttribute extends Attribute {
    public Mat4 cameraMat;
    public float height;

    public CameraViewAttribute() {
        cameraMat = Mat4.makeProjectionMatrix(90.0f, (float) Display.WIDTH /Display.HEIGHT, 0.1f, 1000.0f);
        this.height = 1.96f;
    }
}
