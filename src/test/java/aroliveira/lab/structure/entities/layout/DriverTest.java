package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import aroliveira.lab.structure.util.CurrencyField;
import aroliveira.lab.structure.util.DateField;
import aroliveira.lab.structure.util.TextField;
import aroliveira.lab.structure.util.Type;
import aroliveira.lab.structure.util.TypedFieldConvertable;

public class DriverTest {

	@Test
	public void createHappyDay() {
		Driver driver = new Driver(getLayout(), "onlyOneLine.txt");
		assertNotNull("A driver must be created",driver);
	}
	
	@Test(expected=RuntimeException.class)
	public void tentaCriarSemInformarOLayout() {
		new Driver(null, "onlyOneLine.txt");
	}
	
	@Test(expected=RuntimeException.class)
	public void tentaCriarSemInformarONomeDoArquivo() {
		new Driver(getLayout(), null);
	}
	
	@Test(expected=RuntimeException.class)
	public void tentaCriarInformandoONomeDoArquivoVazio() {
		new Driver(getLayout(), "");
	}
	
	@Test
	public void leUmRegistroHappyDay() {

		Map<String, TypedFieldConvertable> expectedFields = new HashMap<String, TypedFieldConvertable>();
		expectedFields.put("Statement Date",new DateField("03/11/2011"));
		expectedFields.put("Statement Description",new TextField("RSHOP-AUTO POSTO -02/11 "));
		expectedFields.put("Statement Value",new CurrencyField("-50,00"));

		Driver driver = new Driver(getLayout(), "onlyOneLine.txt");
		
		Map<String, TypedFieldConvertable> fields = driver.nextRegister();
		
		assertEquals("Expected fields", expectedFields, fields);
	}
	
	private Layout getLayout() {
		
		Field statamentDateField = new Field("Statement Date", Type.DATE);
		Field statementDescriptionField = new Field("Statement Description", Type.TEXT);
		Field statementValueField = new Field("Statement Value", Type.CURRENCY);
		
		LayoutField statamentDate = new LayoutField(statamentDateField, false, 0, 10);
		LayoutField statamentDescription = new LayoutField(statementDescriptionField, false, 11, 24);
		LayoutField statamentValue = new LayoutField(statementValueField, false, 36, 6);
		
		List<LayoutField> fields = new ArrayList<LayoutField>();		
		fields.add(statamentDate);
		fields.add(statamentDescription);
		fields.add(statamentValue);

		return new Layout(fields, "Test Layout");
	}
}
