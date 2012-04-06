package aroliveira.lab.structure.util;

public class IntegerField extends TypedField {

	public IntegerField(String field) {
		super(field);
	}

	public Integer getValue() {
		return new Integer(getReadedField());
	}

	@Override
	public String toString() {
		return "Integer field: " + getValue();
	}
}
