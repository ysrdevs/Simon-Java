/**
 * ACS-2947 As2 Part A
 * NAME: YUVRAJ SINGH
 * STUDENT# 3093732
 */

import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.*;

public class Simon {
    public static void main(String[] args) throws InterruptedException {

        Scanner key = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>(4);
        words.add("Green");
        words.add("Blue");
        words.add("Yellow");
        words.add("Red");

        ArrayList<String> simonWord = new ArrayList<>();
        ArrayList<String> playerWord = new ArrayList<>();

        int turn = 1;
        boolean simon = true;
        int playerScore = 0;
        while (simon) {
            StringBuilder temp = new StringBuilder();
            int i = 0;
            while (i < turn) {
                String word = words.get((int) floor(random() * 4));
                simonWord.add(word);
                temp.append(word).append(" ");
                i++;
            }
            System.out.print(temp);
            Thread.sleep(1000);
            System.out.print("\r ");
            String[] split = key.nextLine().split(" ");
            Arrays.stream(split).forEach(playerWord::add);
            if (simonWord.equals(playerWord)) {
                turn += 1;
                playerScore++;
            } else {
                System.out.println("Game over! Your score is " + playerScore);
                break;
            }
        }

    }
}