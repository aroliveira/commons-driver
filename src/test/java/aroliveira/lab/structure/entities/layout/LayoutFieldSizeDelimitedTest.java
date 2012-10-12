package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.*;

import org.junit.Test;

public class LayoutFieldSizeDelimitedTest extends LayoutFieldTest {

	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoField() {
		new LayoutFieldSizeDelimited(null, false, 0, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDaPosicaoInicial() {
		new LayoutFieldSizeDelimited(getDefaultField(), false, null, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoTamanho() {
		new LayoutFieldSizeDelimited(getDefaultField(), false, 0, null);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoValorNegativoParaPosicaoInicial() {
		new LayoutFieldSizeDelimited(getDefaultField(), false, -1, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoValorNegativoParaOTamanho() {
		new LayoutFieldSizeDelimited(getDefaultField(), false, 0, -10);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoTamanhoZerado() {
		new LayoutFieldSizeDelimited(getDefaultField(), false, 0, 0);
	}	
	
	@Test
	public void happyDay() {
		
		LayoutFieldSizeDelimited field = new LayoutFieldSizeDelimited(getDefaultField(), false, 0, 10);
		
		assertNotNull("Should exists a layoutField", field);
		assertEquals("field of LayoutField",getDefaultField(), field.getField());
		assertEquals("Nullable of LayoutField", false, field.getNullable());
		assertEquals("Initial position of LayoutField", new Integer(0), field.getInitialPosition());
		assertEquals("Size of LayoutField", new Integer(10), field.getSize());
		
		assertEquals("Print of LayoutField", "Field: 'Field test' Type: 'INTEGER' From: 0 Size: 10 [Nullable]\n", field.toString());
	}
	
	@Test
	public void garanteOPrintDeUmLayoutFieldObrigatorio() {
		
		LayoutField field = new LayoutFieldSizeDelimited(getDefaultField(), true, 0, 10);
		assertEquals("Print of LayoutField", "Field: 'Field test' Type: 'INTEGER' From: 0 Size: 10 [Obligated]\n", field.toString());
	}	
}
