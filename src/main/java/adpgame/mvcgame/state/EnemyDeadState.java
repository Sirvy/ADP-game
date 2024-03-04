package adpgame.mvcgame.state;

import adpgame.mvcgame.config.MvcGameConfig;
import javafx.scene.image.Image;


public class EnemyDeadState implements IEnemyState {


    private static final EnemyDeadState INSTANCE = new EnemyDeadState();

    private EnemyDeadState() {}


    public static EnemyDeadState getInstance() {
        return INSTANCE;
    }

    private Image image;

    @Override
    public Image getStateImage() {
        if (this.image == null) {
            this.image = new Image(MvcGameConfig.ENEMY_DEAD_IMAGE_RESOURCE);
        }
        return this.image;
    }


    @Override
    public IEnemyState nextState() {
        return null;
    }


    @Override
    public int getSpeed() {
        return 0;
    }
}
