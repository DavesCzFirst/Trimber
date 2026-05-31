package trimber.entity;

import trimber.entity.attributes.HitboxAttribute;
import trimber.entity.attributes.TextureAttribute;
import trimber.graphics.Color;
import trimber.math.Mesh;
import trimber.math.Triangle3D;
import trimber.math.Vector3D;

import java.awt.*;

public class CubeEntity extends Entity {

    public CubeEntity() {
        addAttribute(new HitboxAttribute(1.0f, 1.0f, 1.0f ));
        addAttribute(new TextureAttribute(cubeMeshCreator(1.0f), Color.WHITE));

    }

    private static Mesh cubeMeshCreator(float size){
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
}
