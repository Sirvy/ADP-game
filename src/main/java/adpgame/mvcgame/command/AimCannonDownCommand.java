package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class AimCannonDownCommand extends AbstractGameCommand {

    public AimCannonDownCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.aimCannonDown();
    }
}
