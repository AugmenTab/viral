package edu.cnm.deepdive.viral.generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Parses a CSV file and returns the data as a {@code List} of {@code CSVRecord}.
 */
public abstract class CsvReader {

  private CsvReader() {}

  /**
   * Parses a CSV file and returns the data as a {@code List} of {@code CSVRecord}.
   *
   * @param file The file to be parsed as an {@code InputStream}.
   * @return A {@code List} of {@code CSVRecord}.
   * @throws IOException May be thrown if the file is unable to be read.
   */
  public static List<CSVRecord> parseCSV(InputStream file) throws IOException {
    InputStreamReader r = new InputStreamReader(file);
    return CSVFormat.DEFAULT.parse(r).getRecords();
  }

}
