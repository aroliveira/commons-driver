package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.*;

import org.junit.Test;

public class LayoutFieldCharDelimitedTest extends LayoutFieldTest {

	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoField() {
		new LayoutFieldCharDelimited(null, false, 0);
	}

	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoIndex(){
		new LayoutFieldCharDelimited(getDefaultField(), false, null);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoValorNegativoParaIndex(){
		new LayoutFieldCharDelimited(getDefaultField(), false, -1);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoValorZeradoParaIndex(){
		new LayoutFieldCharDelimited(getDefaultField(), false, 0);
	}

	@Test
	public void happyDay() {

		LayoutFieldCharDelimited field = new LayoutFieldCharDelimited(getDefaultField(), false, 1);
		
		assertNotNull("Should exists a layoutField", field);
		assertEquals("field of LayoutField",getDefaultField(), field.getField());
		assertEquals("Nullable of LayoutField", false, field.getNullable());
		assertEquals("Index of LayoutField", new Integer(1), field.getIndex());
		
		assertEquals("Print of LayoutField", "Field: 'Field test' Type: 'INTEGER' Index: 1 [Nullable]\n", field.toString());		
	}

	@Test
	public void garanteOPrintDeUmLayoutFieldObrigatorio() {
		
		LayoutFieldCharDelimited field = new LayoutFieldCharDelimited(getDefaultField(), true, 1);
		assertEquals("Print of LayoutField", "Field: 'Field test' Type: 'INTEGER' Index: 1 [Obligated]\n", field.toString());
	}
}