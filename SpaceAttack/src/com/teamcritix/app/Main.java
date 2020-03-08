package com.teamcritix.app;

import com.teamcritix.ui.GameMainFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GameMainFrame();
        });
    }
}
