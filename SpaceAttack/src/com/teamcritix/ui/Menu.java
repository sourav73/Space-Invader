package com.teamcritix.ui;

import com.teamcritix.constants.Constants;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(Constants.BOARD_WIDTH/3+100 , 350 ,120,50);
    public Rectangle quitButton = new Rectangle(Constants.BOARD_WIDTH/3+100 , 450 ,120,50);

    public void drawMenu (Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("ariel",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.cyan);
        g.drawString("Space Invader", Constants.BOARD_WIDTH/3,300);

        Font font1 = new Font("ariel",Font.BOLD,30);
        g.setFont(font1);
        g.setColor(Color.GREEN);
        g.drawString("Play",playButton.x+30,playButton.y+35);
        g2d.draw(playButton);
        g.drawString("Quit",quitButton.x+30,quitButton.y+35);
        g2d.draw(quitButton);
    }
}
