package trimber.graphics;

import java.util.Random;

public class Color {


    public static final int BLUE = (255 << 24) | (0 << 16) | (0 << 8) | 255;
    public static final int RED = (255 << 24) | (255 << 16) | (0 << 8) | 0;
    public static final int GREEN = (255 << 24) | (0 << 16) | (255 << 8) | 0;
    public static final int BLACK= (255 << 24) | (0 << 16) | (0 << 8) | 0;
    public static final int WHITE= (255 << 24) | (255 << 16) | (255 << 8) | 255;

    int r;
    int b;
    int g;
    int a = 255;
    int color;


    public Color(int r, int b, int g) {
        this.r = r;
        this.b = b;
        this.g = g;
    }


    public static int randomColor(){
        Random r = new Random();
        return r.nextInt();
    }

    @Override
    public String toString() {
        return "Color{}";
    }
}
