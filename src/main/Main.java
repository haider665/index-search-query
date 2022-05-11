package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

import command.AuthorCmd;
import command.BookCmd;
import helper.BinaryHelper;
import model.Author;
import model.Book;
import util.CommonUtil;
import util.QueryParser;

public class Main {
	private static final String PATH = "./resources/application.properties";

	private static FileInputStream ips;
	private static Properties prop;

	public static void main(String[] args) throws IOException {
		ips = new FileInputStream(PATH);
		prop = new Properties();
		prop.load(ips);

		File dml = new File(prop.getProperty("dml"));
		Scanner dmlSc = new Scanner(dml);
		while (dmlSc.hasNext()) {
			String query = dmlSc.nextLine();
			if (query.contains("author")) {
				insertIntoAuthor(query);
			} else {
				insertIntoBook(query);
			}
		}

		String query = "select id,firstname from author join book on book.author_id = author.id where book.genre = comedy";
		String[] tokens = query.split(" ");
		String tableName = query.substring(query.lastIndexOf(" ") + 1);

		boolean hasJoin = query.toUpperCase().contains("JOIN");
		boolean selectAll = query.contains("*");

		if (!hasJoin) {
			if (tableName.equalsIgnoreCase("author")) {
				File authorIdxFile = new File(prop.getProperty("authorIdx"));
				Scanner authorIdxSc = new Scanner(authorIdxFile);

				while (authorIdxSc.hasNext()) {
					AuthorCmd cmd = AuthorCmd.initialize(authorIdxSc.nextLine());
					Author author = CommonUtil.getAuthor(cmd);
					if (selectAll) {
						System.out.println(author);
					} else {
						System.out.println(CommonUtil.getSelectedColumns(tokens[1], author));
					}
				}
			}

		} else {
			String genre = tokens[tokens.length - 1];
			File bookIdxFile = new File(prop.getProperty("bookIdx"));
			Scanner bookIdxSc = new Scanner(bookIdxFile);
			Set<Integer> authorIds = new HashSet<>();

			while (bookIdxSc.hasNext()) {
				BookCmd bookCmd = BookCmd.initialize(bookIdxSc.nextLine());

				if (bookCmd.getGenra().equalsIgnoreCase(genre)) {
					authorIds.add(bookCmd.getAuthorId());
				}

			}

			File authorIdxFile = new File(prop.getProperty("authorIdx"));
			Scanner authorIdxSc = new Scanner(authorIdxFile);

			while (authorIdxSc.hasNext()) {
				AuthorCmd cmd = AuthorCmd.initialize(authorIdxSc.nextLine());
				
				if (!authorIds.contains(cmd.getId())) {
					continue;
				}

				Author author = CommonUtil.getAuthor(cmd);

				if (selectAll) {
					System.out.println(author);
				} else {
					System.out.println(CommonUtil.getSelectedColumns(tokens[1], author));
				}
			}

		}
	}

	public static void insertIntoAuthor(String query) throws IOException {
		BinaryHelper binaryHelper = new BinaryHelper();
		File authorDbFile = new File(prop.getProperty("authorDb"));

		prop.load(ips);
		long fileIndex = authorDbFile.length();
		Author author = QueryParser.getAuthorInformation(query);
		String binaryForm = binaryHelper.getAggregatedBinaryString(author);

		Files.writeString(Path.of(prop.getProperty("authorDb")), binaryForm, StandardOpenOption.APPEND);
		Files.writeString(Path.of(prop.getProperty("authorIdx")),
				CommonUtil.getIndexInformations(author, binaryForm, fileIndex), StandardOpenOption.APPEND);
	}

	public static void insertIntoBook(String query) throws IOException {
		BinaryHelper binaryHelper = new BinaryHelper();
		File bookDbFile = new File(prop.getProperty("bookDb"));
		long bookFileIndex = bookDbFile.length();

		prop.load(ips);
		Book book = QueryParser.getBookInformation(query);
		String binaryForm = binaryHelper.getAggregatedBinaryString(book);

		Files.writeString(Path.of(prop.getProperty("bookDb")), binaryForm, StandardOpenOption.APPEND);
		Files.writeString(Path.of(prop.getProperty("bookIdx")),
				CommonUtil.getIndexInformations(book, binaryForm, bookFileIndex), StandardOpenOption.APPEND);
	}
}
