package trimber;

import trimber.entity.CubeEntity;

public class EntityLoader {
    public void loadObjects(Game game){
        /*this.objects.add(new OldCube(new Vector3D(10f,10f,10f) , Color.randomColor()));
        this.objects.add(new OldCube(new Vector3D(-10f,10f,10f) , Color.randomColor()));
        this.objects.add(new OldCube(new Vector3D(1f,0f,1f), Color.RED));
        this.objects.add(new OldCube(new Vector3D(1f,1f,1f), Color.BLUE));
        this.objects.add(new OldCube(new Vector3D(0f,1f,100f), Color.BLUE));
        this.objects.add(new Plane(10,10));*/
        game.addEntity(new CubeEntity());
    }
}
