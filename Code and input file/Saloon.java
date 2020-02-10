
public class Saloon {
	@SuppressWarnings("unused")
	private int saloonID; 
	@SuppressWarnings("unused")
	private int currentMovie;
	@SuppressWarnings("unused")
	private Movie movie; //movie class objects
	private Showtime[] showtime = new Showtime[3]; // three saloons
	private int numberOfFullSeats = 0; //total full seats
	private static String[] mostFilled = new String[2] ;//array for holding most filled count and id of saloon in that time
	public Saloon(int saloonID,int currentMovie) {
		this.saloonID = saloonID;
		movie = Process.movies[currentMovie];
		for (int i = 0; i < 3; i++) {
			showtime[i] = new Showtime();
		}
	}
	//getter-setter --start
	public Showtime getShowtime(int command) {
		return showtime[command];
	}
	public int getNumberOfFullSeats() {
		return numberOfFullSeats;
	}
	public static String[] getMostFilled() {
		return mostFilled;
	}	
	public void setShowtime(Showtime showtime , int command) {
		this.showtime[command] = showtime; //setter of showtime-- it summons showtime class and matches id of showtiem to that saloon object
	}
	public void setNumberOfFullSeats(int numberOfFullSeats) {
		this.numberOfFullSeats = numberOfFullSeats; //total seats
	}
	public static void setMostFilled(String[] mostFilled) {
		Saloon.mostFilled = mostFilled; //mostfilled saloon finder
	}
	//ends--
	//seat increaser
	public void incrementSeats() {
		numberOfFullSeats++; //seat counter
	}
	//showtimeupdater
	public void updateShowtime(int showTime, int price) {
		showtime[showTime].setPrice(price);//finding showtime id and setting price of it on that showtime
		
	}
	
	
	
	
}
