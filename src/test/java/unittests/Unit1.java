package unittests;

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
        // Initializes JavaFX environment
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
    void testCoinCollectionIncreasesScore() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] result = new boolean[1];

        Platform.runLater(() -> {
            Hat hat = new Hat(getClass().getResource("/hat.png").toExternalForm(), 100, 300);
            Coin coin = new Coin(getClass().getResource("/coin.png").toExternalForm(), 100, 300);

            Pane root = new Pane(hat.getView(), coin.getView());
            Scene2 scene2 = new Scene2();
            scene2.getScene().setRoot(root);

            result[0] = coin.isCollectedBy(hat);
            latch.countDown();
        });

        latch.await();
        assertTrue(result[0]);
    }

    @Test
    void testGameOverOnBombHit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] result = new boolean[1];

        Platform.runLater(() -> {
            Hat hat = new Hat(getClass().getResource("/hat.png").toExternalForm(), 150, 300);
            Bomb bomb = new Bomb(getClass().getResource("/bomb.png").toExternalForm(), 150, 300);

            result[0] = bomb.hitsHat(hat);
            latch.countDown();
        });

        latch.await();
        assertTrue(result[0]);
    }
}
