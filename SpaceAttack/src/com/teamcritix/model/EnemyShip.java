package com.teamcritix.model;

import com.teamcritix.image.Image;
import com.teamcritix.image.ImageFactory;

import javax.swing.*;

public class EnemyShip extends Sprite{
    //ufo ship does not "die": it becomes invisible
    private boolean visible = true;

    public EnemyShip(int x, int y) {
        this.x = x;
        this.y = y;
        initialize();
    }

    private void initialize() {
        ImageIcon imageIcon = ImageFactory.createImage(Image.UFO);
        setImage(imageIcon.getImage());
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void move(int direction){
        this.x += direction;
    }

    @Override
    public void move() {

    }
}
