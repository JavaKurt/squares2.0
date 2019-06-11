package src.kurtis.sq;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable{
    
    private final static long serialVersionUID = 1550691097823471818L;
    
    public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
    
    private Thread thread;
    private boolean running = false;
    
    public static boolean paused = false;
    public int diff = 0;
    
    // 0 = normal 
    // 1 = hard
    
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawner spawner;
    private menu menu;
    private Shop shop;
    
    public enum STATE {
        Menu,
        Select,
        Help,
        Game,
        Win,
        End, 
        Shop
    };
    
    public static STATE gameState = STATE.Menu;
    
    public Game(){
        
        handler = new Handler();
        hud = new HUD();
        shop = new Shop(handler, hud);
                
        menu = new menu(this, handler, hud);
   
        //listen for keys with .addkeylistener
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);
        
        AudioPlayer.load();
        
        new Window(WIDTH, HEIGHT, "Game2.0!", this);
        
        spawner = new Spawner(handler, hud, this);
        r = new Random();
        
        
        if(gameState == STATE.Game)
        {
            //creates the player object in the centre of the screen
            // width and height /2 and then take off the dimensions of the player object
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
        } 
        if(gameState == STATE.Menu){
            for(int i = 0; i < 5; i++){
                handler.addObject(new menuParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.menuParticle, handler));
                //handler.addObject(new menuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.menuParticle, handler));
            }
        }
       
        
        // ---- HOW WOULD I ADD A POINT SYSTEM/
        // THE LONGER YOU LAST, THE MORE POINTS YOU GET?
        // HUMMMMMMM
       
        //method for multiple enemies and random locations
        //for(int i = 0; i < 20; i ++){
        //    handler.addObject(new Enemy((r.nextInt(WIDTH)), r.nextInt(HEIGHT), ID.Enemy));
        //                               // randomise the width and height
        //}


    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        //method to enable automatic control of the window (DON'T HAVE TO CLICK WINDOW TO START GAME)
        this.requestFocus();
        //long lasttime returns the current value of the most precise available SYSTEM TIMER, in nanoseconds. 
        // create the game loop, including the fps and system timer
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
      
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
            //    System.out.println("FPS: " + frames);
                frames = 0;
        }
        
    }
    stop();
}
    
    private void tick(){
        
        if(gameState == STATE.Game)
        {
        	if(!paused) 
        	{
        		hud.tick();
                spawner.tick();
                handler.tick();
                
                if(HUD.HEALTH <= 0){
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemys();
                }
                else if(hud.getLevel() == 20) {
                	gameState = STATE.Win;
                	handler.clearEnemys();
                }
        		
        	}
        }
            
        else if(gameState == STATE.Menu|| gameState == STATE.End || gameState == STATE.Select) {
            menu.tick();
            handler.tick();
        }

    }
    
    private void render(){
        //create a buffer to reduce fps
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        //create a new instance of graphics, choose a colour, fill rectangle
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        
        if(paused) {
        	g.setColor(Color.white);
        	g.drawString("PAUSED", 100, 100);
        }
        
        if(gameState == STATE.Game){
            //renders all of the objects in the hud class
            hud.render(g);
            handler.render(g);
        } else  if(gameState == STATE.Shop) {
        	shop.render(g);
        }
        	else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Win){
            menu.render(g);
          //renders all of the objects in the handler class
            handler.render(g);
        } 
        
        g.dispose();
        bs.show();
    }
    
        
    public static float clamp(float var, float min, float max){
// if we ever see that var , e.g. "x", is greater than our max value, then return var = max, so you can never go past the max width.
        if( var >= max){
            return var = max;
        }
        else if (var <= min){
            return var = min;
        }
        else{
            return var;
        }
    }
    
    public static void main(String[] args){
        new Game();
    }
    
}

/*
General order of rules:
Create new class for an object on the window...
E.g. HUD 
create a render method for this and fillrect etc.
Next, create a handler for the HUD class 
private HUD hud
create an instance of the object in the Game() main like: hud = new HUD();
update the tick() in Game class with hud.tick()
update the render() method in Game class with hud.render(g);
*/
