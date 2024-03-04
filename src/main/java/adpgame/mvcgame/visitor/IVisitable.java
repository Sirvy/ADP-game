package adpgame.mvcgame.visitor;

public interface IVisitable {
    void acceptVisitor(IGameObjectsVisitor visitor);
}
