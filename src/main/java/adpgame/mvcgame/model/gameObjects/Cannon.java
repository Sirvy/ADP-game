package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.model.Vector;
import adpgame.mvcgame.state.DoubleShootingMode;
import adpgame.mvcgame.state.DynamicShootingMode;
import adpgame.mvcgame.state.SingleShootingMode;
import adpgame.mvcgame.abstractfactory.IGameObjectsFactory;

import java.util.ArrayList;
import java.util.List;


public class Cannon extends AbstractCanon {

    private final IGameObjectsFactory gameObjectsFactory;
    private final List<AbstractMissile> shootingBatch;


    public Cannon(Position initPosition, IGameObjectsFactory gameObjectsFactory) {
        super(initPosition);
        this.gameObjectsFactory = gameObjectsFactory;
        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;

        this.shootingBatch = new ArrayList<>();
        this.shootingMode = SINGLE_SHOOTING_MODE;
    }


    @Override
    public void primitiveShoot() {
        this.shootingBatch.add(this.gameObjectsFactory.createMissile(this.angle, this.power));
    }

    public List<AbstractMissile> shoot() {
        this.shootingBatch.clear();
        this.shootingMode.shoot(this);
        return this.shootingBatch;
    }

    @Override
    public void toggleShootingMode() {
        if (this.shootingMode instanceof SingleShootingMode) {
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        }
        else if (this.shootingMode instanceof DoubleShootingMode) {
            this.shootingMode = DYNAMIC_SHOOTING_MODE;
        }
        else if (this.shootingMode instanceof DynamicShootingMode) {
            this.shootingMode = SINGLE_SHOOTING_MODE;
        }
    }


    @Override
    public void moveUp() {
        if (this.position.getY() <= 32) {
            return;
        }
        this.move(new Vector(0, -MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        if (this.position.getY() >= MvcGameConfig.MAX_Y - 64) {
            return;
        }
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void aimUp() {
        this.angle -= MvcGameConfig.ANGLE_STEP;
    }
    @Override
    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;
    }
    @Override
    public void powerUp() {
        this.power = Math.min(MvcGameConfig.MAX_POWER, this.power + MvcGameConfig.POWER_STEP);
    }
    @Override
    public void powerDown() {
        this.power = Math.max(MvcGameConfig.MIN_POWER, this.power - MvcGameConfig.POWER_STEP);
    }

    @Override
    public void increaseMissiles() {
        if (this.shootingMode instanceof DynamicShootingMode) {
            ((DynamicShootingMode) this.shootingMode).increaseMissiles();
        }
    }

    @Override
    public void decreaseMissiles() {
        if (this.shootingMode instanceof DynamicShootingMode) {
            ((DynamicShootingMode) this.shootingMode).decreaseMissiles();
        }
    }
}
