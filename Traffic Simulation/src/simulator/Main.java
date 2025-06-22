package simulator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Main extends Application {

    private static final int GRID_SIZE = 5;
    private Rectangle[][] vehicles = new Rectangle[GRID_SIZE][GRID_SIZE];
    private Circle[][] signals = new Circle[GRID_SIZE][GRID_SIZE];
    private boolean[][] isGreen = new boolean[GRID_SIZE][GRID_SIZE];
    private Random rand = new Random();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                BorderPane cell = new BorderPane();

                signals[i][j] = new Circle(10);
                isGreen[i][j] = rand.nextBoolean();
                signals[i][j].setFill(isGreen[i][j] ? Color.GREEN : Color.RED);

                vehicles[i][j] = new Rectangle(30, 30, Color.GRAY);
                if (rand.nextDouble() < 0.2) {
                    vehicles[i][j].setFill(Color.RED); // Emergency
                }

                cell.setTop(signals[i][j]);
                cell.setCenter(vehicles[i][j]);

                grid.add(cell, j, i);
            }
        }

        root.setCenter(grid);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Traffic Simulator - Final");
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTraffic()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTraffic() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                boolean emergency = vehicles[i][j].getFill() == Color.RED;
                if (emergency || isGreen[i][j]) {
                    vehicles[i][j].setTranslateX(vehicles[i][j].getTranslateX() + 5);
                    if (vehicles[i][j].getTranslateX() > 50) {
                        vehicles[i][j].setTranslateX(0);
                        vehicles[i][j].setFill(rand.nextDouble() < 0.2 ? Color.RED : Color.GRAY);
                        isGreen[i][j] = rand.nextBoolean();
                        signals[i][j].setFill(isGreen[i][j] ? Color.GREEN : Color.RED);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
