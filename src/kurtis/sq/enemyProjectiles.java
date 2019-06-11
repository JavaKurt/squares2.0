package src.kurtis.sq;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class enemyProjectiles extends GameObject{
    
    private Handler handler;
    Random r = new Random();

    public enemyProjectiles(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = (r.nextInt(5 - -5) + -5);
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
        //if(y <= 0 || y >= Game.HEIGHT -45) velY *= -1;
       // if(x <= 0 || x >= Game.WIDTH -16) velX *= -1;
       
       if(y >= Game.HEIGHT) handler.removeObject(this);
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
        
        
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);
    }
    
}
