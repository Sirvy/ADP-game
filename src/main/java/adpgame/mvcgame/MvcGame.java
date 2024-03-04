package adpgame.mvcgame;

import java.util.List;

import adpgame.mvcgame.bridge.gameGraphics.IGameGraphics;
import adpgame.mvcgame.model.NullGameModel;
import adpgame.mvcgame.proxy.GameModelProxy;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.controller.GameController;
import adpgame.mvcgame.memento.CareTaker;
import adpgame.mvcgame.model.GameModel;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.observer.AspectEnum;
import adpgame.mvcgame.observer.IObserver;
import adpgame.mvcgame.view.GameView;

public class MvcGame implements IObserver {


    private IGameModel model;
    private GameView view;
    private GameController controller;

    public void init() {
        this.model = new GameModelProxy(new GameModel());
        this.model.registerObserver(this, AspectEnum.ASPECT_GAME_OVER);
        this.view = new GameView(model);
        this.controller = this.view.getController();
        CareTaker.getInstance().setModel(this.model);
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        this.controller.processPressedKeys(pressedKeysCodes);
    }

    public String getWindowTitle() {
        return MvcGameConfig.GAME_TITLE;
    }

    public int getWindowWidth() {
        return MvcGameConfig.MAX_X;
    }

    public int getWindowHeight() {
        return  MvcGameConfig.MAX_Y;
    }

    public void setGraphicsContext(IGameGraphics gr) {
        this.view.setGraphicsContext(gr);
    }


    // Game Over notification received
    @Override
    public void update() {
        this.view.renderGameOver(this.model.getHighScore());
        this.model = new NullGameModel();
        this.view.setModel(this.model);
        this.controller.setModel(this.model);
    }
}