package aroliveira.lab.structure.util;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateFieldTest {

	@Test
	public void happyDay() {
				
		final Calendar convertedDate = GregorianCalendar.getInstance();
		convertedDate.setTime(new DateField("01/03/1986").getValue());
				
		assertEquals("DAY of converted date",1, convertedDate.get(GregorianCalendar.DAY_OF_MONTH));
		assertEquals("MONTH of converted date",2, convertedDate.get(GregorianCalendar.MONTH));
		assertEquals("YEAR of converted date",1986, convertedDate.get(GregorianCalendar.YEAR));
	}

	@Test
	public void happyDayInformingTheFormat() {

		final Calendar convertedDate = GregorianCalendar.getInstance();
		convertedDate.setTime(new DateField("86@01+03").getValueFormatted(new SimpleDateFormat("yy@dd+MM")));
				
		assertEquals("DAY of converted date",1, convertedDate.get(GregorianCalendar.DAY_OF_MONTH));
		assertEquals("MONTH of converted date",2, convertedDate.get(GregorianCalendar.MONTH));
		assertEquals("YEAR of converted date",1986, convertedDate.get(GregorianCalendar.YEAR));
	}
}
