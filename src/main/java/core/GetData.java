package core;

import java.util.List;

public class GetData {
	String fileName;
	Object[][] data;
	public GetData() {
	}
	public Object[][] getData(String fileName) throws Exception {
	List<String[]> lines = ReadCsvFile.readAllLines(fileName);
	lines.remove(0);
	Object[][] data = new Object[lines.size()][lines.get(0).length];
	int index = 0;
	for(String[] line : lines) {
		data[index] = line;
		index++;
	}
	return data;
	}
}
