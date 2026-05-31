/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trimber;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import trimber.graphics.Screen;
import trimber.input.InputHandler;


/**
 *
 * @author david
 */
public class Display extends Canvas implements Runnable {
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Trimber";
    private Thread thread;
    private boolean running = false;
    private Screen screen;
    private Game game;
    private OldPlayer player;
    private BufferedImage img;
    private int[] pixels ;
    private InputHandler input;
    private int fps = 0;
    //public OldCube cubeMesh = new OldCube(new Vector3D(10f, 0f, 10f));


    public Display() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        screen = new Screen(WIDTH, HEIGHT);
        player = new OldPlayer(1.96f);
        game = new Game(player);
        game.loadObjects();
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        input = new InputHandler(this);
        addKeyListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
        addFocusListener(input);

    }
    
    
    
    private void start(){
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
        //System.out.println("working");
        
    }
    
    private void stop(){
        if (!running) return;
        running = false;
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        
        
                
    }
    
    
    
    public void run() {
    int frames = 0;
    int ticks = 0;
    double unprocessedSeconds = 0;
    long previousTime = System.nanoTime();
    double secondsPerTick = 1.0 / 60.0;
    
    
    long timer = System.currentTimeMillis(); 

    while (running) {
        long currentTime = System.nanoTime();
        long passedTime = currentTime - previousTime;
        previousTime = currentTime;
        unprocessedSeconds += passedTime / 1000000000.0;

        // The game logic is still strictly locked to 60 TPS
        while (unprocessedSeconds >= secondsPerTick) {
            tick();
            unprocessedSeconds -= secondsPerTick;
            ticks++;
        }

        // Rendering happens every single time the loop spins (Uncapped FPS)
        render();
        frames++;
        
        

        // FPS/Tick Counter...
        if (System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            System.out.println("FPS: " + frames + " | Ticks: " + ticks);
            fps = frames;
            frames = 0;
            ticks = 0;
        }
    }
    }
    
    private void tick() {
        game.tick(input.key);
    }
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }
        
        screen.render(game);

        System.arraycopy(screen.pixels, 0, pixels, 0, WIDTH * HEIGHT); 
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH,HEIGHT, null);
        g.setFont(new Font("Verdana", 0, 50));
        g.drawString(String.valueOf(fps), 50, 50);
        g.dispose();
        bs.show();
    }


    
    public static void main(String[] args) {
        Display game = new Display();
        JFrame frame = new JFrame();
        frame.add(game);
        
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setCursor(blank);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        System.out.println("running");
        game.start();
        
    }

    

    
    
}
