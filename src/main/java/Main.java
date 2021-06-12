import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scraper scraper = new Scraper();

        System.out.println("Write player name to get data or exit to kill CLI: ");
        String input;

        Scanner br = new Scanner(new InputStreamReader(System.in));
        do {
            System.out.print("\nplayer> ");

            input = br.nextLine();
            System.out.print(scraper.search(input));
        }
        while (!input.equalsIgnoreCase("exit"));
    }

}