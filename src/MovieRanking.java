import java.io.IOException;
import java.util.*;

public class MovieRanking {
	
	private ArrayList<Movie> movieList = new ArrayList<Movie>();
	static Scanner scanner;

	public MovieRanking() {
		
	}
	
	public void addMovie(Movie movie) {
		this.movieList.add(movie);
	}
	
	public ArrayList<Movie> getMovieList(){
		return movieList;
	}
	
	// Function to calculate the Probability 
    private static float Probability(float rating1,  
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
    private static float[] updateEloRating(float Ra, float Rb, 
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
    
    private static void makeComparison(Movie movie1, Movie movie2, boolean movie1Won) {
    	int k = 50;
    	
    	float[] newRatings = updateEloRating(movie1.getRating(), movie2.getRating(), k, movie1Won);
    	
    	movie1.setRating(newRatings[0]);
    	movie1.incrementComparisons();
    	
    	movie2.setRating(newRatings[1]);
    	movie2.incrementComparisons();
    }
    
    private Movie generateRandomMovie() {
    	
    	//find highest number of comparisons
    	int minComparisons = this.getMovieList().get(0).getComparisons();
    	int maxComparisons = 0;
    	for(int i = 0; i < this.getMovieList().size(); i++) {
    		int comparisons = this.getMovieList().get(i).getComparisons();
    		if( comparisons > maxComparisons) {
    			maxComparisons = comparisons;
    		}
    		if(comparisons < minComparisons) {
    			minComparisons = comparisons;
    		}
    	}
    	
    	//initialise random number generator
    	Random randGenerator = new Random();
    	
    	for(int i = minComparisons; i <= maxComparisons; i++) {
    		int chosen = randGenerator.nextInt(this.getMovieList().size());
    		if (this.getMovieList().get(chosen).getComparisons() <= i) {
    			return this.getMovieList().get(chosen);
    		}
    	}
    	
    	return this.getMovieList().get(0);
    }
    
    private void compareMovies() {
    	Boolean makingComparisons = true;
    	
    	//initiate scanner
    	scanner = new Scanner(System.in);
    	
    	while(makingComparisons == true) {
    		
    		Movie movie1 = generateRandomMovie();
    		Movie movie2 = movie1;
    		
    		while(movie1 == movie2) {
    			movie2 = generateRandomMovie();
    		}
    		
    		System.out.println("\n" + "Which movie do you prefer?");
    		System.out.println("1. " + movie1.getTitle());
    		System.out.println("2. " + movie2.getTitle());
    		System.out.println("exit. Return to main menu without comparing");
    		
    		String selectedOption = "exit";//"exit"
    		
    		selectedOption = Input.nextString(scanner);
    		
    		//Act on user input
			switch (selectedOption) {
				case "1":
					makeComparison(movie1, movie2, true);
					break;
					
				case "2":
					makeComparison(movie1, movie2, false);
					break;
					
				case "exit"://"exit"
					makingComparisons = false;
			 		break;
			 		
			 	default:
			 		System.out.println("\n" + "Invalid input! Please try again.");
			 }
    		
    	}
    	
    }
    
    public void run() {
    	 Boolean programRunning = true;
    	 
    	 //initiate scanner
    	 //Scanner scanner = new Scanner(System.in);
    	 scanner = new Scanner(System.in);
    	 
		 //Menu options array
		 String[] mainMenu = {"View Movie Ranking", "Add Movie", "Make Comparisons", "Save", "Exit"};
		 
		 //Load list from file
		 Input.loadMovieList(this.getMovieList());
		 
		 while(programRunning == true) {
			 
			 Output.printMenu(mainMenu);
			 
			 //Set default behaviour to avoid infinite loops
			 String selectedOption = "exit";
			 
			 selectedOption = Input.selectMainMenuOption(mainMenu, scanner);
			 
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
			 		if (this.getMovieList().size() >= 2) {
			 			compareMovies();
			 		} else {
			 			System.out.println("You need at least 2 movies to compare! Add more movies.");
			 		}
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

		MovieRanking ranking = new MovieRanking();
		ranking.run();
		
	}

}
