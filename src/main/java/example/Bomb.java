public class Bomb extends GameObject {
    private static final double FALL_SPEED = 2;

    public Bomb(String imagePath, double x, double y) {
        super(imagePath, x, y);
    }

    @Override
    public void move() {
        y += FALL_SPEED;
        imageView.setY(y);
    }

    public boolean hitsHat(Hat hat) {
        return imageView.getBoundsInParent().intersects(hat.getView().getBoundsInParent());
    }

    public boolean isOffScreen() {
        return y > 400;
    }
}
