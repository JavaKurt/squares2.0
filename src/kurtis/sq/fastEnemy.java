package src.kurtis.sq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class fastEnemy extends GameObject {
    private Handler handler;

    public fastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = 2;
        velY = 9;
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
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.cyan, 16, 16, 0.05f, handler));
        
        
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
