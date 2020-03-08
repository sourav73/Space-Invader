package com.teamcritix.constants;

public class Constants {

    private Constants() {

    }

    public static final String TITLE = "Space Invasion";
    public static final int BOARD_WIDTH = 1024;
    public static final int BOARD_HEIGHT = 750;

    //Message related constants
    public static final String WIN = "Win!";
    public static final String GAME_OVER = "Game Over!";

    //UFO related constants
    public static final int ENEMY_SHIP_HEIGHT = 24;
    public static final int ENEMY_SHIP_WIDTH = 32;
    public static final int ENEMY_SHIP2_HEIGHT = 28;
    public static final int ENEMY_SHIP2_WIDTH = 34;
    public static final int ENEMY_SHIP_INIT_X = 300;
    public static final int ENEMY_SHIP_INIT_Y = 50;
    public static final int ENEMY_SHIPS_ROW = 4;
    public static final int ENEMY_SHIPS_COLUMN = 9;
    public static final int ENEMY_SHIPS2_COUNT = 8;

    //Spaceship not go outside the canvas
//    public static final int BORDER_PADDING = 50;
    public static final int BORDER_RIGHT = 50;
    public static final int BORDER_LEFT = 50;
    //Every time UFOs hit the one side of the frame they decrease 30 pixel
    public static final int GO_DOWN = 30;

    //Bomb related constants
    public static final int BOMB_HEIGHT = 6;
    public static final int BOMB_WIDTH = 2;
    public static final double BOMB_DROPPING_PROBABILITY = .0005;

    //Speed of the application
    public static final int GAME_SPEED = 10;
    //SpaceShip dimension
    public static final int SPACESHIP_WIDTH = 70;
    public static final int SPACESHIP_HEIGHT = 70;
    //Speed of the laser
    public static final int LASER_HORIZONTAL_TRANSLATION = 6;
    //Images for objects
    public static final String UFO_IMAGE_URL = "images/ufo.png";
    public static final String UFO2_IMAGE_URL = "images/ufo2.png";
    public static final String LASER_IMAGE_URL = "images/laser.png";
    public static final String BOMB_IMAGE_URL = "images/bomb.png";
    public static final String BACKGROUND_IMAGE_URL = "images/background.jpg";
    public static final String SPACESHIP_IMAGE_URL = "images/spaceship.png";
    public static final String GAME_ICON_URL = "images/game_icon.png";

}
