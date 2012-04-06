package aroliveira.lab.structure.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegerFieldTest {

	@Test
	public void happyDay() {
		assertEquals("expected number converted", new Integer(10), new IntegerField("10").getValue());
	}
}
