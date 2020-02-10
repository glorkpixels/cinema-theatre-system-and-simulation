
public class Star {
	private String name;
	private String bDate;
	private String bPlace;
	//star constructor
	Star(String name, String bDate, String bPlace) {
		this.name = name;
		this.bDate = bDate;
		this.bPlace = bPlace;
	}
	//getter-setter --starts
	public String getName() {
		return name;
	}

	public String getbDate() {
		return bDate;
	}

	public String getbPlace() {
		return bPlace;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public void setbPlace(String bPlace) {
		this.bPlace = bPlace;
	}
	//ends--
}
