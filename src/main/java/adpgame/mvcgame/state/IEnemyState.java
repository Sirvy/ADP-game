package adpgame.mvcgame.state;

import javafx.scene.image.Image;


public interface IEnemyState {
    Image getStateImage();

    IEnemyState nextState();

    int getSpeed();
}
