package test.java.unittests;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.java.example.Bomb;
import main.java.example.Coin;
import main.java.example.Hat;
import main.java.example.Scene2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class Unit1 {

    @BeforeAll
    static void init() {
        new JFXPanel();
    }

    @Test
    void testSceneCreation() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        final Scene[] result = new Scene[1];

        Platform.runLater(() -> {
            Scene2 scene2 = new Scene2();
            result[0] = scene2.getScene();
            latch.countDown();
        });

        latch.await();
        assertNotNull(result[0]);
    }

    @Test
    void testCoinCollisionUpdatesScore() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] collected = new boolean[1];

        Platform.runLater(() -> {
            Hat hat = new Hat(getClass().getResource("/hat.png").toExternalForm(), 100, 300);
            Coin coin = new Coin(getClass().getResource("/coin.png").toExternalForm(), 100, 300);
            Scene2 scene2 = new Scene2();

            Pane root = new Pane(hat.getView(), coin.getView());
            scene2.getScene().setRoot(root);

            int scoreBefore = getScore(scene2);
            coin.handleCollision(hat, root, scene2);
            int scoreAfter = getScore(scene2);

            collected[0] = (scoreAfter > scoreBefore);
            latch.countDown();
        });

        latch.await();
        assertTrue(collected[0]);
    }

    @Test
    void testBombCollisionEndsGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] gameEnded = new boolean[1];

        Platform.runLater(() -> {
            Hat hat = new Hat(getClass().getResource("/hat.png").toExternalForm(), 150, 300);
            Bomb bomb = new Bomb(getClass().getResource("/bomb.png").toExternalForm(), 150, 300);
            Scene2 scene2 = new Scene2();

            Pane root = new Pane(hat.getView(), bomb.getView());
            scene2.getScene().setRoot(root);

            gameEnded[0] = bomb.handleCollision(hat, root, scene2);
            latch.countDown();
        });

        latch.await();
        assertTrue(gameEnded[0]);
    }

    private int getScore(Scene2 scene2) {
        try {
            var field = Scene2.class.getDeclaredField("score");
            field.setAccessible(true);
            return field.getInt(scene2);
        } catch (Exception e) {
            throw new RuntimeException("Unable to access score", e);
        }
    }
}
