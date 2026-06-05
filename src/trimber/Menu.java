package trimber;

import trimber.graphics.Color;
import trimber.graphics.UIButton;
import trimber.input.InputHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private UIButton playRect = new UIButton(Display.WIDTH/4, Display.HEIGHT/4, Display.WIDTH/2, Display.HEIGHT/2, "play", Color.GREEN, State.GAME );

    private List<UIButton> rects = new ArrayList<>();

    public Menu() {
        rects.add(playRect);


    }





    public void drawUI(Graphics g){
        for(UIButton b: rects){
            b.draw(g);
        }
    }


    public State onMouseClick(InputHandler input) {


        for(UIButton rect: rects){
            if(rect.intersect(input.currentMousePos)){
                return rect.state;
            }
        }
        return State.MENU;


    }
}
