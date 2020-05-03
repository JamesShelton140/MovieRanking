
public class Movie implements Comparable<Movie>{
	
	private String title;
	private int year;
	private float rating;
	private int comparisons;

	public Movie(String title, int year) {
		this(title, year, 1000.0f, 0);
	}
	
	public Movie(String title, int year, float rating) {
		this(title, year, rating, 0);
	}
	
	public Movie(String title, int year, float rating, int comparisons) {
		setTitle(title);
		setYear(year);
		setRating(rating);
		setComparisons(comparisons);
	}
	
	/*
	 * Accessors and Mutators
	 */
	
	//Accessors
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public float getRating() {
		return rating;
	}
	
	public int getComparisons() {
		return comparisons;
	}
	
	//Mutators
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public void setComparisons(int comparisons) {
		this.comparisons = comparisons;
	}
	
	/*
	 * Other methods
	 */
	
	public String toString() {
		return this.getTitle() + " (" + this.getYear() + ")" + " " + (int)this.getRating();
	}
	
	@Override
	public int compareTo(Movie movie) {
		int value = (int) (this.getRating() - movie.getRating());
		if (value == 0) {
			//if ratings are equal then compare titles in reverse order (this is opposite to the compareTo specification)
			value = -this.getTitle().compareTo(movie.getTitle());
		}
		return value;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
