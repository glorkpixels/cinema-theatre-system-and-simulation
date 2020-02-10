
public class Showtime {

	private Seat[][] seats = new Seat[5][5];
	private Movie movie;
	private int price;

	// showtime saloon seats
	Showtime() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Seat seat = new Seat();
				seats[i][j] = seat;
			}
		}
	}

	// getter setters --start
	public Seat getSeats(int coloumb, int row) {
		return seats[coloumb][row];
	}

	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	// getter setter end--
}
