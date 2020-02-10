import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import enigma.core.Enigma;

public class Process {

	// needed arrays and counters
	public static Star[] stars = new Star[99];
	public static int starCounter = 0;
	public static Director[] directors = new Director[99];
	public static int directorCounter = 0;
	public static Movie[] movies = new Movie[99];
	public static int movieCounter = 0;
	public static Saloon[] saloons = new Saloon[3];
	private double totalRevenue = 0;
	private Scanner scan;

	// counter increaser start--
	public void incrementStarCounter() {
		starCounter++;

	}

	public void incrementMovieCounter() {
		movieCounter++;

	}

	public void incrementDirectorCounter() {
		directorCounter++;

	}

	// --end
	public enigma.console.Console cn = Enigma.getConsole("CineCENG Administration System");// console name

	public void Command(String command) throws IOException {// all the commands that given

		// command line reading
		String[] commands = command.split(";"); // string splitter

		if ("load".equals(commands[0])) {
			load(commands[1]);
		} else if ("addStar".equals(commands[0])) {
			addStar(commands[1], commands[2], commands[3]);
		} else if ("addDirector".equals(commands[0])) {
			addDirector(commands[1], commands[2], commands[3]);
		} else if ("addMovie".equals(commands[0])) {
			if (commands.length == 8) {
				addMovie(commands[1], commands[2], Double.parseDouble(commands[3].toString()),
						Integer.parseInt(commands[4].toString()), Integer.parseInt(commands[5].toString()),
						Integer.parseInt(commands[6].toString()), Integer.parseInt(commands[7].toString()));
			} else if (commands.length == 7) {
				addMovie(commands[1], commands[2], Double.parseDouble(commands[3].toString()),
						Integer.parseInt(commands[4].toString()), Integer.parseInt(commands[5].toString()),
						Integer.parseInt(commands[6].toString()), 0);
			}
		} else if ("updateSaloon".equals(commands[0])) {
			updateSaloon(commands[1], Integer.parseInt(commands[2].toString()));
		} else if ("updateShowtime".equals(commands[0])) {
			updateShowtime(Integer.parseInt(commands[1].substring(1)) - 1, commands[2],
					Integer.parseInt(commands[3].toString()));
		} else if ("sellTicket".equals(commands[0])) {
			sellTicket(commands[1], commands[2], commands[3], commands[4]);
		} else if ("cancelTicket".equals(commands[0])) {
			cancelTicket(commands[1], commands[2], commands[3]);
		} else if ("displayActors".equals(commands[0])) {
			displayActors();
		} else if ("displayDirectors".equals(commands[0])) {
			displayDirectors();
		} else if ("displayMovies".equals(commands[0])) {
			displayMovies();
		} else if ("displaySeats".equals(commands[0])) {
			displaySeats(commands[1], commands[2]);
		} else if ("endTheDay".equals(commands[0])) {
			endoftheDay();
		} else if ("displayAll".equals(commands[0])) {
			displayAll();
		}
	}

	// main launcher
	void Launch() throws IOException {

		while (true) {
			String command;// main launcher for main and commanding console
			scan = new Scanner(System.in);
			cn.getOutputStream().print("Command:");
			command = scan.nextLine();
			Command(command);

		}
	}

	// loader with buffered reader
	public void load(String file) throws IOException {
		File openFile = new File(file);
		FileReader fr = new FileReader(openFile);
		BufferedReader br = new BufferedReader(fr);
		String line;// buffered reader

		while ((line = br.readLine()) != null) {
			Command(line); // sending line to command to process
		}
		System.out.println("Text command file read!");
		fr.close();
		br.close();

	}

	// actor adder with object of Star Class
	public void addStar(String name, String bdate, String bplace) throws IOException {
		Star star = new Star(name, bdate, bplace); // sending commands to star object
		stars[starCounter] = star;
		incrementStarCounter();

	}

	// director adder with object of Director Class
	public void addDirector(String name, String bdate, String bplace) throws IOException {
		Director director = new Director(name, bdate, bplace);// sending commands to director object
		directors[directorCounter] = director;
		incrementDirectorCounter();
	}

	// movie adder with object of Movie Class
	public void addMovie(String name, String genre, double duration, int directorID, int actorID1, int actorID2,
			int actorID3) {

		int[] actorIDs = { actorID1, actorID2, actorID3 };
		Movie movie = new Movie(name, genre, duration, directorID, actorIDs);
		movies[movieCounter] = movie;
		incrementMovieCounter();

	}

	// saloon updater
	public void updateSaloon(String saloonID, int movieID) {
		int saloon1 = Integer.parseInt(saloonID.substring(1)); // parsing s"1" part of command
		Saloon saloon = new Saloon(saloon1, movieID); // new saloon
		saloon1 -= 1;// array coordinate of saloon
		saloons[saloon1] = saloon;

	}

	// show time with price and session
	public void updateShowtime(int saloonID, String showTime, int price) {
		int showtime = 3;// nulling showtime
		if (showTime.equals("morning")) { // desion making of showtime
			showtime = 0;
		} else if (showTime.equals("noon")) {
			showtime = 1;
		} else {
			showtime = 2;
		}
		saloons[saloonID].updateShowtime(showtime, price);
	}

	// ticket selling
	public void sellTicket(String SaloonId, String showTime, String seat, String type) {
		int tempShowtime = 3;
		if (showTime.equals("morning")) {
			tempShowtime = 0;
		}

		else if (showTime.equals("noon")) {
			tempShowtime = 1;
		}

		else if (showTime.equals("evening")) {
			tempShowtime = 2;
		}

		int coloumb = 6;
		if (seat.substring(0, 1).equals("A")) {
			coloumb = 0;
		} else if (seat.substring(0, 1).equals("B")) {
			coloumb = 1;
		}

		else if (seat.substring(0, 1).equals("C")) {
			coloumb = 2;
		}

		else if (seat.substring(0, 1).equals("D")) {
			coloumb = 3;
		}

		else if (seat.substring(0, 1).equals("E")) {
			coloumb = 4;
		}

		Random rand = new Random();

		int column = rand.nextInt(5);
		int row = rand.nextInt(5);
		while (saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime).getSeats(column, row)
				.isFull()) {
			column = rand.nextInt(5);
			row = rand.nextInt(5);

		}
		if (saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
				.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).isFull() == false) {
			if (type.equals("regular")) {
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setFull(true);
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setType(2);

				totalRevenue += saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getPrice();

			}

			else if (type.equals("student")) {
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setFull(true);
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setType(1);

				totalRevenue += saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getPrice() / 2;

			}
		} else {
			if (type.equals("regular")) {
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime).getSeats(column, row)
						.setFull(true);
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime).getSeats(column, row)
						.setType(2);
				totalRevenue += saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getPrice();

			}

			else if (type.equals("student")) {
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime).getSeats(column, row)
						.setFull(true);
				saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime).getSeats(column, row)
						.setType(1);
				totalRevenue += saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
						.getPrice() / 2;

			}

		}
	}

	// array displayer for actors, directors, movies and showtime saloons --start
	public void cancelTicket(String SaloonId, String showTime, String seat) {
		int tempShowtime = 3;
		if (showTime.equals("morning")) {
			tempShowtime = 0;
		}

		else if (showTime.equals("noon")) {
			tempShowtime = 1;
		}

		else if (showTime.equals("evening")) {
			tempShowtime = 2;
		}

		int coloumb = 6;
		if (seat.substring(0, 1).equals("A")) {
			coloumb = 0;
		} else if (seat.substring(0, 1).equals("B")) {
			coloumb = 1;
		}

		else if (seat.substring(0, 1).equals("C")) {
			coloumb = 2;
		}

		else if (seat.substring(0, 1).equals("D")) {
			coloumb = 3;
		}

		else if (seat.substring(0, 1).equals("E")) {
			coloumb = 4;
		}

		String temp = saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
				.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).getType().toString();

		if (temp == "student") {
			totalRevenue -= saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime).getPrice()
					/ 2;
			saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
					.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setFull(false);
			saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
					.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setType(3);

		}

		else if (temp == "Regular") {
			saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
					.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setFull(false);
			saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime)
					.getSeats(coloumb, Integer.parseInt(seat.substring(1).toString()) - 1).setType(3);
			totalRevenue -= saloons[Integer.parseInt(SaloonId.substring(1)) - 1].getShowtime(tempShowtime).getPrice()
					/ 2;
		}

	}

	public void displayActors() {
		for (int i = 0; i < stars.length; i++) {
			if (stars[i] != null) {
				System.out.println(stars[i].getName() + " " + stars[i].getbDate() + " " + stars[i].getbPlace());

			}
		}

	}

	public void displayDirectors() {

		for (int i = 0; i < directors.length; i++) {
			if (directors[i] != null) {
				System.out.println(
						directors[i].getName() + " " + directors[i].getbDate() + " " + directors[i].getbPlace());

			}
		}
	}

	public void displayMovies() {

		for (int i = 0; i < movies.length; i++) {
			if (movies[i] != null) {
				System.out.println(movies[i].getName() + " " + movies[i].getGenre() + " " + movies[i].getDuration());

			}
		}

	}

	public void displaySeats(String commands, String commands2) {
		System.out.println(commands + " - " + commands2);
		int tempShowtime = 3;
		if (commands2.equals("morning")) {
			tempShowtime = 0;
		}

		else if (commands2.equals("noon")) {
			tempShowtime = 1;
		}

		else if (commands2.equals("evening")) {
			tempShowtime = 2;
		}
		
		

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (saloons[Integer.parseInt(commands.substring(1)) - 1].getShowtime(tempShowtime).getSeats(i, j)
						.isFull()) {
					System.out.print("X");
				} else {
					System.out.print("O");
				}
			}
			System.out.println();
		}

	}

	public void endoftheDay() {
		System.out.println(">Total revenue of CineCeng: " + totalRevenue + "$");

		int tempShowtime = 3;
		int tempSaloon = 0;
		int fullseatCounter = 0;
		int[] countedseats = new int[9];
		int temp = 0;
		int temp2 = 0;
		int localtemp = 0;
		
		
		
		
		
		for (int i = 0; i < 9; i++) {

			if (i %3 == 0) {
				tempShowtime = 0;
			}

			else if (i%3 == 1) {
				tempShowtime = 1;
			}

			else if (i%3 == 2) {
				tempShowtime = 2;
			}

			for (int i1 = 0; i1 < 5; i1++) {
				for (int j = 0; j < 5; j++) {
					if (saloons[tempSaloon].getShowtime(tempShowtime).getSeats(i1, j).isFull()) {
						fullseatCounter++;
//						System.out.print("X");
					} else {
//						System.out.print("O");
					}
				}
			}
			countedseats[temp] = fullseatCounter;
			temp++;
			tempSaloon++;
			if (tempSaloon>2) {
				tempSaloon=0;
			}
			fullseatCounter = 0;

			for (int j = 0; j < countedseats.length; j++) {
				int tempered = 0;
				tempered ++;
				if (countedseats[j] > localtemp) {
					localtemp = countedseats[j];
					temp2 = tempered;
				}
				
			}
			
			if (i>7) {
				if (temp2 < 3) {
					if (temp2 == 1) {
						System.out.println(">The most-filled showtime :" + "S" + temp2 + "-morning");
					} else if (temp2 == 2) {
						System.out.println(">The most-filled showtime :" + "S" + temp2 + "-noon");
					} else if (temp2 == 3) {
						System.out.println(">The most-filled showtime :" + "S" + temp2 + "-evening");
					}

				} else if (2 < temp2 && temp2 < 6) {
					int module = temp2 % 3;
					if (module == 1) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-morning");
					} else if (module == 2) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-noon");
					} else if (module == 0) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-evening");
					}

					if (module == 2) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-morning");
					} else if (module == 2) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-noon");
					} else if (module == 0) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-evening");
					}
				}

				else if (temp2 > 5 && temp2 < 9) {
					int module = temp2 % 3;
					if (module == 1) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-morning");
					} else if (module == 2) {
						System.out.println(">The most-filled showtime :" + "S" + module + "-noon");
					} else if (module == 0) {
						System.out.println(">The most-filled showtime :" + "S" + module  + "-evening");
						
					}
				}
				System.out.println("MORNING");
				
				System.out.println("S1");
				displaySeats("s1", "morning");
				System.out.println("S2");
				displaySeats("s2", "morning");
				System.out.println("S3");
				displaySeats("s3", "morning");
				System.out.println();
				System.out.println("NOON");
				System.out.println("S1");
				displaySeats("s1", "noon");
				System.out.println("S2");
				displaySeats("s2", "noon");
				System.out.println("S3");
				displaySeats("s3", "noon");
				System.out.println();
				System.out.println("EVENING");
				System.out.println("S1");
				displaySeats("s1", "evening");
				System.out.println("S2");
				displaySeats("s2", "evening");
				System.out.println("S3");
				displaySeats("s3", "evening");
				System.out.println();
				
			}

		}
		
		

	

	}

	// end
	public void displayAll() {
		displayActors();
		System.out.println();
		displayDirectors();
		System.out.println();
		displayMovies();
		System.out.println();
		cn.getTextWindow().setCursorPosition(9, 1);
		System.out.println("MORNING");
		cn.getTextWindow().setCursorPosition(9, 1);
		displaySeats("s1", "morning");
		cn.getTextWindow().setCursorPosition(9, 1);
		displaySeats("s2", "morning");
		cn.getTextWindow().setCursorPosition(9, 7);
		displaySeats("s3", "morning");
		System.out.println();
		
		System.out.println("NOON");
		displaySeats("s1", "noon");
		displaySeats("s2", "noon");
		displaySeats("s3", "noon");
		
		System.out.println();
		System.out.println("EVENING");
		displaySeats("s1", "evening");
		displaySeats("s2", "evening");
		displaySeats("s3", "evening");
		System.out.println();
	}

}
