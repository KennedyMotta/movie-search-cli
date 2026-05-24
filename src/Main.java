import classes.ApiRequest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scan_input = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("         🎬 MOVIE SEARCH APP");
        System.out.println("========================================");

        ApiRequest request = new ApiRequest();

        while (true) {

            String searchType = "";
            boolean searchedType = false;

            while (!searchedType) {

                System.out.println("\n----------------------------------------");
                System.out.println("           CHOOSE SEARCH TYPE");
                System.out.println("----------------------------------------");
                System.out.println("  [1] Search movies (list)");
                System.out.println("  [2] Exact title");
                System.out.println("  [3] IMDb ID");
                System.out.println("  [0] Exit");
                System.out.println("----------------------------------------");
                System.out.print("👉 Enter your choice: ");

                String option = scan_input.nextLine();

                switch (option) {
                    case "1" -> {
                        searchType = "s";
                        searchedType = true;
                    }
                    case "2" -> {
                        searchType = "t";
                        searchedType = true;
                    }
                    case "3" -> {
                        searchType = "i";
                        searchedType = true;
                    }
                    case "0" -> {
                        System.out.println("\n👋 Exiting app...");
                        scan_input.close();
                        return;
                    }
                    default -> System.out.println("❌ Invalid option. Try again.");
                }
            }

            System.out.println("\n----------------------------------------");
            System.out.print("🎥 Enter movie name: ");
            String movieName = scan_input.nextLine();

            String encodedMovieName =
                    URLEncoder.encode(movieName, StandardCharsets.UTF_8);

            System.out.println("\n🔎 Searching...");
            System.out.println("----------------------------------------");

            request.searchMovie(encodedMovieName, searchType);

            System.out.println("\n----------------------------------------");
            System.out.println("Do you want to search again?");
            System.out.println("[1] Yes");
            System.out.println("[0] No (Exit)");
            System.out.print("👉 Choice: ");

            String again = scan_input.nextLine();

            if (again.equals("0")) {
                System.out.println("\n👋 Bye!");
                break;
            }
        }

        scan_input.close();

        System.out.println("\n========================================");
        System.out.println("            SEARCH COMPLETE");
        System.out.println("========================================");
    }
}