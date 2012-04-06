package aroliveira.lab.structure.util;

public class TextField extends TypedField {

	public TextField(String field) {
		super(field);
	}

	public String getValue() {
		return getReadedField();
	}

	@Override
	public String toString() {
		return "Text field: " + getValue();
	}
}
