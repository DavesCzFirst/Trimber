package trimber.graphics;

import trimber.State;

import java.awt.*;
import java.awt.Color;

public class UIButton {
    int x; int y; int width; int height;
    String text; Color bGColor; public State state;

    public UIButton(int x, int y, int width, int height, String text, int bGColor, State state) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = state;
        this.bGColor = new Color(bGColor, true);
        this.text = text;
    }
    
    public boolean intersect(Point a){
        return (a.x >= this.x && a.x <= (this.x + this.width) &&
                a.y >= this.y && a.y <= (this.y + this.height));
    }

    public void draw(Graphics g) {

        g.setColor(bGColor);
        g.fillRect(x, y, width, height);
        g.setColor(java.awt.Color.WHITE);
        g.drawString(this.text, x + 10, y + 30); // Rough text centering
    }
}
