package aroliveira.lab.structure.util;

import aroliveira.lab.structure.entities.layout.Field;

public class TypedFieldConverter {

	public static TypedFieldConvertable convertToCorrectType(String readedField, Field field){

		switch (field.getType()) {

		case TEXT:
				return new TextField(readedField);

		case CURRENCY:
				return new CurrencyField(readedField);

		case DATE:
				return new DateField(readedField);

		case INTEGER:
				return new IntegerField(readedField);

		default:
			throw new RuntimeException("Unexpected type to convert");
		}
	}
}