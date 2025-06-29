import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene1 scene1 = new Scene1(stage);
        stage.setScene(scene1.getScene());
        stage.setTitle("hat n coin");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
