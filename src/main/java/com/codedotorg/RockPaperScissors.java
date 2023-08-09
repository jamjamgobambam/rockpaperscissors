package com.codedotorg;

import com.codedotorg.modelmanager.CameraController;
import com.codedotorg.modelmanager.ModelManager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RockPaperScissors {

    /** The main window of the app */
    private Stage window;

    private MainScene game;
    private GameOverScene gameOver;
    private GameLogic logic;

    /** Manages the TensorFlow model used for image classification */
    private ModelManager model;

    /** Controls the camera capture and provides frames to the TensorFlow model for classification */
    private CameraController cameraController;

    private Timeline timeline;

    /**
     * Constructor for the RockPaperScissors class.
     * Initializes a new CameraController, ModelManager, and MainScene.
     */
    public RockPaperScissors() {
        cameraController = new CameraController();
        model = new ModelManager();
        game = new MainScene();
        gameOver = new GameOverScene();
        logic = new GameLogic();
    }
    
    /**
     * Starts the Rock, Paper, Scissors game.
     * Sets the title of the primary stage to "Rock, Paper, Scissors".
     * Adds a shutdown hook to stop the camera capture when the app is closed.
     * Calls the showMainScreen() method to display the main screen.
     *
     * @param primaryStage the primary stage of the application
     */
    public void startApp(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Rock, Paper, Scissors");

        // Shutdown hook to stop the camera capture when the app is closed
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            cameraController.stopCapture();
        }));

        loadMainScreen();
        updateGame();
    }

    public void loadMainScreen() {
        // Set the game to starting defaults
        resetGame();

        // Display the window
        window.show();

        // Capture the camera view and set the model for the cameraController object
        cameraController.captureCamera(game.getCameraView(), model);

        // Retrieve the Loading object
        Loading cameraLoading = game.getLoadingAnimation();

        // Show the loading animation while the camera is loading
        cameraLoading.showLoadingAnimation(game.getCameraView());
    }

    public void updateGame() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            // Get the predicted class and score from the CameraController
            String predictedClass = cameraController.getPredictedClass();
            double predictedScore = cameraController.getPredictedScore();

            if (predictedClass != null) {
                game.showUserResponse(predictedClass, predictedScore);
                String computerChoice = logic.getComputerChoice();
                String winner = logic.determineWinner(predictedClass, computerChoice);
                loadGameOver(winner);
            }
        }));

        // Specify that the animation should repeat indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }

    public void loadGameOver(String winner) {
        Button playAgainButton = gameOver.getPlayAgainButton();

        playAgainButton.setOnAction(event -> {
            resetGame();
        });

        Scene gameOverScene = gameOver.createGameOverScene(winner, cameraController);
        window.setScene(gameOverScene);
        timeline.stop();
    }

    public void resetGame() {
        logic.resetLogic();
        Scene mainScene = game.createMainScene(cameraController);
        window.setScene(mainScene);
        updateGame();
    }

}
