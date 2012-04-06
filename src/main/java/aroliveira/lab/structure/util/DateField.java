package aroliveira.lab.structure.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateField extends TypedField {

	private static Format defaultFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public DateField(String field) {
		super(field);
	}

	public Date getValue() {
		return getValueFormatted(defaultFormat);
	}
	
	public Date getValueFormatted(Format format) {
		try {
			return (Date)format.parseObject(getReadedField());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	

	@Override
	public String toString() {
		return "Date field: " + DateFormat.getInstance().format(getValue());
	}	
}
