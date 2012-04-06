package aroliveira.lab.structure.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import aroliveira.lab.structure.entities.layout.Field;

public class TypedFieldConverterTest {

	@Test
	public void conversaoParaINTEGERHappyDay() {
		final Field field = new Field("integer", Type.INTEGER);
		TypedFieldConvertable convertable = TypedFieldConverter.convertToCorrectType("87", field);
		
		assertEquals("Conversion of INTEGER field",new Integer(87), convertable.getValue());
	}

	@Test
	public void conversaoParaCURRENCYHappyDay() {
		final Field field = new Field("currency", Type.CURRENCY);
		TypedFieldConvertable convertable = TypedFieldConverter.convertToCorrectType("-87,87", field);
		
		assertEquals("Conversion of CURRENCY field",new Long("-8787"), convertable.getValue());
	}	
	
	@Test
	public void conversaoParaDATEHappyDay() {
		
		final Field field = new Field("date", Type.DATE);
		TypedFieldConvertable convertable = TypedFieldConverter.convertToCorrectType("11/11/2011", field);
		
		final Calendar convertedDate = GregorianCalendar.getInstance();
		convertedDate.setTime((Date) convertable.getValue());
		
		assertEquals("DAY of converted date",11, convertedDate.get(GregorianCalendar.DAY_OF_MONTH));
		assertEquals("MONTH of converted date",10, convertedDate.get(GregorianCalendar.MONTH));
		assertEquals("YEAR of converted date",2011, convertedDate.get(GregorianCalendar.YEAR));
	}
	
	@Test
	public void conversaoParaTEXTHappyDay() {
		final String expectedText = "PAGTO EM CONTA CORRENTE ";
		final Field field = new Field("text", Type.TEXT);
		TypedFieldConvertable convertable = TypedFieldConverter.convertToCorrectType(expectedText, field);
		
		assertEquals("Conversion of TEXT field", expectedText, convertable.getValue());
	}
}
