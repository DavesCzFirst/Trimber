package trimber;

import trimber.input.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private class ClickableRect{float x1; float x2; float y1; float y2; State state; int bGColor; String name;}
    private ClickableRect playRect = new ClickableRect();
    private List<ClickableRect> rects = new ArrayList<>();

    public Menu() {

        playRect.x1 = Display.HEIGHT; playRect.x2 = 0;

    }



    private boolean isIn(InputHandler input, ClickableRect rect){
        return (rect.x1>= input.currentMousePos.x && rect.x2<= input.currentMousePos.x &&
                rect.y1>= input.currentMousePos.y && rect.y2<= input.currentMousePos.y);
    }

    private void drawUI(){

    }


    public State onMouseClick(InputHandler input) {


        for(ClickableRect rect: rects){
            if(isIn(input, rect)){
                return rect.state;
            }
        }
        return State.MENU;


    }
}
