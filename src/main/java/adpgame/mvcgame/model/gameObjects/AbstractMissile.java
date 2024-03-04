package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;


public abstract class AbstractMissile extends LifetimeLimitedGameObject {

    private final double initAngle;
    private final int initVelocity;


    public AbstractMissile(Position position, double initAngle, int initVelocity) {
        super(position);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
    }

    public double getInitAngle() {
        return this.initAngle;
    }

    public int getInitVelocity() {
        return this.initVelocity;
    }

    public abstract void move();


    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitMissile(this);
    }
}
