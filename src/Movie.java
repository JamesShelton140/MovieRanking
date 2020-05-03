
public class Movie {
	
	private String title;
	private int year;

	public Movie(String title, int year) {
		setTitle(title);
		setYear(year);
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
	
	//Mutators
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
