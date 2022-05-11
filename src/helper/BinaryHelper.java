package helper;

import model.Author;
import model.Book;
import util.CommonUtil;

public class BinaryHelper {

	public String getAggregatedBinaryString(Author author) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(author.getId()).append(",")
			.append(CommonUtil.convertStringToBinary(author.getFirstName()))
			.append(",")
			.append(CommonUtil.convertStringToBinary(author.getLastName()))
			.append(",")
			.append(CommonUtil.convertStringToBinary(author.getAddress()))
			.append(",")
			.append(CommonUtil.convertStringToBinary(author.getEmail()));

		return sb.toString();
	}

	public String getAggregatedBinaryString(Book book) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(book.getId())
			.append(",")
			.append(CommonUtil.convertStringToBinary(book.getName()))
			.append(",")
			.append(CommonUtil.convertStringToBinary(book.getGenre()))
			.append(",")
			.append(book.getAuthorId());

		return sb.toString();
	}

	public String getStringFromBinary(String binary) {
		StringBuilder sb = new StringBuilder();
	
		for (int index = 0; index < binary.length(); index += 8) {
			char character = (char) Integer.parseInt(binary.substring(index, index + 8), 2);
			sb.append(character);
		}

		return sb.toString();
	}
}
