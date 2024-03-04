package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class MoveCannonDownCommand extends AbstractGameCommand {

    public MoveCannonDownCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.moveCannonDown();
    }
}
