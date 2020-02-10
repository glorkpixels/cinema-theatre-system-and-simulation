
public class Movie {

	private String name;
	private String genre;
	private double duration;
	private int directorID;
	private int[] actorID;

	//movie constructor
	public Movie(String name, String genre, double duration, int directorID, int[] actorID) {

		this.name = name;
		this.genre = genre;
		this.duration = duration;
		this.directorID = directorID;
		this.actorID = actorID;
	}
	//getter-setter --starts
	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public double getDuration() {
		return duration;
	}

	public int getDirectorID() {
		return directorID;
	}

	public int[] getActorID() {
		return actorID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public void setDirectorID(int directorID) {
		this.directorID = directorID;
	}

	public void setActorID(int[] actorID) {
		this.actorID = actorID;
	}
	//ends--
}
