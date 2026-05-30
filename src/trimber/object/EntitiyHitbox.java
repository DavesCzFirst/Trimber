package trimber.object;

public class EntitiyHitbox extends Hitbox {
    public EntitiyHitbox(float width, float height, float length) {
        super(-width/2, 0f, -length/2, width/2, height, length/2);
    }
}
