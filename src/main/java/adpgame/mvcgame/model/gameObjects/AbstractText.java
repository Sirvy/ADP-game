package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;


public class AbstractText extends GameObject {

    protected String text;

    public AbstractText(Position position) {
        super(position);
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitInfoPanel(this);
    }
}
