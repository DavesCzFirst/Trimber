package trimber.entity;

import trimber.entity.attributes.Attribute;
import trimber.math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    public int id;
    public String name;
    public Vector3D position = new Vector3D();
    public Vector3D rotation = new Vector3D(0,0,0);
    public Vector3D scale = new Vector3D(1.0f, 1.0f, 1.0f);

    private List<Attribute> attributes = new ArrayList<>();

    public void addAttribute(Attribute a) {
        if (this.getAttribute(a.getClass()) != null) {
            return;
        }

        this.attributes.add(a);
        a.entity = this;
        a.onEntityLoad();
    }

    public <T extends Attribute> T getAttribute(Class<T> attributeClass) {
        for (Attribute a : attributes) {
            if (attributeClass.isInstance(a)) {
                return attributeClass.cast(a);
            }
        }
        return null;
    }

    public <T> List<T> getAllAttributesByInterface(Class<T> interfaceClass) {
        List<T> found = new ArrayList<>();

        for (Attribute a : attributes) {
            if (interfaceClass.isInstance(a)) {
                found.add(interfaceClass.cast(a));
            }
        }
        return found;
    }
}
