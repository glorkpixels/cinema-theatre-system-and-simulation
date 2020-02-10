
public class Seat {

	private String type;
	private String seatID;
	private boolean isFull = false; //seat checker
	public Seat() {
		
	}
	//seat getter-setters --start
	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	public String getSeatID() {
		return seatID;
	}
	public String getType() {
		return type;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public void setType(int type) {
		if (type == 1) {
			this.type = "Student";
		}
		else if(type == 2) {
			this.type = "Regular";
		}
	}
	
	//ends--
}