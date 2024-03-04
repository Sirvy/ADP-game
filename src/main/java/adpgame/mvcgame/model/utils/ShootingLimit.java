package adpgame.mvcgame.model.utils;

import adpgame.mvcgame.config.MvcGameConfig;

import java.util.Date;


public class ShootingLimit implements IShootingLimit {

    private Date lastShot;


    public ShootingLimit() {
        this.lastShot = new Date();
    }


    @Override
    public boolean canShoot() {
        return !(Math.abs(new Date().getTime() - lastShot.getTime()) < MvcGameConfig.INTERVAL_BETWEEN_SHOTS);
    }


    @Override
    public void updateLimit() {
        this.lastShot = new Date();
    }
}
