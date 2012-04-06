package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import aroliveira.lab.structure.util.Type;

public class FieldTest {

	@Test(expected = RuntimeException.class)
	public void garanteAObrigatoriedadeDoNome() {
		new Field(null, Type.INTEGER);
	}

	@Test(expected = RuntimeException.class)
	public void garanteAObrigatoriedadeDoTipo() {
		new Field("Field", null);
	}

	@Test(expected = RuntimeException.class)
	public void garanteQueONomeNaoPodeEstarVazio() {
		new Field("", Type.INTEGER);
	}

	@Test
	public void happyDay() {
		Field field = new Field("Field test", Type.INTEGER);

		assertNotNull("Should exists a created Field", field);
		assertEquals("Field name", "Field test", field.getName());
		assertEquals("Field type",Type.INTEGER, field.getType());
		
		assertEquals("Print of field", "Field: 'Field test' Type: 'INTEGER'", field.toString());
	}
}