package kurtis.sq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
    
    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }
        
        //calculate the distance between this object and our player object
        // with the distance, we can set the velx and velx , 
        
        

    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;
        
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY()) * (y-player.getY()));
        
        velX = (float) ((-1.0/distance) * diffX);
        velY = (float) ((-1.0 /distance) * diffY);
        
        
        //reversing the velocity y
        // z = velyx *-1 
        // z = -5 * -1 = 5   -- moves up   --
        // if z = 5 *-1 = -5 -- moves down --
        if(y <= 0 || y >= Game.HEIGHT -45) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH -16) velX *= -1;
        
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.pink, 16, 16, 0.05f, handler));
        
        
    }

    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int)x, (int)y, 16, 16);
    }
    
}
