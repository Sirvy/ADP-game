package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;


public class Item extends GameObject {

    public Item(Position position) {
        super(position);
    }


    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitItem(this);
    }
}
