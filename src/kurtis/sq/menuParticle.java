package src.kurtis.sq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class menuParticle extends GameObject {
    private Handler handler;
    
    Random r = new Random();
    
    private Color col;

    public menuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = 2;
        velY = 9;
        
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        
    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;
        
        //reversing the velocity y
        // z = velyx *-1 
        // z = -5 * -1 = 5   -- moves up   --
        // if z = 5 *-1 = -5 -- moves down --
        if(y <= 0 || y >= Game.HEIGHT -45) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH -16) velX *= -1;
        
        //handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 16, 16, 0.05f, handler));
        
        
    }

    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}

