package trimber.entity.entities;

import trimber.entity.Entity;
import trimber.entity.attributes.ClickAttribute;
import trimber.entity.attributes.HitboxAttribute;
import trimber.entity.attributes.TextureAttribute;
import trimber.graphics.Color;
import trimber.math.Mesh;
import trimber.math.Triangle3D;
import trimber.math.Vector3D;
import trimber.object.hitboxes.BlockHitbox;
import trimber.object.hitboxes.EntitiyHitbox;

public class CubeEntity extends Entity {

    public CubeEntity() {
        super(new Vector3D(0,0,2f));
        addAttribute(new HitboxAttribute(new BlockHitbox(1.0f, 1.0f, 1.0f)));
        addAttribute(new TextureAttribute(cubeMeshCreator(1.0f), Color.WHITE));
        addAttribute(new ClickAttribute(new BlockHitbox(1.0f, 1.0f, 1.0f)));

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
