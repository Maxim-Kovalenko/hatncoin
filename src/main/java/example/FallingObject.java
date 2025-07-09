package main.java.example;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class FallingObject {
    protected ImageView imageView;
    protected double x, y;

    public FallingObject(String imagePath, double x, double y) {
        this.x = x;
        this.y = y;
        imageView = new ImageView(imagePath);
        imageView.setX(x);
        imageView.setY(y);
    }

    public abstract void move();

    public ImageView getView() {
        return imageView;
    }

    public boolean isOffScreen() {
        return y > 400;
    }

    public abstract boolean handleCollision(Hat hat, Pane root, Scene2 scene);
}
