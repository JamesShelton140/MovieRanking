import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Input {

	public static void loadMovieList(ArrayList<Movie> movieList) {
		Path path = Paths.get(".\\resources\\movieList.sav");
        Scanner scanner;
		try {
			scanner = new Scanner(path);
			System.out.println("Reading save file.");
	        //read line by line
	        while(scanner.hasNextLine()){
	            //read next role as "role number" and add to setupData as string array [role, number]
	        	String[] details = scanner.nextLine().split("%&");
	        	Movie movie = new Movie(details[0], Integer.parseInt(details[1]), Float.parseFloat(details[2]));
	        	movieList.add(movie);
	        }
	        scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
	
	public static String selectMenuOption(String menu[], Scanner scanner) {
		
		//Get and parse user input
		String selectedOption = nextString(scanner).toLowerCase();
		 
		//List all options and viable input
		String[] View = {"view", "View Movie Ranking", Integer.toString(Arrays.asList(menu).indexOf("View Movie Ranking")+1)};
		String[] Add = {"add", "Add Movie", Integer.toString(Arrays.asList(menu).indexOf("Add Movie")+1)};
		String[] Compare = {"compare", "Make Comparisons", Integer.toString(Arrays.asList(menu).indexOf("Make Comparisons")+1)};
		String[] Save = {"save", Integer.toString(Arrays.asList(menu).indexOf("Save")+1)};
		String[] Exit = {"exit", "0", "q", "quit"};
		 
		String[][] optionsToParse = {View, Add, Compare, Save, Exit};
		 
		for(int i = 0; i < optionsToParse.length; i++) {
			if(Arrays.asList(optionsToParse[i]).contains(selectedOption)) {
				selectedOption = optionsToParse[i][0];
				break;
			}
		}
		
		return selectedOption;
		 
	}
	
	public static String nextString(Scanner scanner) {
		
		String string = scanner.nextLine();
		
		return string;
	}
	
	public static int nextInt(Scanner scanner) {
		
		int i = scanner.nextInt();
		
		return i;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
