package adpgame.mvcgame.state;

import adpgame.mvcgame.model.gameObjects.AbstractCanon;


public class DynamicShootingMode implements IShootingMode {

    private int missiles = 3;

    @Override
    public String getName() {
        return DynamicShootingMode.class.getSimpleName();
    }


    @Override
    public void shoot(AbstractCanon cannon) {
        for (int i = 0; i < Math.floor(this.missiles / 2.0); i++) {
            cannon.aimUp();
        }
        for (int i = 0; i < this.missiles; i++) {
            cannon.primitiveShoot();
            cannon.aimDown();
        }
        for (int i = 0; i < Math.ceil(this.missiles / 2.0); i++) {
            cannon.aimUp();
        }
    }

    public void increaseMissiles() {
        if (this.missiles < 19) this.missiles++;
    }

    public void decreaseMissiles() {
        if (this.missiles > 1) this.missiles--;
    }
}
