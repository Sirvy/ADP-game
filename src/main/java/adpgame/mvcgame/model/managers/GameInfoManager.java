package adpgame.mvcgame.model.managers;

import adpgame.mvcgame.model.gameObjects.ControlInfoText;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.model.gameObjects.GameObject;
import adpgame.mvcgame.model.gameObjects.GameStatus;
import adpgame.mvcgame.model.gameObjects.PanelInfoText;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameInfoManager extends AbstractManager {

    private GameStatus gameStatus;
    private ControlInfoText controlInfoText;
    private PanelInfoText panelInfoText;


    public GameInfoManager(IGameModel mediator) {
        super(mediator);
        this.gameStatus = new GameStatus(new Position(450, 50));
        this.controlInfoText = new ControlInfoText(new Position(50, 50));
        this.panelInfoText = new PanelInfoText(new Position(250, 50));
    }

    public void update(String power, double aimAngle, String movingStrategy, String shootingMode) {
        this.panelInfoText.updatePanel(power, aimAngle, movingStrategy, shootingMode);
    }

    public List<GameObject> getGameObjects() {
        return Stream.of(
                Stream.of(this.controlInfoText),
                Stream.of(this.panelInfoText),
                Stream.of(this.gameStatus)
        ).flatMap(i -> i).collect(Collectors.toList());
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public void increaseScore(int amount) {
        this.gameStatus.setScore(this.gameStatus.getScore() + amount);
    }

    public void increaseDifficulty() {
        this.gameStatus.setDifficulty(this.gameStatus.getDifficulty() + 1);
    }

    public void reduceHP() {
        this.gameStatus.setHP(this.gameStatus.getHP() - 1);
    }

    public boolean isOutOfHP() {
        return this.gameStatus.getHP() <= 0;
    }
}
