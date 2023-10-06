import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVImporter {
		public static ArrayList<String []> importData(String filename) {
				try {
						FileReader fr = new FileReader(filename);
						CSVReader reader = new CSVReader(fr);
						ArrayList<String []> data = new ArrayList (reader.readAll());
						return data;
				}
				catch (Exception e) {
						//output info about errors
						e.printStackTrace();
				}
				return null;
		}
}