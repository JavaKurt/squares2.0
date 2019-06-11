package src.kurtis.sq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class enemyFirstBoss extends GameObject{
    
    private Handler handler;
    Random r = new Random();
    private HUD hud;
    
    private int timer = 80;
    private int timer2 = 150;
    

    public enemyFirstBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = 0;
        velY = 2;
    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 96, 96);
    }

    public void tick() {
        x += velX;
        y += velY;
        
        if(timer <= 0) velY = 0;
        else timer--;
        
        if(timer <= 0) timer2--;
        if(timer2 <=0){
            if(velX == 0) velX = 5;
            velX += 0.01f*Math.signum(velX);
            int spawn = r.nextInt(10);
            if(spawn == 0) handler.addObject(new enemyProjectiles((int) x+48, (int)y+48, ID.Enemy, handler));
            
        }
       
        //reversing the velocity y
        // z = -5 * -1 = 5   -- moves up   --
        // if z = 5 *-1 = -5 -- moves down --
        //if(y <= 0 || y >= Game.HEIGHT -45) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH -48) velX *= -1;
      
       // handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 64, 64, 0.05f, handler));
        
        
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 110, 110);
       

    }
    
}

