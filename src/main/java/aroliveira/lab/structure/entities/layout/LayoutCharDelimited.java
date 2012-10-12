package aroliveira.lab.structure.entities.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;

import aroliveira.lab.structure.util.TypedFieldConvertable;
import aroliveira.lab.structure.util.TypedFieldConverter;

@Entity(name = "LAYOUT_CHAR_DELIMITED")
public class LayoutCharDelimited extends Layout<LayoutFieldCharDelimited> {

	private static final long serialVersionUID = -8199329800205974413L;

	@Column(name = "CHAR_DELIMITER")
	String charDelimiter;
	
	public LayoutCharDelimited(String name, String charDelimiter) {
		this(name, charDelimiter, new ArrayList<LayoutFieldCharDelimited>());
	}

	public LayoutCharDelimited(String name, String charDelimiter, 
			List<LayoutFieldCharDelimited> layoutFields) {
		super(layoutFields, name);
		setCharDelimiter(charDelimiter);
	}

	@Override
	protected void checkFields(List<LayoutFieldCharDelimited> layoutFields) {

		super.checkFields(layoutFields);
		
		if (layoutFields == null) return;

		Collections.sort(layoutFields);
		
		checkIfExistsFieldsWithSameIndex(layoutFields);
	}

	private void checkIfExistsFieldsWithSameIndex(
			List<LayoutFieldCharDelimited> layoutFields) {

		if (layoutFields.isEmpty()) return;

		LayoutFieldCharDelimited field = layoutFields.get(0);

		for (int i = 1; i < layoutFields.size(); i++) {

			if (layoutFields.get(i).getIndex()
					.equals(field.getIndex()))
				throw new RuntimeException(
						"cannot exists two fields with same index");

		}
	}

	@Override
	public void addLayoutField(LayoutFieldCharDelimited layoutField) {
		List<LayoutFieldCharDelimited> aux = new ArrayList<LayoutFieldCharDelimited>(
				getLayoutFields());
		aux.add(layoutField);
		Collections.sort(aux);
		checkIfExistsFieldsWithSameIndex(aux);

		layoutFields.add(layoutField);
		Collections.sort(layoutFields);
	}

	
	public String getCharDelimiter() {
		return charDelimiter;
	}
	
	public void setCharDelimiter(String delimiter) {

		if ( delimiter == null ) throw new RuntimeException("delimiter cannot be null");
		
		if ( delimiter.isEmpty() ) throw new RuntimeException("delimiter cannot be empty");
		
		this.charDelimiter = delimiter;
	}	


	
	@Override
	public Map<LayoutField, TypedFieldConvertable> convertLine(String readLine) {

		Map<LayoutField, TypedFieldConvertable> mapFields = new HashMap<LayoutField, TypedFieldConvertable>();

		List<String> decodedLine = Arrays.asList(readLine.split(getCharDelimiter()));

		if (decodedLine.size() < layoutFields.size())
			throw new RuntimeException("Line cannot be parsed");
		
		for (LayoutFieldCharDelimited layoutField : layoutFields) {
			TypedFieldConvertable fieldConverted = TypedFieldConverter
					.convertToCorrectType(
							decodedLine.get(layoutField.getIndex() - 1),
							layoutField.getField());

			mapFields.put(layoutField, fieldConverted);
		}

		return mapFields;
	}
}
