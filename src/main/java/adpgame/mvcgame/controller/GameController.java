package adpgame.mvcgame.controller;

import adpgame.mvcgame.command.AimCannonDownCommand;
import adpgame.mvcgame.command.DecreaseMissilesCommand;
import adpgame.mvcgame.command.ExitGameCommand;
import adpgame.mvcgame.command.MoveCannonDownCommand;
import adpgame.mvcgame.command.MoveCannonUpCommand;
import adpgame.mvcgame.command.PowerCannonDownCommand;
import adpgame.mvcgame.command.ToggleMovingStrategyCommand;
import adpgame.mvcgame.command.ToggleShootingModeCommand;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.command.AimCannonUpCommand;
import adpgame.mvcgame.command.IncreaseMissilesCommand;
import adpgame.mvcgame.command.PowerCannonUpCommand;

import java.util.List;

public class GameController {

    private IGameModel model;
    public GameController(IGameModel model) {
        this.model = model;
    }

    public void setModel(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameConfig.UP_KEY:
                    this.model.registerCommand(new MoveCannonUpCommand(this.model));
                    break;
                case MvcGameConfig.DOWN_KEY:
                    this.model.registerCommand(new MoveCannonDownCommand(this.model));
                    break;
                case MvcGameConfig.EXIT_KEY:
                    this.model.registerCommand(new ExitGameCommand());
                    break;
                case MvcGameConfig.SHOOT_KEY:
                    this.model.cannonShoot();
                    break;
                case MvcGameConfig.AIM_UP_KEY:
                    this.model.registerCommand(new AimCannonUpCommand(this.model));
                    break;
                case MvcGameConfig.AIM_DOWN_KEY:
                    this.model.registerCommand(new AimCannonDownCommand(this.model));
                    break;
                case MvcGameConfig.POWER_UP_KEY:
                    this.model.registerCommand(new PowerCannonUpCommand(this.model));
                    break;
                case MvcGameConfig.POWER_DOWN_KEY:
                    this.model.registerCommand(new PowerCannonDownCommand(this.model));
                    break;
                case MvcGameConfig.MOVING_STRATEGY_KEY:
                    this.model.registerCommand(new ToggleMovingStrategyCommand(this.model));
                    break;
                case MvcGameConfig.SHOOTING_MODE_KEY:
                    this.model.registerCommand(new ToggleShootingModeCommand(this.model));
                    break;
                case MvcGameConfig.UNDO_LAST_COMMAND_KEY:
                    this.model.undoLastCommand();
                    break;
                case MvcGameConfig.MORE_MISSILES:
                    this.model.registerCommand(new IncreaseMissilesCommand(this.model));
                    break;
                case MvcGameConfig.LESS_MISSILES:
                    this.model.registerCommand(new DecreaseMissilesCommand(this.model));
                    break;

                default:
                    //nothing
            }
        }
        this.model.update();
        pressedKeysCodes.clear();
    }
}
