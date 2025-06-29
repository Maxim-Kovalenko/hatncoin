import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {
    protected double x, y;
    protected ImageView imageView;

    public GameObject(String imagePath, double x, double y) {
        this.x = x;
        this.y = y;
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        imageView.setX(x);
        imageView.setY(y);
    }

    public ImageView getView() {
        return imageView;
    }

    public abstract void move();
}
