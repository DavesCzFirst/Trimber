package trimber.entity.attributes;

import trimber.graphics.Texture;
import trimber.math.Mesh;

public class TextureAttribute extends Attribute {
    public Mesh mesh;
    public Texture texture;
    public boolean hasTexture;
    public int color;

    public TextureAttribute(Mesh mesh, Texture texture) {
        this.mesh = mesh;
        this.texture = texture;
        hasTexture = true;
    }

    public TextureAttribute(Mesh mesh, int color) {
        this.mesh = mesh;
        this.color = color;
        hasTexture = false;
    }
}
