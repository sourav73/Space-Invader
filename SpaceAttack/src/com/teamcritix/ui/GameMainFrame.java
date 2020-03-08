package com.teamcritix.ui;

import com.teamcritix.constants.Constants;

import javax.swing.*;


public class GameMainFrame extends JFrame {
    public GameMainFrame(){
        initializeLayout();
    }
    ImageIcon imageIcon = new ImageIcon(Constants.GAME_ICON_URL);

    private void initializeLayout() {
        add(new GamePanel());

        setTitle(Constants.TITLE);
        setIconImage(imageIcon.getImage());

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
}
