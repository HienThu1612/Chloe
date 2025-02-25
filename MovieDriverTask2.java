import java.util.Scanner;

public class MovieDriverTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            Movie movie = new Movie();

            //Prompt user to enter the title of a movie
            System.out.print("Enter the movie title: ");
            String title = scanner.nextLine();
            movie.setTitle(title);

            // Prompt user to enter the movie's rating
            System.out.print("Enter the movie rating: ");
            String rating = scanner.nextLine();
            movie.setRating(rating);

            // Prompt user to enter the number of tickets sold at a theater
            System.out.print("Enter the number of tickets sold: ");
            int soldTickets = scanner.nextInt();
            movie.setSoldTickets(soldTickets);

            // Print using toString method
            System.out.println(movie);
            
            // Ask the user if they want to continue
            // Task #2
            System.out.print("Do you want to enter another movie? (yes/no): ");
            scanner.nextLine(); 
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
        System.out.println("Goodbye");
        scanner.close();
    }
}