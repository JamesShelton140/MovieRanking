import java.io.IOException;
import java.util.*;

public class MovieRanking {
	
	private ArrayList<Movie> movieList = new ArrayList<Movie>();

	public MovieRanking() {
		
	}
	
	public void addMovie(Movie movie) {
		this.movieList.add(movie);
	}
	
	public ArrayList<Movie> getMovieList(){
		return movieList;
	}
	
	// Function to calculate the Probability 
    static float Probability(float rating1,  
                               float rating2) 
    { 
        return 1.0f * 1.0f / (1 + 1.0f *  
                (float)(Math.pow(10, 1.0f *  
               (rating1 - rating2) / 400))); 
    } 
      
    // Function to calculate Elo rating 
    // K is a constant. 
    // d determines whether Player A wins 
    // or Player B.  
    static float[] updateEloRating(float Ra, float Rb, 
                            int K, boolean d) 
    {  
      
        // To calculate the Winning 
        // Probability of Player B 
        float Pb = Probability(Ra, Rb); 
      
        // To calculate the Winning 
        // Probability of Player A 
        float Pa = Probability(Rb, Ra); 
      
        // Case d==true When Player A wins 
        // Updating the Elo Ratings 
        if (d == true) { 
            Ra = Ra + K * (1 - Pa); 
            Rb = Rb + K * (0 - Pb); 
        } 
      
        // Case d==false When Player B wins 
        // Updating the Elo Ratings 
        else { 
            Ra = Ra + K * (0 - Pa); 
            Rb = Rb + K * (1 - Pb); 
        } 
      
        float[] newRatings = {(float)(Math.round(Ra * 1000000.0) / 1000000.0), (float)(Math.round(Rb * 1000000.0) / 1000000.0)};  
        return newRatings;
    }
    
    static void makeComparison(Movie movie1, Movie movie2, boolean movie1Won) {
    	int k = 50;
    	
    	float[] newRatings = updateEloRating(movie1.getRating(), movie2.getRating(), k, movie1Won);
    	
    	movie1.setRating(newRatings[0]);
    	movie2.setRating(newRatings[1]);
    }
    
    public void run() {
    	 Boolean programRunning = true;
    	 
    	 //initiate scanner
    	 Scanner scanner = new Scanner(System.in);
    	 
		 //Menu options array
		 String[] mainMenu = {"View Movie Ranking", "Add Movie", "Make Comparisons", "Save", "Exit"};
		 
		 //Load list from file
		 Input.loadMovieList(this.getMovieList());
		 
		 while(programRunning == true) {
			 
			 Output.printMenu(mainMenu);
			 
			 //Set default behaviour to avoid infinite loops
			 String selectedOption = "Exit";
			 
			 selectedOption = Input.selectMenuOption(mainMenu, scanner);
			 
			 //Act on user input
			 switch (selectedOption) {
			 	case "view":
			 		Output.printMovieList(this.getMovieList());
			 		break;
			 		
			 	case "add":
			 		System.out.println("\n"+"Input title of movie:");
			 		String title = Input.nextString(scanner);
			 		System.out.println("\n"+"Input year of release:");
			 		int year = Input.nextInt(scanner);
			 		
			 		this.addMovie(new Movie(title, year));
			 		
			 		break;
			 		
			 	case "compare":
			 		break;
			 		
			 	case "save":
			 		Output.saveMovieList(this.getMovieList());
			 		break;
			 		
			 	case "exit":
			 		System.out.println("Exiting!");
			 		programRunning = false;
			 		break;
			 		
			 	default:
			 		System.out.println("Invalid input! Please try again.");
			 }
			 
		 }
		 
		 scanner.close();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Movie movie1 = new Movie("Avengers: Endgame", 2019);
		Movie movie2 = new Movie("Serenity", 2005);
		
		System.out.println(movie1);
		System.out.println(movie2 );
		
		makeComparison(movie1, movie2, true);
		
		System.out.println(movie1);
		System.out.println(movie2);
		*/
		
		MovieRanking ranking = new MovieRanking();
		ranking.run();
		
		/*
		System.out.println(ranking.getMovieList());
		
		Input.loadMovieList(ranking.getMovieList());
		
		System.out.println(ranking.getMovieList());
		
		Output.saveMovieList(ranking.getMovieList());
		
		Output.printMovieList(ranking.getMovieList());
		*/
	}

}
