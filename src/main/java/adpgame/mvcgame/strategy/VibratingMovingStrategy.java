package adpgame.mvcgame.strategy;

import adpgame.mvcgame.model.Vector;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;

import java.util.concurrent.ThreadLocalRandom;


public class VibratingMovingStrategy implements IMovingStrategy {

    @Override
    public String getName() {
        return "Vibrating";
    }


    @Override
    public void updatePosition(AbstractMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle() + ThreadLocalRandom.current().nextDouble(-1, 1);
        long time = missile.getAge() / 100;

        int dX = (int) (initVelocity * time * Math.cos(initAngle));
        int dY = (int) (initVelocity * time * Math.sin(initAngle));

        missile.move(new Vector(dX, dY));
    }

}
