package adpgame.mvcgame.view;

import adpgame.mvcgame.bridge.gameGraphics.IGameGraphics;
import adpgame.mvcgame.model.gameObjects.AbstractText;
import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.model.gameObjects.AbstractCanon;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.GameStatus;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;
import javafx.scene.image.Image;


public class GameObjectsRenderer implements IGameObjectsVisitor {

    private IGameGraphics gameGraphics;
    private Image enemyImage = new Image(MvcGameConfig.ENEMY_IMAGE_RESOURCE);
    private Image missileImage = new Image(MvcGameConfig.MISSILE_IMAGE_RESOURCE);
    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    @Override
    public void visitCannon(AbstractCanon cannon) {
        this.gameGraphics.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannon.getPosition());
        int x = 12 + (int) (80 * Math.cos(cannon.getAngle()));
        int y = 15 + (int) (80 * Math.sin(cannon.getAngle()));
        this.gameGraphics.drawText("+", new Position(cannon.getPosition().getX() + x, cannon.getPosition().getY() + y));
    }


    @Override
    public void visitMissile(AbstractMissile missile) {
        this.gameGraphics.drawExistingImage(this.missileImage, missile.getPosition());
    }


    @Override
    public void visitInfoPanel(AbstractText text) {
        this.gameGraphics.drawText(text.getText(), text.getPosition());
    }

    @Override
    public void visitEnemy(AbstractEnemy enemy) {
        this.gameGraphics.drawExistingImage(enemy.getState().getStateImage(), enemy.getPosition());
    }


    @Override
    public void visitStatus(GameStatus status) {
        this.gameGraphics.drawText(
                "HP: " + status.getHP() + "\n"
                        + "Score: " + status.getScore() + "\n"
                        + "Difficulty: " + status.getDifficulty() + "\n"
            , status.getPosition());
    }

    @Override
    public void visitItem(Item item) {
        this.gameGraphics.drawImage(MvcGameConfig.ITEM_IMAGE_RESOURCE, item.getPosition());
    }
}
