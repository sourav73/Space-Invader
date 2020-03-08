package com.teamcritix.model;

import com.teamcritix.constants.Constants;
import com.teamcritix.image.Image;
import com.teamcritix.image.ImageFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class SpaceShip extends Sprite{

    public SpaceShip() {
        initialize();
    }

    private void initialize() {
        ImageIcon imageIcon = ImageFactory.createImage(Image.SPACESHIP);
        setImage(imageIcon.getImage());

        int start_x = Constants.BOARD_WIDTH/2-Constants.SPACESHIP_WIDTH/2;
        int start_y = Constants.BOARD_HEIGHT-100;

        setX(start_x);
        setY(start_y);
    }

    @Override
    public void move() {
        x += dx;
        //Cannot go beyond the canvas of the left side
        if(x < Constants.SPACESHIP_WIDTH){
            x = Constants.SPACESHIP_WIDTH;
        }
        //can not go beyond the canvas on the right
        if (x >= Constants.BOARD_WIDTH-2*Constants.SPACESHIP_WIDTH) {
            x =Constants.BOARD_WIDTH-2*Constants.SPACESHIP_WIDTH;
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        //Going to left
        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            dx = -2;
        }
        //Going to right
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        //we have to handle keyReleased events as well - otherwise the spaceship
        //would not be able to stand still
        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            dx = 0;
        }
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            dx = 0;
        }
    }
}
