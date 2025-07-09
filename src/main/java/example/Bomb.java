package main.java.example;

import javafx.scene.layout.Pane;

public class Bomb extends FallingObject {
    private static final double FALL_SPEED = 2;

    public Bomb(String imagePath, double x, double y) {
        super(imagePath, x, y);
    }

    @Override
    public void move() {
        y += FALL_SPEED;
        imageView.setY(y);
    }

    @Override
    public boolean handleCollision(Hat hat, Pane root, Scene2 scene) {
        return imageView.getBoundsInParent().intersects(hat.getView().getBoundsInParent());
    }
}
