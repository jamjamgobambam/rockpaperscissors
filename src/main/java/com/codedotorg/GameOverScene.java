package com.codedotorg;

import com.codedotorg.modelmanager.CameraController;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverScene {

    /** Button to play the game again */
    private Button playAgainButton;

    /** Button to exit the app */
    private Button exitButton;

    public GameOverScene() {
        exitButton = new Button("Exit");
        playAgainButton = new Button("Play Again");
    }

    /**
     * Returns a Scene object that displays a message indicating who won
     * 
     * @param winner The text containing who won.
     * @param primaryStage The primary stage of the JavaFX application.
     * @param cameraController The CameraController object used to control the camera in the 3D scene.
     * @return A Scene object that displays a message indicating who won the game.
     */
    public Scene createGameOverScene(String winner, CameraController cameraController) {
        createExitButtonAction(cameraController);
        
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Label winnerLabel = new Label(winner);

        layout.getChildren().addAll(winnerLabel, playAgainButton, exitButton);

        Scene gameOverScene = new Scene(layout, 600, 750);
        gameOverScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        return gameOverScene;
    }

    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    /**
     * Sets the action for the exit button. When clicked, it
     * stops the camera capture and exits the program.
     */
    private void createExitButtonAction(CameraController cameraController) {
        exitButton.setOnAction(event -> {
            cameraController.stopCapture();
            System.exit(0);
        });
    }

}
