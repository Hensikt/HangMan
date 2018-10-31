import java.util.Scanner;
import java.util.Random;

public class hangMan{

    static int wins = 0;
    static int losses = 0;
    static int rounds = 0;

    private static void lose(){
        losses++;
    }

    private static void win(){
        wins++;
    }

    private static void round(){
        rounds++;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] WordList = { "zeilboot", "vis", "drank", "computer",
                "archeage", "koffie", "carnaval", "halloween",
                "eclipse", "horloge" };

        boolean playing = true;
        while (playing) {
            System.out.println("Welkom bij galgje!");
            char[] randomGuessWord =
                    WordList[random.nextInt(WordList.length)].toCharArray();
            int amountOfGuesses = randomGuessWord.length;
            char[] playerGuess = new char[amountOfGuesses];

            for (int i = 0; i < playerGuess.length; i++) {
                playerGuess[i] = '_';
            }

            boolean wordIsGuessed = false;
            int tries = 0;

            while (!wordIsGuessed && tries != amountOfGuesses) {
                System.out.println("Gegokte letters: ");
                printArray(playerGuess);
                System.out.printf(
                        "U Heeft %d pogingen over. \n", amountOfGuesses - tries);
                System.out.println("Type een letter");
                char input = scanner.nextLine().charAt(0);
                tries++;

                if (input == '-') {
                    playing = false;
                    wordIsGuessed = true;
                } else {
                    for (int i = 0; i < randomGuessWord.length; i++) {
                        if (randomGuessWord[i] == input) {
                            playerGuess[i] = input;
                        }
                    }

                    if (isTheWordGuessed(playerGuess)) {
                        wordIsGuessed = true;
                        System.out.println("Gefeliciteerd, u won!");
                        win();
                        rounds++;
                    }
                }

            }
            if (!wordIsGuessed)
                System.out.println("U hangt aan de galg");
            lose();
            rounds++;
            System.out.println("Wilt u opnieuw proberen? (yes/no)");
            String anotherGame = scanner.nextLine();

            if (anotherGame.equals("no"))
                System.out.println("U kiest om te stoppen");
            playing = false;

        }

        System.out.println("Game over..");
        System.out.println("Gewonnen: " + wins + " Verloren: "
                + losses + " rondes: " + rounds);
    }

    public static void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean isTheWordGuessed(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_')
                return false;
        }
        return true;
    }

}