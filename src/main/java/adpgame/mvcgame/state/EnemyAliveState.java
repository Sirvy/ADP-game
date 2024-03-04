package adpgame.mvcgame.state;

import adpgame.mvcgame.config.MvcGameConfig;
import javafx.scene.image.Image;


public class EnemyAliveState implements IEnemyState {

    private static EnemyAliveState INSTANCE;

    private EnemyAliveState() {}


    public static EnemyAliveState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EnemyAliveState();
        }
        return INSTANCE;
    }

    private Image image;

    @Override
    public Image getStateImage() {
        if (this.image == null) {
            this.image = new Image(MvcGameConfig.ENEMY_IMAGE_RESOURCE);
        }
        return this.image;
    }

    @Override
    public IEnemyState nextState() {
        return EnemyCollidedState.getInstance();
    }


    @Override
    public int getSpeed() {
        return 4;
    }
}
