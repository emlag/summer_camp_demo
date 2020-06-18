package edu.cis.demo.Utils;

import edu.cis.demo.Hud;

public class Utils
{
    public static void addScore(int score, Hud hud)
    {
        int currentScore = hud.getScore();
        int newScore = currentScore + score;
        hud.setScore(newScore);
    }
}
