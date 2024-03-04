package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class AimCannonUpCommand extends AbstractGameCommand {

    public AimCannonUpCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.aimCannonUp();
    }
}
