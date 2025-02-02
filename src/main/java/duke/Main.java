package duke;

import java.io.IOException;

import duke.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String FXML_VIEW_MAIN_WINDOW = "/view/MainWindow.fxml";
    private static final String TITLE = "Duke";

    private final Duke duke = new Duke(Duke.DEFAULT_STORAGE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_VIEW_MAIN_WINDOW));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
