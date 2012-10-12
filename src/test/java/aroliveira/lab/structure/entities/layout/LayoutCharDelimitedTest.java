package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import aroliveira.lab.structure.util.TextField;
import aroliveira.lab.structure.util.Type;
import aroliveira.lab.structure.util.TypedFieldConvertable;

public class LayoutCharDelimitedTest extends LayoutTest {

	@Test
	public void happyDayConvertLine() {
		LayoutField firstField = getDefaultLayoutField(1);
		LayoutField secondField = getDefaultLayoutField(2);

		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add((LayoutFieldCharDelimited) secondField);
		fields.add((LayoutFieldCharDelimited) firstField);		

		HashMap<LayoutField,TypedFieldConvertable> expectedConvertedFields = new HashMap<LayoutField, TypedFieldConvertable>();
		expectedConvertedFields.put(firstField,new TextField("primeiro"));
		expectedConvertedFields.put(secondField,new TextField("segundo"));
		
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";", fields);
		Map<LayoutField, TypedFieldConvertable> convertedLine = layout.convertLine("primeiro;segundo");
		assertEquals("Fields converted from line",expectedConvertedFields, convertedLine);
	}	
	
	@Test
	public void convertOnlyTheFirstWorldOfLine() {
		
		LayoutField firstField = getDefaultLayoutField(1);

		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add((LayoutFieldCharDelimited) firstField);		

		HashMap<LayoutField,TypedFieldConvertable> expectedConvertedFields = new HashMap<LayoutField, TypedFieldConvertable>();
		expectedConvertedFields.put(firstField,new TextField("primeiro"));
		
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";", fields);
		Map<LayoutField, TypedFieldConvertable> convertedLine = layout.convertLine("primeiro;segundo;terceiro");
		assertEquals("Fields converted from line",expectedConvertedFields, convertedLine);
	}
	
	@Test
	public void convertOnlyTheLastWorldOfLine() {
		LayoutField firstField = getDefaultLayoutField(3);

		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add((LayoutFieldCharDelimited) firstField);		

		HashMap<LayoutField,TypedFieldConvertable> expectedConvertedFields = new HashMap<LayoutField, TypedFieldConvertable>();
		expectedConvertedFields.put(firstField,new TextField("terceiro"));
		
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";", fields);
		Map<LayoutField, TypedFieldConvertable> convertedLine = layout.convertLine("primeiro;segundo;terceiro");
		assertEquals("Fields converted from line",expectedConvertedFields, convertedLine);
	}	
	
	@Test
	public void convertTheMidleWorldOfLine() {
		LayoutField firstField = getDefaultLayoutField(2);

		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add((LayoutFieldCharDelimited) firstField);		

		HashMap<LayoutField,TypedFieldConvertable> expectedConvertedFields = new HashMap<LayoutField, TypedFieldConvertable>();
		expectedConvertedFields.put(firstField,new TextField("segundo"));
		
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";", fields);
		Map<LayoutField, TypedFieldConvertable> convertedLine = layout.convertLine("primeiro;segundo;terceiro");
		assertEquals("Fields converted from line",expectedConvertedFields, convertedLine);
	}	

	@Test(expected=RuntimeException.class)
	public void dontConvertLineLeterThanLayoutFields() {
		LayoutField firstField = getDefaultLayoutField(1);
		LayoutField secondField = getDefaultLayoutField(2);

		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add((LayoutFieldCharDelimited) secondField);
		fields.add((LayoutFieldCharDelimited) firstField);
		
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";", fields);
		layout.convertLine("primeiro");
	}

	
	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoNome() {
		new LayoutCharDelimited(null, ";");
	}

	@Test(expected=RuntimeException.class)
	public void garanteQueONomeNaoPodeSerVazio() {
		new LayoutCharDelimited("", ";");
	}

	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoCharDelimited() {
		new LayoutCharDelimited("teste", null);
	}

	@Test(expected=RuntimeException.class)
	public void garanteQueOCharDelimitedNaoPodeSerVazio() {
		new LayoutCharDelimited("teste", "");
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPossivelSetarNuloNoCharDelimited() {		
		
		LayoutCharDelimited layout = new LayoutCharDelimited(null, ";");
		layout.setCharDelimiter(null);
	}

	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPossivelSetarVazioNoCharDelimited() {
		
		LayoutCharDelimited layout = new LayoutCharDelimited(null, ";");
		layout.setCharDelimiter("");
	}
	
	@Test
	public void garanteQueSeNaoForInformadoOsLayoutsFieldsElesSaoInicializadosVazis() {
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";");
		assertEquals("Layout's fields", new ArrayList<LayoutField>(), layout.getLayoutFields());
	}
	
	@Test
	public void garanteQueSeOsLayoutsFieldsInformadosForemNulosElesSaoInicializadosVazis() {
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";", null);
		assertEquals("Layout's fields", new ArrayList<LayoutFieldCharDelimited>(), layout.getLayoutFields());
	}
	
	@Test
	public void garanteQueInformandoOsLayoutsFieldsNoConstrutorElesNaoSaoReinicializados() {
		
		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add(getDefaultLayoutField(1));
		
		Layout<LayoutFieldCharDelimited> layout = new LayoutCharDelimited("Layout de teste", ";", fields);
		assertEquals("Layout's fields", fields, layout.getLayoutFields());
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPossivelCadastrarDoisFieldsComMesmoIndice(){
		LayoutField firstField = getDefaultLayoutField(1);
		LayoutField secondField = getDefaultLayoutField(1);

		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add((LayoutFieldCharDelimited) secondField);
		fields.add((LayoutFieldCharDelimited) firstField);
		new LayoutCharDelimited("Layout de teste", ";", fields);
	}	
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPossivelSetarDoisFieldsComMesmoIndice(){
		LayoutField firstField = getDefaultLayoutField(1);
		LayoutField secondField = getDefaultLayoutField(1);

		List<LayoutFieldCharDelimited> fields = new ArrayList<LayoutFieldCharDelimited>();
		fields.add((LayoutFieldCharDelimited) firstField);
		LayoutCharDelimited layout = new LayoutCharDelimited("Layout de teste", ";", fields);
		layout.addLayoutField((LayoutFieldCharDelimited) secondField);
	}	
	
	private LayoutFieldCharDelimited getDefaultLayoutField( int index){
		return new LayoutFieldCharDelimited(new Field("test", Type.TEXT), false, index);
	}
}
