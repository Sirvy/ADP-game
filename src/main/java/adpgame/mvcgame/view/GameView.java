package adpgame.mvcgame.view;

import adpgame.mvcgame.bridge.gameGraphics.IGameGraphics;
import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.controller.GameController;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.observer.AspectEnum;
import adpgame.mvcgame.observer.IObserver;
import javafx.scene.image.Image;


public class GameView implements IObserver {

    private IGameModel model;
    private final GameController controller;
    private IGameGraphics gameGraphics;
    private final GameObjectsRenderer gameObjectsRenderer;

    private final Image background = new Image(MvcGameConfig.BACKGROUND_IMAGE_RESOURCE);

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.model.registerObserver(this, AspectEnum.ASPECT_ALL);
        this.gameObjectsRenderer = new GameObjectsRenderer();
    }

    public void setModel(IGameModel model) {
        this.model = model;
    }

    public GameController getController() {
        return this.controller;
    }

    public void renderGameOver(int score) {
        this.gameGraphics.clear();
        this.gameGraphics.drawText("Game Over! (Score: " + score + ")", new Position(MvcGameConfig.MAX_X / 2 - 30, MvcGameConfig.MAX_Y / 2));
    }

    private void render() {
        // Clear the canvas
        this.gameGraphics.clear();
        this.gameGraphics.drawExistingImage(this.background, new Position(0, 0));
        this.model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(this.gameObjectsRenderer));
    }

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
        this.gameObjectsRenderer.setGraphicsContext(gameGraphics);
        this.render();
    }

    @Override
    public void update() {
        this.render();
    }
}
