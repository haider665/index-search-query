package util;

import model.Author;
import model.Book;

public class QueryParser {
	
	public static Author getAuthorInformation(String query) {
		String[] informations = extractInformation(query);

		return new Author(
				Integer.parseInt(informations[0]), 
				informations[1],
				informations[2],
				informations[3],
				informations[4]
		);
	}
	
	public static Book getBookInformation(String query) {
		String[] informations = extractInformation(query);
		
		return new Book(
				Integer.parseInt(informations[0]), 
				informations[1],
				informations[2],
				Integer.parseInt(informations[3].trim())
		);
	}

	private static String[] extractInformation(String query) {
		int startingIndex = query.indexOf('(') + 1;
		int endingIndex = query.indexOf(')');

		return query.substring(startingIndex, endingIndex).split(",");
	}
}
