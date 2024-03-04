package adpgame.mvcgame.state;

import adpgame.mvcgame.model.gameObjects.AbstractCanon;


public class DoubleShootingMode implements IShootingMode {
    @Override
    public String getName() {
        return DoubleShootingMode.class.getSimpleName();
    }
    @Override
    public void shoot(AbstractCanon cannon) {
        cannon.aimUp();
        cannon.primitiveShoot();
        cannon.aimDown();
        cannon.aimDown();
        cannon.primitiveShoot();
        cannon.aimUp();
    }
}
