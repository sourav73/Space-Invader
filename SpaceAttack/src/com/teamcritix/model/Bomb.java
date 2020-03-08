package com.teamcritix.model;

import com.teamcritix.constants.Constants;
import com.teamcritix.image.Image;
import com.teamcritix.image.ImageFactory;

import javax.swing.*;

public class Bomb extends Sprite{
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        initialize();
    }

    private void initialize() {
        ImageIcon imageIcon = ImageFactory.createImage(Image.BOMB);
        setImage(imageIcon.getImage());
    }

    @Override
    public void move() {
        this.y++;  //1 pixel in every iteration

        //Checking constraint bottom of the canvas
        if(y>= Constants.BOARD_HEIGHT - Constants.BOMB_HEIGHT){
            die();
        }
    }
}
