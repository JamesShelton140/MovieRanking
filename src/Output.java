import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Output {

	public static void printMenu(String[] menu) {
		System.out.println();
		System.out.println("------- Menu -------");
		for(int i = 0; i < menu.length; i++) {
			if(menu[i] != "Exit") {
				System.out.println(i+1+". "+menu[i]);
			} else {
				System.out.println(0+". "+menu[i]);
			}
		}
	}
	
	public static void printMovieList(ArrayList<Movie> movieList) {
		System.out.println("------- Movie Ranking -------");
		
		Movie[] movieArray = movieList.toArray(new Movie[1]);
		Arrays.sort(movieArray);
		
		for(int i = 1; i <= movieArray.length; i++) {
			System.out.println(i + " - " + movieArray[movieArray.length - i]);
		}
	}
	
	public static void saveMovieList(ArrayList<Movie> movieList) {
		//Sort movieList before saving to reduce sort actions in future and make movieList.sav readable directly.
		Movie[] movieArray = movieList.toArray(new Movie[1]);
		Arrays.sort(movieArray);
		
		try {
		      File myObj = new File(".\\resources\\movieList.sav");
		      if (myObj.createNewFile()) {
		    	  System.out.println("File created: " + myObj.getName());
		      } else {
		    	  System.out.println("File already exists.");
		      }
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		try {
		      FileWriter writer = new FileWriter(".\\resources\\movieList.sav");
		      BufferedWriter bufferedWriter = new BufferedWriter(writer);
		      for(int i = 0; i < movieArray.length; i++) {
		    	  Movie movie = movieArray[i];
		    	  bufferedWriter.write(movie.getTitle()+"%&"+movie.getYear()+"%&"+movie.getRating());
		    	  if (i+1 < movieArray.length) {
		    		  bufferedWriter.newLine();
		    	  }
		      }
		      bufferedWriter.close();
		      System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
