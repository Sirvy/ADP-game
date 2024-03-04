package adpgame.mvcgame.strategy;

import adpgame.mvcgame.model.Vector;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;


public class BirdLikeMovingStrategy implements IMovingStrategy {

    @Override
    public String getName() {
        return "Bird-like";
    }


    @Override
    public void updatePosition(AbstractMissile missile) {
        long time = missile.getAge() / 100;
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();

        int dX = (int) (initVelocity + time * Math.cos(initAngle + time));
        int dY = (int) (time * Math.sin(initAngle + time));

        missile.move(new Vector(dX, dY));
    }
}
