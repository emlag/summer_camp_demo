package edu.cis.demo;


public class Constants
{
    public static final int WIDTH = 20 * 16;
    public static final int HEIGHT = 11 * 16;
    public static final String MAP_FILENAME = "level1.tmx";
    //add this
    public static final String ATLAS_FILENAME = "Mario_and_Enemies.atlas";
    public static final String LITTLE_MARIO_STRING = "little_mario";
    public static final String GOOMBA_STRING = "goomba";


    public static final int GROUND_LAYER = 2;
    public static final int PIPES_LAYER = 3;
    public static final int COINS_LAYER = 4;
    public static final int BRICKS_LAYER = 5;

    //movement animation sprites
    public static final int RUN_ANIM_STRT = 1;
    public static final int RUN_ANIM_END = 4;
    public static final int JUMP_ANIM_STRT = 4;
    public static final int JUMP_ANIM_END = 6;
    public static final int GOOMBA_ANIM_STRT = 0;
    public static final int GOOMBA_ANIM_END = 2;


}
