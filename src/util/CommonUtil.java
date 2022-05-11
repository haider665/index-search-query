package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;

import command.AuthorCmd;
import command.BookCmd;
import helper.BinaryHelper;
import model.Author;
import model.Book;

public class CommonUtil {
	
	private final static String path = "./resources/application.properties";

	public static String convertStringToBinary(String input) {
		StringBuilder result = new StringBuilder();
		char[] chars = input.trim().toCharArray();

		for (char aChar : chars) {
			result.append(String.format("%8s", Integer.toBinaryString(aChar)).replaceAll(" ", "0"));
		}
	    
		return result.toString();
	}
	
	public static String getIndexInformations(Author author ,String ss, long index) {
		return String.join(",", numberToString(author.getId()), numberToString(index), numberToString(ss.length())).concat(System.lineSeparator()) ;
	}
	
	public static String getIndexInformations(Book book ,String ss, long index) {
		return String.join(",", book.getGenre(), numberToString(index), numberToString(ss.length()), numberToString(book.getAuthorId()).concat(System.lineSeparator())) ;
	}
	
	public static String numberToString(long number) {
		return Long.toString(number);
	}
	
	public static Author getAuthor(AuthorCmd authorCmd) throws IOException {
		Author author = new Author();		
		FileInputStream ips = new FileInputStream(path);
		Properties prop = new Properties();
		
		prop.load(ips);
		BinaryHelper binaryHelper = new BinaryHelper();
		RandomAccessFile raf = new RandomAccessFile(prop.getProperty("authorDb"), "r"); 
		
		raf.seek(authorCmd.getIndex());
		byte[] bytes = new byte[authorCmd.getLength()];
		raf.readFully(bytes);
		
		String[] newArr = new String(bytes).split(",");
		
		author.setId(Integer.parseInt(newArr[0]));
		author.setFirstName(binaryHelper.getStringFromBinary(newArr[1]));
		author.setLastName(binaryHelper.getStringFromBinary(newArr[2]));
		author.setAddress(binaryHelper.getStringFromBinary(newArr[3]));
		author.setEmail(binaryHelper.getStringFromBinary(newArr[4]));
		
		return author;
	}
	
	public static String getSelectedColumns(String columns, Author author) {
		String[] column = columns.split(",");
		StringBuilder sb = new StringBuilder();
		sb.append("Author [");
		
		for(String col: column) {
			if(col.equalsIgnoreCase("id")) {
				sb.append("id=").append(author.getId()).append(",");
			}
			if(col.equalsIgnoreCase("firstName")) {
				sb.append("firstName=").append(author.getFirstName()).append(",");
			}
			if(col.equalsIgnoreCase("lastName")) {
				sb.append("lastName=").append(author.getLastName()).append(",");
			}
			if(col.equalsIgnoreCase("address")) {
				sb.append("address=").append(author.getAddress()).append(",");
			}
			if(col.equalsIgnoreCase("email")) {
				sb.append("email=").append(author.getEmail()).append(",");
			}
		}
		
		return sb.append("]").toString();
	}
}
