package com.teamcritix.model;

import com.teamcritix.constants.Constants;
import com.teamcritix.image.Image;
import com.teamcritix.image.ImageFactory;

import javax.swing.*;

public class Laser extends Sprite{

    public Laser(){

    }

    public Laser(int x, int y){
        this.x = x;
        this.y = y;
        initialize();
    }

    private void initialize() {
        ImageIcon imageIcon = ImageFactory.createImage(Image.LASER);
        setImage(imageIcon.getImage());

        setX(x+Constants.SPACESHIP_WIDTH/2);
        setY(y);
    }

    @Override
    public void move() {
        // The laser moves from bottom to top
        this.y -= Constants.LASER_HORIZONTAL_TRANSLATION;

        if(this.y<0){
            this.die();
        }
    }
}
