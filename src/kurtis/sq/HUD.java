package kurtis.sq;

import java.awt.Color;
import java.awt.Graphics;


public class HUD {
    
	public int bounds = 0;
    public static float HEALTH = 100;
    
    private float greenValue = 255;
    

    private int score = 0;
    private int level = 1;
    
    public void tick(){
        //clamp prevents health from going past a certain point
        HEALTH = (int) Game.clamp(HEALTH, 0, 100 + bounds);
        
        //clamps the green value from going past 
        greenValue = HEALTH * 2;
        greenValue = (int) Game.clamp(greenValue, 0, 255);
        
        

            score++; 
    }
    
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 + bounds, 32);
        g.setColor(new Color(75, (int)greenValue, 0));
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200 + bounds, 32);
        
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
            
            if(HUD.HEALTH == 0){
                g.drawString("Highscore: " + score, Game.WIDTH/2 - 50, Game.HEIGHT/2 + 50);
                g.drawString("GAME OVER", Game.WIDTH/2 - 50, Game.HEIGHT/2);
                
            }
        g.drawString("Space for the shop",15, 94);
    }
    
    public void setScore(int score){
        this.score=score;
    }
    public int getScore(){
        return score;
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public int getLevel(){
        return level;
    }

}

