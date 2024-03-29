import java.util.ArrayList;
import java.util.List;

public class DiceGame {
    public static void main(String[] args) {
        String[] lines = FileInput.readFile(args[0], true, true);
        int playerCount = Integer.parseInt(lines[0]);
        String[] playerNames = lines[1].split(",");
        List<Players> players = new ArrayList<>();

        for (String playerName : playerNames) {
            players.add(new Players(playerName));
        }

        int indexCurrent = 0;
        for (int i = 2; i < lines.length; i++) {

           String[] dice = lines[i].split("-");
           int dice1 = Integer.parseInt(dice[0]);
           int dice2 = Integer.parseInt(dice[1]);

           Players currentPlayer = players.get(indexCurrent);
              if (currentPlayer.isPlaying()) {
                  if (dice1 == 0 && dice2 == 0) {
                      /**
                       * If both dice are 0, the player skips the turn and the score does not change.
                       */
                      //System.out.println(currentPlayer.getName() + " skipped the turn and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".");
                      FileOutput.writeToFile(args[1], currentPlayer.getName() + " skipped the turn and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".", true, true);
                  }
                  else if (dice1 == 1 && dice2 == 1){
                        /**
                         * If both dice are 1, the player is removed from the game and the turn passes to the next player.
                         */
                      //System.out.println(currentPlayer.getName() + " threw 1-1. Game over " + currentPlayer.getName() + "!");
                      FileOutput.writeToFile(args[1], currentPlayer.getName() + " threw 1-1. Game over " + currentPlayer.getName() + "!", true, true);
                      currentPlayer.setPlaying(false);
                      players.remove(currentPlayer);
                      indexCurrent = (indexCurrent - 1) % players.size();
                  }
                  else {
                      if (dice1 == 1 || dice2 == 1) {
                          /**
                           * If one of the dice is 1, the player’s score does not change.
                           */
                          currentPlayer.increaseScore(0);
                          //System.out.println(currentPlayer.getName() + " threw " + dice1 + "-" + dice2 + " and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".");
                          FileOutput.writeToFile(args[1], currentPlayer.getName() + " threw " + dice1 + "-" + dice2 + " and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".", true, true);
                      }
                      else {
                            /**
                             * If none of the dice is 1, the player’s score increases by the sum of the dice.
                             */
                          currentPlayer.increaseScore(dice1 + dice2);
                          //System.out.println(currentPlayer.getName() + " threw " + dice1 + "-" + dice2 + " and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".");
                          FileOutput.writeToFile(args[1], currentPlayer.getName() + " threw " + dice1 + "-" + dice2 + " and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".", true, true);
                      }
                  }
                  indexCurrent = (indexCurrent + 1) % players.size();
              }

            if (players.size() == 1) {
                  Players lastPlayer = players.get(0);
                  //System.out.println(lastPlayer.getName() + " is the winner of the game with the score of " + lastPlayer.getScore() + ". Congratulations " + lastPlayer.getName() + "!");
                  FileOutput.writeToFile(args[1], lastPlayer.getName() + " is the winner of the game with the score of " + lastPlayer.getScore() + ". Congratulations " + lastPlayer.getName() + "!", true, true);
                  break;
              }

        }
    }
}

