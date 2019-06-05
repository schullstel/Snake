package app;

import Snake.Direction;
import Snake.Drawer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static Snake.Config.STAGE_HEIGHT;
import static Snake.Config.STAGE_WIDTH;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("sample.fxml"));

        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
                return null;
            }
        };

        Drawer drawer = new Drawer(sleeper);
        drawer.startAnimation();

        Scene nowa = new Scene(drawer.getPane_(), STAGE_WIDTH, STAGE_HEIGHT);


        nowa.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.UP) && drawer.getDirection() != Direction.DOWN)
                drawer.setDirection(Direction.UP);
            if (e.getCode().equals(KeyCode.DOWN) && drawer.getDirection() != Direction.UP)
                drawer.setDirection(Direction.DOWN);
            if (e.getCode().equals(KeyCode.LEFT) && drawer.getDirection() != Direction.RIGHT)
                drawer.setDirection(Direction.LEFT);
            if (e.getCode().equals(KeyCode.RIGHT) && drawer.getDirection() != Direction.LEFT)
                drawer.setDirection(Direction.RIGHT);
            try {
                Thread.sleep(125);
            } catch (Exception exc) {

            }

        });
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                primaryStage.setScene(new Scene(drawer.doSmth(), STAGE_WIDTH, STAGE_HEIGHT));
                primaryStage.setTitle("WASTED");
            }
        });


        primaryStage.setTitle("MegaWonsz9");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("app/megawonsz.png"));
        primaryStage.setScene(nowa);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
