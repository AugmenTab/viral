package edu.cnm.deepdive.viral.generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public abstract class CsvReader {

  private CsvReader() {

  }

  public static List<CSVRecord> parseCSV(InputStream file) throws IOException {
    InputStreamReader r = new InputStreamReader(file);
    return CSVFormat.DEFAULT.parse(r).getRecords();
  }

}
