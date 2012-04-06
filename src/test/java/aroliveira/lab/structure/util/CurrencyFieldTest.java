package aroliveira.lab.structure.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CurrencyFieldTest {

	@Test
	public void happyDayInformingPointWithNegativeValue() {
		
		Long convertedValue = new CurrencyField("-10.43").getValue();
		assertEquals("Converted currency value", new Long("-1043"), convertedValue);
	}
	
	@Test
	public void happyDayInformingCommonWithNegativeValue() {
		
		Long convertedValue = new CurrencyField("-145,86").getValue();
		assertEquals("Converted currency value", new Long("-14586"), convertedValue);
	}
	
	@Test
	public void happyDayInformingPoint() {
		
		Long convertedValue = new CurrencyField("1.87").getValue();
		assertEquals("Converted currency value", new Long("187"), convertedValue);
	}
	
	@Test
	public void happyDayInformingCommon() {
		
		Long convertedValue = new CurrencyField("7,00").getValue();
		assertEquals("Converted currency value", new Long("700"), convertedValue);
	}
}