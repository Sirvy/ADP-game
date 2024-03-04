package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;


public class Enemy extends AbstractEnemy {

    public Enemy(Position position) {
        super(position);
    }


    @Override
    public void move() {
        this.position.setX(this.position.getX() - this.getState().getSpeed());
    }

}
