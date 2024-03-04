package adpgame.mvcgame.view;

import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.gameObjects.AbstractCanon;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.AbstractText;
import adpgame.mvcgame.model.gameObjects.GameStatus;
import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;


public class GameSoundPlayer implements IGameObjectsVisitor {

    @Override
    public void visitCannon(AbstractCanon cannon) {
        String resourceUrl = Objects.requireNonNull(this.getClass().getClassLoader().getResource(MvcGameConfig.PEW_SOUND_RESOURCE)).toExternalForm();
        new MediaPlayer(new Media(resourceUrl)).play();
    }


    @Override
    public void visitMissile(AbstractMissile missile) {

    }


    @Override
    public void visitInfoPanel(AbstractText text) {

    }


    @Override
    public void visitEnemy(AbstractEnemy enemy) {

    }


    @Override
    public void visitStatus(GameStatus status) {

    }

    @Override
    public void visitItem(Item item) {

    }
}
