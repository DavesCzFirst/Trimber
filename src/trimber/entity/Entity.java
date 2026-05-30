package trimber.entity;

import trimber.entity.attributes.Attribute;
import trimber.math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    public int id;
    public String name;
    public Vector3D position = new Vector3D();
    public Vector3D rotation;
    public Vector3D scale = new Vector3D(1.0f, 1.0f, 1.0f);

    private List<Attribute> attributes = new ArrayList<>();

    public void addAttribute(Attribute a) {
        this.attributes.add(a);
        a.entity = this;
    }

    public <T extends Attribute> T getAttribute(Class<T> attributeClass) {
        for (Attribute a : attributes) {
            if (attributeClass.isInstance(a)) {
                return attributeClass.cast(a);
            }
        }
        return null;
    }
}
