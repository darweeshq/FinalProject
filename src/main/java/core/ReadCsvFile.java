
	package core;

	import java.io.FileReader;

	import java.util.ArrayList;
	import java.util.List;

	import com.opencsv.CSVReader;

	public class ReadCsvFile {

		public static List<String[]> readAllLines(String filePath) throws Exception {
			CSVReader reader = new CSVReader(new FileReader(filePath), ',');

			List<String[]> data = new ArrayList<String[]>();

			// read line by line
			String[] record = null;

			while ((record = reader.readNext()) != null) {
				data.add(record);
				
			}		
			reader.close();
			return data;
		}
//		
//		public static void main(String[] args) throws Exception {
//			List<String[]> data = ReadCsvFile.readAllLines("inputBooking.csv");
//			System.out.println(data.get(0)[0]);
//		}
	}

