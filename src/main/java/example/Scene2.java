package main.java.example;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Scene2 {
    private Pane root;
    private Scene scene;
    private Hat hat;
    private ArrayList<FallingObject> objects = new ArrayList<>();
    private Label scoreLabel;
    private Label gameOverLabel;
    private int score = 0;
    private boolean isGameOver = false;

    private Random random = new Random();
    private Stage stage;

    public Scene2() {
        root = new Pane();
        scene = new Scene(root, 400, 400);

        hat = new Hat("/resources/hat.png", 180, 330);
        root.getChildren().add(hat.getView());

        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(new Font(14));
        scoreLabel.setTextFill(Color.BLACK);
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        root.getChildren().add(scoreLabel);

        gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(new Font("Arial", 24));
        gameOverLabel.setTextFill(Color.RED);
        gameOverLabel.setLayoutX(130);
        gameOverLabel.setLayoutY(180);
        gameOverLabel.setVisible(false);
        root.getChildren().add(gameOverLabel);

        scene.setOnKeyPressed(this::handleKeyPress);

        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(4), e -> spawnObjects()));
        spawner.setCycleCount(Timeline.INDEFINITE);
        spawner.play();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isGameOver) {
                    updateObjects();
                    checkCollisions();
                }
            }
        };
        timer.start();
    }

    public Scene getScene() {
        return scene;
    }

    private void handleKeyPress(KeyEvent event) {
        hat.handleKeyPress(event.getCode());
    }

    private void spawnObjects() {
        if (isGameOver) return;

        double x1 = random.nextDouble() * (400 - 40);
        Coin coin = new Coin("/resources/coin.png", x1, 0);
        objects.add(coin);
        root.getChildren().add(coin.getView());

        double x2 = random.nextDouble() * (400 - 40);
        Bomb bomb = new Bomb("/resources/bomb.png", x2, 0);
        objects.add(bomb);
        root.getChildren().add(bomb.getView());
    }

    private void updateObjects() {
        for (FallingObject obj : objects) {
            obj.move();
        }
    }

    private void checkCollisions() {
        Iterator<FallingObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            FallingObject obj = iterator.next();

            if (obj instanceof Coin coin) {
                if (coin.isCollectedBy(hat)) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                    root.getChildren().remove(coin.getView());
                    iterator.remove();
                } else if (coin.isOffScreen()) {
                    root.getChildren().remove(coin.getView());
                    iterator.remove();
                }
            } else if (obj instanceof Bomb bomb) {
                if (bomb.hitsHat(hat)) {
                    endGame();
                    return;
                } else if (bomb.isOffScreen()) {
                    root.getChildren().remove(bomb.getView());
                    iterator.remove();
                }
            }
        }
    }

    public void launch(Stage stage) {
        this.stage = stage;
        stage.setScene(getScene());
    }

    private void endGame() {
        isGameOver = true;
        gameOverLabel.setVisible(true);

        Scene1 menu = new Scene1(new Stage());
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(e -> menu.mainmenu(stage));
        delay.play();
    }
}
