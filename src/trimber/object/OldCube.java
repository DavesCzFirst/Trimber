package trimber.object;

import trimber.Object;
import trimber.graphics.Color;
import trimber.math.Mesh;
import trimber.math.Triangle3D;
import trimber.math.Vector3D;

public class OldCube extends Object{


    public OldCube() {
        super(tempName(1), new Vector3D(0f,0f,0f), new Vector3D(0,0,0), 1);
        this.color = Color.WHITE;

    }

    public OldCube(Vector3D position, int color) {
        super(tempName(1), position, new Vector3D(0,0,0), 1);
        this.color = color;
        hitbox = new Hitbox(position.x, position.y, position.z, position.x + scale, position.y + scale, position.z +scale);
    }

    private static Mesh tempName(int size){
        Mesh cubeMesh = new Mesh();
        
// SOUTH FACE (Front)
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(0.0f * size, 0.0f * size, 0.0f * size), new Vector3D(0.0f * size, 1.0f * size, 0.0f * size), new Vector3D(1.0f * size, 1.0f * size, 0.0f * size)));
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(0.0f * size, 0.0f * size, 0.0f * size), new Vector3D(1.0f * size, 1.0f * size, 0.0f * size), new Vector3D(1.0f * size, 0.0f * size, 0.0f * size)));

// EAST FACE (Right)
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(1.0f * size, 0.0f * size, 0.0f * size), new Vector3D(1.0f * size, 1.0f * size, 0.0f * size), new Vector3D(1.0f * size, 1.0f * size, 1.0f * size)));
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(1.0f * size, 0.0f * size, 0.0f * size), new Vector3D(1.0f * size, 1.0f * size, 1.0f * size), new Vector3D(1.0f * size, 0.0f * size, 1.0f * size)));

// NORTH FACE (Back)
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(1.0f * size, 0.0f * size, 1.0f * size), new Vector3D(1.0f * size, 1.0f * size, 1.0f * size), new Vector3D(0.0f * size, 1.0f * size, 1.0f * size)));
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(1.0f * size, 0.0f * size, 1.0f * size), new Vector3D(0.0f * size, 1.0f * size, 1.0f * size), new Vector3D(0.0f * size, 0.0f * size, 1.0f * size)));

// WEST FACE (Left)
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(0.0f * size, 0.0f * size, 1.0f * size), new Vector3D(0.0f * size, 1.0f * size, 1.0f * size), new Vector3D(0.0f * size, 1.0f * size, 0.0f * size)));
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(0.0f * size, 0.0f * size, 1.0f * size), new Vector3D(0.0f * size, 1.0f * size, 0.0f * size), new Vector3D(0.0f * size, 0.0f * size, 0.0f * size)));

// TOP FACE
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(0.0f * size, 1.0f * size, 0.0f * size), new Vector3D(0.0f * size, 1.0f * size, 1.0f * size), new Vector3D(1.0f * size, 1.0f * size, 1.0f * size)));
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(0.0f * size, 1.0f * size, 0.0f * size), new Vector3D(1.0f * size, 1.0f * size, 1.0f * size), new Vector3D(1.0f * size, 1.0f * size, 0.0f * size)));

// BOTTOM FACE
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(1.0f * size, 0.0f * size, 1.0f * size), new Vector3D(0.0f * size, 0.0f * size, 1.0f * size), new Vector3D(0.0f * size, 0.0f * size, 0.0f * size)));
        cubeMesh.triangles.add(new Triangle3D(new Vector3D(1.0f * size, 0.0f * size, 1.0f * size), new Vector3D(0.0f * size, 0.0f * size, 0.0f * size), new Vector3D(1.0f * size, 0.0f * size, 0.0f * size)));


        return cubeMesh;
    }



    public OldCube(Vector3D position, Mesh mesh) {
        super(position, mesh);
    }
}
