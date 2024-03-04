package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.state.DoubleShootingMode;
import adpgame.mvcgame.state.DynamicShootingMode;
import adpgame.mvcgame.state.IShootingMode;
import adpgame.mvcgame.state.SingleShootingMode;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;

import java.util.List;


public abstract class AbstractCanon extends GameObject {

    protected int power;
    protected double angle;

    protected IShootingMode shootingMode;

    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode();
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode();
    protected static IShootingMode DYNAMIC_SHOOTING_MODE = new DynamicShootingMode();


    public AbstractCanon(Position position) {
        super(position);
    }


    public abstract void primitiveShoot();
    public abstract List<AbstractMissile> shoot();
    public abstract void toggleShootingMode();
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void aimUp();
    public abstract void aimDown();
    public abstract void powerUp();
    public abstract void powerDown();
    public abstract void increaseMissiles();
    public abstract void decreaseMissiles();

    public int getPower() {
        return this.power;
    }
    public double getAngle() {
        return this.angle;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }
    public String getShootingMode() {
        return this.shootingMode.getName();
    }

    public IShootingMode getShootingModeState() {
        return this.shootingMode;
    }

    public void setShootingMode(IShootingMode shootingMode) {
        this.shootingMode = shootingMode;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitCannon(this);
    }
}
