package main.java.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public abstract class FallingObject {

    protected double x, y;
    protected ImageView imageView;

    public FallingObject(String imagePath, double x, double y) {
        this.x = x;
        this.y = y;
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        imageView.setX(x);
        imageView.setY(y);
    }

    public abstract void move();

    public boolean isOffScreen() {
        return y > 400;
    }

    public ImageView getView() {
        return imageView;
    }

}
