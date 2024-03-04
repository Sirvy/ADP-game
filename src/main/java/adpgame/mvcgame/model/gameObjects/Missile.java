package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.strategy.IMovingStrategy;


public class Missile extends AbstractMissile {

    private final Position previousPosition;
    private final IMovingStrategy movingStrategy;

    public Missile(Position position, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(position, initAngle, initVelocity);
        this.movingStrategy = movingStrategy;
        this.previousPosition = new Position(position.getX(), position.getY());
    }

    public Position getPreviousPosition() {
        return this.previousPosition;
    }

    @Override
    public void move() {
        this.previousPosition.setX(position.getX());
        this.previousPosition.setY(position.getY());
        this.movingStrategy.updatePosition(this);
    }

}
