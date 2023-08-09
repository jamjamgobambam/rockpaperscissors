package com.codedotorg;

public class GameLogic {

    private boolean gameOver;

    public GameLogic() {
        gameOver = false;
    }

    public String getComputerChoice() {
        String[] options = {"rock", "paper", "scissors"};
        int randomIndex = (int)(Math.random() * 3);
        return options[randomIndex];
    }

    public String determineWinner(String predictedClass, String computerChoice) {
        String userChoice = predictedClass.substring(predictedClass.indexOf(" ") + 1);

        String result = "Computer choice: " + computerChoice + "\nUser Choice: " + userChoice + "\n";

        if (userChoice.equals(computerChoice)) {
            result += "Tie!";
            gameOver = true;
        }
        else if (userChoice.equals("rock") && computerChoice.equals("scissors") ||
            userChoice.equals("paper") && computerChoice.equals("rock") ||
            userChoice.equals("scissors") && computerChoice.equals("paper")) {
                result += "You win!";
                gameOver = true;
        }
        else if (!userChoice.equals("neutral")) {
            result += "You lose :(";
            gameOver = true;
        }

        return result;
    }

    public void resetLogic() {
        gameOver = false;
    }


}
