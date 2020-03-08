package com.teamcritix.ui;

import com.teamcritix.constants.Constants;

import java.awt.*;

public class GameOverMenu {
    public Rectangle playAgainButton = new Rectangle(Constants.BOARD_WIDTH/3+50 , 350 ,200,50);
    public Rectangle quitButton = new Rectangle(Constants.BOARD_WIDTH/3+90 , 450 ,120,50);

    public void drawGameOverMenu (Graphics g,String message){

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("ariel",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.cyan);
        if(message.equals("You Win!")){
            g.drawString(message, Constants.BOARD_WIDTH /  3+ 40, 300);
        }else if(message.equals("Game Over!")){
            g.drawString(message, Constants.BOARD_WIDTH / 3 + 10, 300);
        }

        Font font1 = new Font("ariel",Font.BOLD,30);
        g.setFont(font1);
        g.setColor(Color.GREEN);
        g.drawString("Play Again",playAgainButton.x+30,playAgainButton.y+35);
        g2d.draw(playAgainButton);
        g.drawString("Quit",quitButton.x+30,quitButton.y+35);
        g2d.draw(quitButton);
    }

}
