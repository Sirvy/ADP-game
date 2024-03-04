package adpgame.mvcgame.model.utils;

public interface IEnemyCreationLimit {
    boolean canPerform(int difficulty);
    void updateLimit();
}
