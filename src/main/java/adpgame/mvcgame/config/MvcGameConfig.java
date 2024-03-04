package adpgame.mvcgame.config;

public class MvcGameConfig {

    public static final int MAX_X = 1280;
    public static final int MAX_Y = 768;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 200;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final int POWER_STEP = 1;
    public static final int INIT_POWER = 10;
    public static final double INIT_ANGLE = 0;
    public static final double GRAVITY = 9.81;
    public static final int INTERVAL_BETWEEN_SHOTS = 500;
    public static final int MAX_POWER = 50;
    public static final int MIN_POWER = 1;

    public static final int INIT_HP = 10;
    public static final int INIT_DIFFICULTY = 1;


    public static final String GAME_TITLE = "The NI-ADP MvcGame";

    public static final String UP_KEY = "UP";
    public static final String DOWN_KEY = "DOWN";
    public static final String EXIT_KEY = "ESCAPE";
    public static final String SHOOT_KEY = "SPACE";
    public static final String AIM_UP_KEY = "A";
    public static final String AIM_DOWN_KEY = "Y";
    public static final String POWER_UP_KEY = "F";
    public static final String POWER_DOWN_KEY = "D";
    public static final String MOVING_STRATEGY_KEY = "M";
    public static final String SHOOTING_MODE_KEY = "N";
    public static final String UNDO_LAST_COMMAND_KEY = "B";

    public static final String MORE_MISSILES = "I";
    public static final String LESS_MISSILES = "O";



    public static final String CANNON_IMAGE_RESOURCE = "images/cannon.png";
    public static final String MISSILE_IMAGE_RESOURCE = "images/missile.png";
    public static final String PEW_SOUND_RESOURCE = "sound/pew.wav";
    public static final String ENEMY_IMAGE_RESOURCE = "images/enemy1.png";
    public static final String ENEMY_COLLISION_IMAGE_RESOURCE = "images/enemy2.png";
    public static final String ENEMY_DEAD_IMAGE_RESOURCE = "images/enemy2WithBlood.png";
    public static final String BACKGROUND_IMAGE_RESOURCE = "images/back.jpg";
    public static final String ITEM_IMAGE_RESOURCE = "images/item.png";

}