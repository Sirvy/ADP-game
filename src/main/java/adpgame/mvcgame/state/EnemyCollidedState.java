package adpgame.mvcgame.state;

import adpgame.mvcgame.config.MvcGameConfig;
import javafx.scene.image.Image;


public class EnemyCollidedState implements IEnemyState {


    private static EnemyCollidedState INSTANCE;

    private EnemyCollidedState() {}


    public static EnemyCollidedState getInstance() {
        if (INSTANCE == null) {
             INSTANCE = new EnemyCollidedState();
        }
        return INSTANCE;
    }

    private Image image;

    @Override
    public Image getStateImage() {
        if (this.image == null) {
            this.image = new Image(MvcGameConfig.ENEMY_COLLISION_IMAGE_RESOURCE);
        }
        return this.image;
    }


    @Override
    public IEnemyState nextState() {
        return EnemyDeadState.getInstance();
    }


    @Override
    public int getSpeed() {
        return 1;
    }
}
