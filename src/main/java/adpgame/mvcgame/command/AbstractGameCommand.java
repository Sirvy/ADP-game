package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public abstract class AbstractGameCommand {
    protected IGameModel subject;
    private Object memento;
    protected abstract void execute();
    public void doExecute() {
        this.memento = this.subject.createMemento();
        this.execute();
    }
    public void unExecute() {
        this.subject.setMemento(this.memento);
    }

}
