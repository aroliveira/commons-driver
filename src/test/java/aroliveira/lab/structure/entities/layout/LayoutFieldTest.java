package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import aroliveira.lab.structure.util.Type;

public class LayoutFieldTest {

	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoField() {
		new LayoutField(null, false, 0, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDaPosicaoInicial() {
		new LayoutField(getField(), false, null, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoTamanho() {
		new LayoutField(getField(), false, 0, null);
	}

	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoValorNegativoParaPosicaoInicial() {
		new LayoutField(getField(), false, -1, 10);
	}

	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoValorNegativoParaOTamanho() {
		new LayoutField(getField(), false, 0, -10);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoTamanhoZerado() {
		new LayoutField(getField(), false, 0, 0);
	}

	@Test
	public void happyDay() {
		LayoutField field = new LayoutField(getField(), false, 0, 10);
		
		assertNotNull("Should exists a layoutField", field);
		assertEquals("field of LayoutField",getField(), field.getField());
		assertEquals("Nullable of LayoutField", false, field.getNullable());
		assertEquals("Initial position of LayoutField", new Integer(0), field.getInitialPosition());
		assertEquals("Size of LayoutField", new Integer(10), field.getSize());
		
		assertEquals("Print of LayoutField", "Field: 'Field test' Type: 'INTEGER' From: 0 Size: 10 [Nullable]\n", field.toString());
	}	
	
	@Test
	public void garanteOPrintDeUmLayoutFieldObrigatorio() {
		
		LayoutField field = new LayoutField(getField(), true, 0, 10);
		assertEquals("Print of LayoutField", "Field: 'Field test' Type: 'INTEGER' From: 0 Size: 10 [Obligated]\n", field.toString());
	}	
	
	private Field getField() {
		return new Field("Field test", Type.INTEGER);
	}
}
