package src.kurtis.sq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Player extends GameObject{
    
    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);  
        this.handler = handler;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }
   

    public void tick() {
        x += velX;
        y += velY;
        
        x = Game.clamp((int) x, 0, Game.WIDTH - 38);
        y = Game.clamp((int) y, 0, Game.HEIGHT - 67);
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.blue, 32, 32, 0.09f, handler));
        
        collision();
        //gameOver();
    }
    
    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.fastEnemy || tempObject.getId() == ID.SmartEnemy){ //tempobject is now enemy
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                    //AudioPlayer.getSound("hit_music").play();
                    }
                }
            else if(tempObject.getId() == ID.HorizontalEnemy || tempObject.getId() == ID.HorizontalEnemyR) {
            	if(getBounds().intersects(tempObject.getBounds())) {
            		HUD.HEALTH -= 2;
            		//AudioPlayer.getSound("hit_music").play();
            	}
            }
                
            }
        }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, 32 ,32);
    }


}
