package com.teamcritix.ui;

import com.teamcritix.callbacks.GameEventListener;
import com.teamcritix.callbacks.MouseInput;
import com.teamcritix.constants.Constants;
import com.teamcritix.image.Image;
import com.teamcritix.image.ImageFactory;
import com.teamcritix.model.*;
import com.teamcritix.sound.SoundFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    private ImageIcon backgroundImage;
    private Timer timer;
    private SpaceShip spaceShip;
    private boolean inGame = true;
    private Laser laser;
    private int direction = -1;
    private List<EnemyShip> enemyShips;
    private List<Bomb> bombs;
    private Random random;
    private String message;
    private int deaths=0;
    private SoundFactory soundFactory;
    private int score=0;
    private int shields = 2;
    private Menu menu;
    private GameOverMenu gameOverMenu;

    public enum STATE{
        MENU, GAME, GAME_FINISHED_MENU, REPLAY
    }

    public static STATE state = STATE.MENU;

    public GamePanel(){
            initializeVariables();
            initializeLayout();
            initializeGame();
    }

    private void initializeGame() {
        this.addMouseListener(new MouseInput());
        for (int i = 0 ; i < Constants.ENEMY_SHIPS_ROW ; i++){
            for(int j = 0 ; j < Constants.ENEMY_SHIPS_COLUMN ; j++){
                EnemyShip enemyShip = new EnemyShip(Constants.ENEMY_SHIP_INIT_X + 50 * j,
                        Constants.ENEMY_SHIP_INIT_Y + 50 * i);
                this.enemyShips.add(enemyShip);
            }
        }
    }

    private void initializeVariables() {
        this.menu = new Menu();
        this.gameOverMenu = new GameOverMenu();
        this.soundFactory = new SoundFactory();
        this.random = new Random();
        this.bombs = new ArrayList<>();
        this.enemyShips = new ArrayList<>();
        this.spaceShip = new SpaceShip();
        this.laser = new Laser();
        this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
        this.timer = new Timer(Constants.GAME_SPEED,new GameLoop(this));
        this.timer.start();
    }

    private void initializeLayout() {
            addKeyListener(new GameEventListener(this));
            setFocusable(true);
            setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));

    }

    //Drawing objects
    private void drawPlayer(Graphics g) {
        g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
    }
    private void drawLaser(Graphics g) {
        //If the laser is dead(outside the canvas) --> DO NOT SHOW
        if(!laser.isDead()){
            g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
        }
    }
    private void drawAliens(Graphics g) {
        for(EnemyShip enemyShip : this.enemyShips){
            if(enemyShip.isVisible()){
                g.drawImage(enemyShip.getImage() , enemyShip.getX() , enemyShip.getY() ,this);
            }
        }
    }
    private void drawBombs(Graphics g) {
        for(Bomb bomb: this.bombs){
            if(!bomb.isDead()){
                g.drawImage(bomb.getImage(),bomb.getX(),bomb.getY(),this);
            }
        }
    }
    private void drawGameOver(Graphics g) {
        g.drawImage(backgroundImage.getImage(), 0, 0, null);

        Font font = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Constants.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                Constants.BOARD_HEIGHT / 2 - 100);
    }
    private void drawScores(Graphics g) {
        if(!inGame)
            return;

        Font font = new Font("Helvetica", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Score: " + score, Constants.BOARD_WIDTH - 150, 50);
        g.drawString("Shields: " + shields, 50, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage.getImage(),0,0,null);
        if(state == STATE.GAME) {
            doDrawing(g);
        }else if(state == STATE.MENU){
            menu.drawMenu(g);
        }else if(state == STATE.GAME_FINISHED_MENU){

            if(message.equals(Constants.GAME_OVER)) {
                gameOverMenu.drawGameOverMenu(g, "Game Over!");
            }else if(message.equals(Constants.WIN)){
                gameOverMenu.drawGameOverMenu(g,"You Win!");
            }



        }

    }

    private void doDrawing(Graphics g) {
        if(inGame) {
            System.out.println("DRAW");
            drawScores(g);
            drawPlayer(g);
            drawLaser(g);
            drawAliens(g);
            drawBombs(g);
        } else {
            if(timer.isRunning()){
                timer.stop();
            }
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void doOneLoop() {
        if(state == STATE.GAME) {
            update();
            repaint();
        }
    }

    private void update() {
        System.out.println("UPDATE");
        //This is when the user loses
        if(spaceShip.isDead()){
            inGame = false;
            message = Constants.GAME_OVER;
            state = STATE.GAME_FINISHED_MENU;
        }
        //This is when the user wins
        if(deaths == (this.enemyShips.size())){
            inGame = false;
            message = Constants.WIN;
            state = STATE.GAME_FINISHED_MENU;
        }

        this.spaceShip.move();

        if(!laser.isDead()){
            int shotX = laser.getX();
            int shotY = laser.getY();

            //Destroying enemy ships
            for(EnemyShip alien:this.enemyShips){
                int alienX = alien.getX();
                int alienY = alien.getY();
                if(!alien.isVisible()) continue;
                //Collision detection algorithm
                if(shotX >= (alienX) && shotX <= (alienX + Constants.ENEMY_SHIP_WIDTH) && shotY >= (alienY)
                && shotY <= (alienY + Constants.ENEMY_SHIP_HEIGHT)){
                    alien.setVisible(false);
                    laser.die();
                    deaths++;
                    soundFactory.explosion();
                    score += 10;
                }
            }

            this.laser.move();
        }


        for(EnemyShip enemyShip:this.enemyShips){
            //bouncing back enemy ships on the right and left
            if(enemyShip.getX() >= Constants.BOARD_WIDTH - 2 * Constants.BORDER_RIGHT && direction != -1
                    || enemyShip.getX() <= Constants.BORDER_LEFT && direction != 1){
                direction *= -1;
                for (EnemyShip ufo : enemyShips) {
                    ufo.setY(ufo.getY() + Constants.GO_DOWN);
                }
            }

            if(enemyShip.isVisible()){
                //If the enemy ships reach the bottom of the screen it is called invasion(instant death / loss)
                if(enemyShip.getY() > Constants.BOARD_HEIGHT - 100 - Constants.SPACESHIP_HEIGHT){
                    spaceShip.die();
                }
                enemyShip.move(direction);
            }
        }
        //generating bombs by enemy ships
        for(EnemyShip enemyShip:this.enemyShips){
            if(enemyShip.isVisible() && random.nextDouble() < Constants.BOMB_DROPPING_PROBABILITY){
                Bomb bomb = new Bomb(enemyShip.getX(),enemyShip.getY());
//                soundFactory.laser();
                this.bombs.add(bomb);
            }
        }
        //moving the bomb
        for(Bomb bomb:this.bombs){
            //Collision detection for bomb and space ship
            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int spaceShipX = spaceShip.getX();
            int spaceShipY = spaceShip.getY();
            if(!bomb.isDead() && !spaceShip.isDead()){
                if(bombX >= (spaceShipX) && bombX <= (spaceShipX + Constants.SPACESHIP_WIDTH) && bombY >= (spaceShipY)
                        && bombY <= (spaceShipY + Constants.SPACESHIP_HEIGHT)){
                    bomb.die();
                    soundFactory.explosion();
                    shields--;
                    if(shields<0) {
                        spaceShip.die();
                    }
                }
            }

            if(!bomb.isDead()){
                bomb.move();
            }
        }

    }

    public void keyPressed(KeyEvent e) {
        this.spaceShip.keyPressed(e);
        //If user hit 'SPACE' then a new laser beam is generated
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            int laserX = this.spaceShip.getX();
            int laserY = this.spaceShip.getY();
            if(inGame && laser.isDead()){
                soundFactory.laser();
                laser = new Laser(laserX,laserY);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        this.spaceShip.keyReleased(e);
    }
}
