package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class MoveCannonUpCommand extends AbstractGameCommand {

    public MoveCannonUpCommand(IGameModel model) {
        this.subject = model;
    }


    @Override
    protected void execute() {
        this.subject.moveCannonUp();
    }
}
