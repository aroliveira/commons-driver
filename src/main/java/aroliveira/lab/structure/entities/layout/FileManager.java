package aroliveira.lab.structure.entities.layout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

	private File file;

	private FileReader fstream;

	private BufferedReader reader;

	public FileManager(String fileName) {
		
		if ( fileName == null ) throw new RuntimeException("FileName cannot be null");
		
		if ( fileName.isEmpty() ) throw new RuntimeException("FileName cannot be empty");		
		
		file = new File(fileName);
	}

	public String readLine() {
		initializeReader();
		return silentRead();
	}

	private void initializeReader() {
		
		if (reader != null) return;

		if (!file.exists())
			throw new RuntimeException("File " + file.getName() + " not found");		

		try {
			fstream = new FileReader(file);
			reader = new BufferedReader(fstream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> readLines(Integer linesQuantity) {
		
		if ( linesQuantity == null ) throw new RuntimeException("Line quantity must be declareded");
		
		if ( linesQuantity < 0 ) throw new RuntimeException("Cannot inform negative line quantity");
		
		initializeReader();
		List<String> lines = new ArrayList<String>();
		
		int index = 0;
		String line = "";
		
		while (index < linesQuantity) {			
			line = silentRead();
			if ( line == null ) break;
			lines.add(line);
			index ++;
		};		
		
		return lines;
	}
	
	private String silentRead(){
		try {
			return reader.readLine();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}				
	}
}