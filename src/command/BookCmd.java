package command;

public class BookCmd {
	
	private String genra;
	
	private int index;
	
	private int length;
	
	private int authorId;

	public String getGenra() {
		return genra;
	}

	public void setGenra(String genra) {
		this.genra = genra;
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

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public  static BookCmd initialize(String information) {
		BookCmd  bookCmd = new  BookCmd();
		String[] informations = information.split(",");
		
		bookCmd.setGenra(informations[0].trim());
		bookCmd.setIndex(Integer.parseInt(informations[1]));
		bookCmd.setLength(Integer.parseInt(informations[2]));
		bookCmd.setAuthorId(Integer.parseInt(informations[3]));
		
		return bookCmd;
	}
}
