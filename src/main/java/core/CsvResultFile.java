package core;

import java.util.ArrayList;
import java.util.List;

public class CsvResultFile {
	ArrayList<String> outputHeaders;
	ArrayList<ArrayList<String>> outputData;
	public CsvResultFile(ArrayList<String> outputHeaders, ArrayList<ArrayList<String>> outputData) {
		this.outputHeaders = outputHeaders;
		this.outputData = outputData;
		
	}
	public void csvResultFile(String outPutCsvFile) {
	List<String[]> data = new ArrayList<String[]>();
	for(ArrayList<String> row: outputData) {
		String[] row_data = new String[row.size()];
		for(int i= 0;i<row.size();i++) {
			row_data[i] = row.get(i);
		}
		data.add(row_data);
	}
	String[] headers = new String[outputHeaders.size()];
	for(int i= 0;i<outputHeaders.size();i++) {
		headers[i] = outputHeaders.get(i);
	}
	WriteCsvFile.writeDataLineByLine(outPutCsvFile, data, headers);
}
}
