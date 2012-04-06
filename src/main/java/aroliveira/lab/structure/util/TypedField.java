package aroliveira.lab.structure.util;

import java.text.Format;

public abstract class TypedField implements TypedFieldConvertable {

	private String readedField;
	
	public abstract String toString();
	
	public TypedField(final String field) {
		setReadedField(field);
	}	

	public Object getValueFormatted(String readedField, Format format) {
		throw new RuntimeException("Not yet implemented");
	}
	
	private void setReadedField(String readedField) {
		this.readedField = readedField;
	}
	
	String getReadedField() {
		return readedField;
	}
	
	public boolean equals(Object obj){
		return this.getValue().equals( ((TypedFieldConvertable) obj).getValue());
	}	
}
