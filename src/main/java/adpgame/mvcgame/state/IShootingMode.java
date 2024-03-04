package adpgame.mvcgame.state;

import adpgame.mvcgame.model.gameObjects.AbstractCanon;


public interface IShootingMode {
    String getName();
    void shoot(AbstractCanon cannon);
}
