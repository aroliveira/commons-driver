package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

		Map<LayoutField, TypedFieldConvertable> expectedFields = new LinkedHashMap<LayoutField, TypedFieldConvertable>();
		expectedFields.put(getStatementDate(),new DateField("03/11/2011"));
		expectedFields.put(getStatamentDescription(),new TextField("RSHOP-AUTO POSTO -02/11 "));
		expectedFields.put(getStatamentValue(),new CurrencyField("-50,00"));

		Driver driver = new Driver(getLayout(), "onlyOneLine.txt");

		Map<LayoutField, TypedFieldConvertable> fields = driver.nextRegister();
		
		assertEquals("Expected fields", expectedFields, fields);
	}
	
	private Layout<LayoutFieldSizeDelimited> getLayout() {

		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();		
		fields.add(getStatementDate());
		fields.add(getStatamentDescription());
		fields.add(getStatamentValue());

		return new LayoutSizeDelimited("Test Layout", fields);
	}
	
	private LayoutFieldSizeDelimited getStatementDate(){
		
		Field statamentDateField = new Field("Statement Date", Type.DATE);
		return new LayoutFieldSizeDelimited(statamentDateField, false, 0, 10);
	}

	private LayoutFieldSizeDelimited getStatamentDescription(){
		
		Field statementDescriptionField = new Field("Statement Description", Type.TEXT);
		return new LayoutFieldSizeDelimited(statementDescriptionField, false, 11, 24);
	}
	
	private LayoutFieldSizeDelimited getStatamentValue(){
		
		Field statementValueField = new Field("Statement Value", Type.CURRENCY);
		return new LayoutFieldSizeDelimited(statementValueField, false, 36, 6);
	}
}
