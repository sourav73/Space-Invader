package com.teamcritix.callbacks;

import com.teamcritix.constants.Constants;
import com.teamcritix.ui.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*
        public Rectangle playButton = new Rectangle(Constants.BOARD_WIDTH/3+100 , 350 ,120,50);
        public Rectangle quitButton = new Rectangle(Constants.BOARD_WIDTH/3+100 , 450 ,120,50);
        public Rectangle playAgainButton = new Rectangle(Constants.BOARD_WIDTH/3+50 , 350 ,200,50);
        public Rectangle quitButton = new Rectangle(Constants.BOARD_WIDTH/3+90 , 450 ,120,50);
         */

        boolean b = mx >= Constants.BOARD_WIDTH / 3 + 100 && mx <= Constants.BOARD_WIDTH / 3 + 220;
        //Play button
        if(b){
            if(my >= 350 && my <=400){
                //Pressed play button
                GamePanel.state = GamePanel.STATE.GAME;
            }
        }
        //Quit button
        if(b){
            if(my >= 450 && my <=500){
                //Pressed quit button
                System.exit(1);
            }
        }

        if(GamePanel.state == GamePanel.STATE.GAME_FINISHED_MENU){
            if(mx >= Constants.BOARD_WIDTH/3+50 && mx <= Constants.BOARD_WIDTH/3+250){
                if(my >= 350 && my <= 400){
                    //Pressed play Again button
                    GamePanel.state = GamePanel.STATE.GAME;
                }
            }
//            if(b){
//                if(my >= 450 && my <=500){
//                    //Pressed quit button
//                    System.exit(1);
//                }
//            }

        }



    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
