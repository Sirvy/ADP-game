package adpgame.mvcgame.strategy;

import adpgame.mvcgame.model.Vector;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;


public class SimpleMovingStrategy implements IMovingStrategy {

    @Override
    public String getName() {
        return "Simple";
    }


    @Override
    public void updatePosition(AbstractMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        long time = missile.getAge() / 100;
        int dX = (int) (initVelocity * time * Math.cos(initAngle));
        int dY = (int) (initVelocity * time * Math.sin(initAngle));
        missile.move(new Vector(dX, dY));
    }
}
