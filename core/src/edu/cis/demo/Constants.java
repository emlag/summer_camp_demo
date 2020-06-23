package edu.cis.demo;


public class Constants
{
    public static final int WIDTH = 20 * 16;
    public static final int HEIGHT = 11 * 16;
    public static final String MAP_FILENAME = "level1.tmx";

    //atlas name
    public static final String ATLAS_FILENAME = "sprites.atlas";
    //sprite names
    public static final String LITTLE_MARIO_STRING = "little_mario";
    public static final String GOOMBA_STRING = "goomba";


    public static final int GROUND_LAYER = 2;
    public static final int PIPES_LAYER = 3;
    public static final int COINS_LAYER = 4;
    public static final int BRICKS_LAYER = 5;

    public static final int NOTHING_BIT = 0;

    //movement animation sprites
    public static final int RUN_ANIM_STRT = 1;
    public static final int RUN_ANIM_END = 4;
    public static final int JUMP_ANIM_STRT = 4;
    public static final int JUMP_ANIM_END = 6;
    public static final int GOOMBA_ANIM_STRT = 0;
    public static final int GOOMBA_ANIM_END = 2;

    //collision
    public static final Integer PLAYER_ID = 99;
    public static final Integer GOOMBA_ID = 98;

    //movement strings
    public static final String UP = "up";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
}
