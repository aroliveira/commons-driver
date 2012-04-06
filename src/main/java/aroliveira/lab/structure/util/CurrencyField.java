package aroliveira.lab.structure.util;

import java.text.NumberFormat;

public class CurrencyField extends TypedField {

	public CurrencyField(String field) {
		super(field);
	}

	public Long getValue() {
		return new Long(getReadedField().replace(",","").replace(".",""));
	}
	
	@Override
	public String toString() {
		return "Currency field: " + NumberFormat.getCurrencyInstance().format(getValue());
	}	
}
