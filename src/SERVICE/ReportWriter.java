package SERVICE;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReportWriter {

	/**
	 * Writes the given lines of text to a report file.
	 * 
	 * @param filePath Path to the file where the report will be written.
	 * @param lines    List of lines (text) to be written to the report.
	 */
	public static void writeReport(String filePath, List<? extends CharSequence> lines) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (CharSequence line : lines) {
				writer.write(line.toString());
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error writing the report: " + e.getMessage());
		}
	}

	/**
	 * Reads the content of the report file and returns it as a single string.
	 * 
	 * @param filePath Path to the file to be read.
	 * @return The content of the file as a string.
	 */
	public static String readReport(String filePath) {
		try {
			return Files.readString(Paths.get(filePath));
		} catch (IOException e) {
			System.out.println("Error reading the report: " + e.getMessage());
			return "";
		}
	}
}
