package model;

public class Book {
	
	private int id;
	
	private String name;
	
	private String genre;
	
	private int authorId;

	public Book(int id, String name, String genre, int authorId) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", genre=" + genre + ", authorId=" + authorId + "]";
	}
}
