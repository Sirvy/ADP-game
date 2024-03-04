package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class ToggleShootingModeCommand extends AbstractGameCommand {

    public ToggleShootingModeCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.toggleShootingMode();
    }
}
