package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;


public class GameStatus extends GameObject {
    private int HP = MvcGameConfig.INIT_HP;
    private int Score = 0;
    private int difficulty = MvcGameConfig.INIT_DIFFICULTY;


    public GameStatus(Position position) {
        super(position);
    }


    public int getHP() {
        return HP;
    }


    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getScore() {
        return Score;
    }


    public void setScore(int score) {
        Score = score;
    }


    public int getDifficulty() {
        return difficulty;
    }


    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }


    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitStatus(this);
    }
}
