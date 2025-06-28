package example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scene1 {
    private Scene scene;

    public Scene1(Stage stage) {
        Image image = new Image(getClass().getResource("/hat.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Button button = new Button("Play");
        button.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");

        button.setOnAction(e -> {
            Scene2 scene2 = new Scene2();
            scene2.launch(stage);
        });

        VBox layout = new VBox(20, imageView, button);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 400, 400);
    }

    public void mainmenu(Stage stage) {
        Scene1 menu = new Scene1(stage);
        stage.setScene(menu.getScene());
    }

    public Scene getScene() {
        return scene;
    }
}
