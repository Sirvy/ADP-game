package adpgame.mvcgame.state;

import adpgame.mvcgame.model.gameObjects.AbstractCanon;


public class SingleShootingMode implements IShootingMode {

    @Override
    public String getName() {
        return SingleShootingMode.class.getSimpleName();
    }
    @Override
    public void shoot(AbstractCanon cannon) {
        cannon.primitiveShoot();
    }

}
