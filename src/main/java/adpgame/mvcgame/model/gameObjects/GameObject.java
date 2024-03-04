package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.model.Vector;
import adpgame.mvcgame.visitor.IVisitable;


public abstract class GameObject implements IVisitable {

    protected Position position;


    public GameObject(Position position) {
        this.position = position;
    }

    public void move(Vector vector){
        this.position.add(vector);
    }

    public Position getPosition() {
        return this.position;
    }
}
