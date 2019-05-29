package kurtis.sq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{
    
    private Handler handler;

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = 5;
        velY = 5;
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
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
        
        
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 16, 16);
    }
    
}
