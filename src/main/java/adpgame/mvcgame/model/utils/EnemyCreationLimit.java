package adpgame.mvcgame.model.utils;

import adpgame.mvcgame.config.MvcGameConfig;

import java.util.Date;


public class EnemyCreationLimit implements IEnemyCreationLimit {

    private Date lastEnemy;

    public EnemyCreationLimit() {
        this.lastEnemy = null;
    }


    @Override
    public boolean canPerform(int difficulty) {
        return !(lastEnemy != null && Math.abs(new Date().getTime() - lastEnemy.getTime()) < MvcGameConfig.INTERVAL_BETWEEN_SHOTS * 50 / (1 + Math.ceil(Math.pow(Math.log(difficulty), 2))));
    }


    @Override
    public void updateLimit() {
        this.lastEnemy = new Date();
    }
}
