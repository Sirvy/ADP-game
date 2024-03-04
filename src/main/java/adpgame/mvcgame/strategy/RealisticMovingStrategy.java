package adpgame.mvcgame.strategy;

import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.Vector;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;


public class RealisticMovingStrategy implements IMovingStrategy {

    @Override
    public String getName() {
        return "Realistic";
    }


    @Override
    public void updatePosition(AbstractMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        long time = missile.getAge() / 100;
        int dX = (int) (initVelocity * time * Math.cos(initAngle));
        int dY = (int) (initVelocity * time * Math.sin(initAngle) + (0.5 * MvcGameConfig.GRAVITY * time * time));
        missile.move(new Vector(dX, dY));

    }
}
