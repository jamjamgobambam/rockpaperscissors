package com.codedotorg;

public class GameLogic {

    /** Whether or not the game is over */
    private boolean gameOver;

    /**
     * Constructor for the GameLogic class.
     * Initializes the gameOver variable to false.
     */
    public GameLogic() {
        gameOver = false;
    }

    /**
     * Returns a random choice of "rock", "paper", or
     * "scissors" for the computer player.
     * 
     * @return a String representing the computer's choice
     */
    public String getComputerChoice() {
        String[] options = {"rock", "paper", "scissors"};
        int randomIndex = (int)(Math.random() * 3);
        return options[randomIndex];
    }

    public String determineWinner(String predictedClass, String computerChoice) {
        // Get the predicted class without the leading number
        String userChoice = predictedClass.substring(predictedClass.indexOf(" ") + 1);

        // Create a string containing the computer choice and user choice
        String result = "Computer choice: " + computerChoice + "\nUser Choice: " + userChoice + "\n";

        // Check if there is a tie
        if (userChoice.equals(computerChoice)) {
            result += getTieResult();
        }
        // Check if the user won
        else if (userChoice.equals("rock") && computerChoice.equals("scissors") ||
            userChoice.equals("paper") && computerChoice.equals("rock") ||
            userChoice.equals("scissors") && computerChoice.equals("paper")) {
                result += getUserWinnerResult();
        }
        // Check if the computer won
        else if (!userChoice.equals("neutral")) {
            result += getComputerWinnerResult();
        }

        return result;
    }

    /**
     * Sets the game over flag to true and returns a
     * string indicating a tie result.
     * 
     * @return A string indicating a tie result.
     */
    public String getTieResult() {
        gameOver = true;
        return "Tie!";
    }

    /**
     * Sets the game over flag to true and returns a string
     * indicating that the user has won.
     * 
     * @return a string indicating that the user has won
     */
    public String getUserWinnerResult() {
        gameOver = true;
        return "You win!";
    }

    /**
     * Sets the game over flag to true and returns a string
     * indicating that the computer has won.
     * 
     * @return A string indicating that the player has lost.
     */
    public String getComputerWinnerResult() {
        gameOver = true;
        return "You lose :(";
    }

    /**
     * Returns whether the game is over or not.
     * 
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Resets the game logic by setting the gameOver flag to false.
     */
    public void resetLogic() {
        gameOver = false;
    }

}
