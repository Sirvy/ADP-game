package adpgame;

import adpgame.mvcgame.bridge.gameGraphics.GameGraphics;
import adpgame.mvcgame.bridge.gameGraphics.IGameGraphics;
import adpgame.mvcgame.bridge.gameGraphics.JavaFxGameGraphics;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.util.ArrayList;

import adpgame.mvcgame.MvcGame;

public class MvcGameJavaFxLauncher extends Application {

    private static final MvcGame theMvcGame = new MvcGame();

    @Override
    public void init() {
        theMvcGame.init();
    }

    @Override
    public void start(Stage stage) {
        String winTitle = theMvcGame.getWindowTitle();
        int winWidth = theMvcGame.getWindowWidth();
        int winHeigth = theMvcGame.getWindowHeight();
        stage.setTitle( winTitle );
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        Canvas canvas = new Canvas( winWidth, winHeigth );
        root.getChildren().add( canvas );
        IGameGraphics gameGraphics = new GameGraphics(new JavaFxGameGraphics(canvas.getGraphicsContext2D()));
        ArrayList<String> pressedKeysCodes = new ArrayList<>();
        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    // only add once... prevent duplicates
                    if (!pressedKeysCodes.contains(code))
                        pressedKeysCodes.add(code);
                }
        );
        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    pressedKeysCodes.remove( code );
                }
        );
        // the game-loop
        theMvcGame.setGraphicsContext(gameGraphics);
        new AnimationTimer() {
            private long lastUpdate = 0 ;
            public void handle(long currentNanoTime) {
                if (currentNanoTime - lastUpdate >= 33_000_000) {
                    theMvcGame.processPressedKeys(pressedKeysCodes);
                    lastUpdate = currentNanoTime ;
                }
            }
        }.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}