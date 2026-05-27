package trimber.object;

import trimber.Object;
import trimber.graphics.Color;
import trimber.graphics.Texture;
import trimber.math.Mesh;
import trimber.math.Triangle3D;
import trimber.math.Vector3D;

public class Plane extends Object {
    public Plane(float length, float width) {
        super(tempName(length, width), new Vector3D(0,0,0), new Vector3D(0,0,0), 1);
        color = Color.WHITE;
        texture = new Texture();
        hitbox = new Hitbox(position.x - width / 2.0f, position.y, position.z- length / 2.0f, position.x + width / 2.0f, position.y, position.z -length / 2.0f);
    }

    private static Mesh tempName(float length, float width){
        Mesh planeMesh = new Mesh();
        float halfL = length / 2.0f;
        float halfW = width / 2.0f;
        Vector3D topRight = new Vector3D( halfL, 0,  halfW);
        topRight.u = 1.0f;
        topRight.v = 0.0f;
        Vector3D bottomRight = new Vector3D( halfL, 0, -halfW, 1.0f, 1.0f);
        bottomRight.u = 1.0f; bottomRight.v = 1.0f;
        Vector3D bottomLeft = new Vector3D(-halfL, 0, -halfW);
        bottomLeft.u = 0.0f;
        bottomLeft.v = 1.0f;
        Vector3D topLeft = new Vector3D(-halfL, 0,  halfW);
        topLeft.u = 0.0f;
        topLeft.v = 0.0f;


        planeMesh.triangles.add(new Triangle3D(topRight, bottomRight, bottomLeft));
        planeMesh.triangles.add(new Triangle3D(bottomLeft, topLeft, topRight));

        planeMesh.triangles.add(new Triangle3D(bottomLeft, bottomRight, topRight));
        planeMesh.triangles.add(new Triangle3D(topRight, topLeft, bottomLeft));
        return planeMesh;
    }

}
