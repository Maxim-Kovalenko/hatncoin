package example;

import javafx.scene.input.KeyCode;

public class Hat extends GameObject {
    private static final double MOVE_DISTANCE = 5;
    private static final double WINDOW_WIDTH = 400;

    public Hat(String imagePath, double x, double y) {
        super(imagePath, x, y);
    }

    @Override
    public void move() {
    }

    public void handleKeyPress(KeyCode key) {
        if (key == KeyCode.LEFT) {
            x -= MOVE_DISTANCE;
        } else if (key == KeyCode.RIGHT) {
            x += MOVE_DISTANCE;
        }

        double imageWidth = imageView.getFitWidth();
        if (x < 0) x = 0;
        if (x > WINDOW_WIDTH - imageWidth) x = WINDOW_WIDTH - imageWidth;

        imageView.setX(x);
    }
}
