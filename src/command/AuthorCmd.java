package command;

public class AuthorCmd {
	
	private int id;
	
	private int index;
	
	private int length;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public  static AuthorCmd initialize(String information) {
		AuthorCmd  authorCmd = new  AuthorCmd();
		String[] informations = information.split(",");
		
		authorCmd.setId(Integer.parseInt(informations[0]));
		authorCmd.setIndex(Integer.parseInt(informations[1]));
		authorCmd.setLength(Integer.parseInt(informations[2]));
		
		return authorCmd;
	}
}
