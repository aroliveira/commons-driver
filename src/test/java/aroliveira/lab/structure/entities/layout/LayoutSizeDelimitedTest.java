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

public class LayoutSizeDelimitedTest extends LayoutTest {

	@Test
	public void happyDayConvertLine() {
		convertAndCheckLine("primeiro cSegun");
	}

	@Test
	public void converteLinhaMaiorQueONecessarioComUmCaracterSobrando() {
		convertAndCheckLine("primeiro cSegun_");
	}
	
	@Test
	public void converteApenasOsFieldsDaPontaDaLinha() {

		LayoutField firstField = getDefaultLayoutField(0, 5);
		LayoutField secondField = getDefaultLayoutField(10, 5);

		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add((LayoutFieldSizeDelimited) secondField);
		fields.add((LayoutFieldSizeDelimited) firstField);		

		HashMap<LayoutField,TypedFieldConvertable> expectedConvertedFields = new HashMap<LayoutField, TypedFieldConvertable>();
		expectedConvertedFields.put(firstField,new TextField("AAAAA"));
		expectedConvertedFields.put(secondField,new TextField("CCCCC"));
		
		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste", fields);
		
		Map<LayoutField, TypedFieldConvertable> convertedLine = layout.convertLine("AAAAA22222CCCCC");
		assertEquals("Fields converted from line",expectedConvertedFields, convertedLine);
	}
	
	@Test(expected=RuntimeException.class)
	public void tentaConverterOResultadoDeUmaLinhaMenorQueOEsperadoFaltandoUmCharacter() {

		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add(getDefaultLayoutField(10, 5));
		fields.add(getDefaultLayoutField(0, 10));

		final LayoutSizeDelimited layout = new LayoutSizeDelimited("Layout de teste", fields);
		layout.convertLine("primeiro cSegu");
	}

	@Test
	public void converteApenasOFieldDoMeioDaLinha() {

		LayoutField firstField = getDefaultLayoutField(5, 5);
		
		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add((LayoutFieldSizeDelimited) firstField);
		
		Map<LayoutField, TypedFieldConvertable> expectedConvertedFields = new HashMap<LayoutField, TypedFieldConvertable>();
		expectedConvertedFields.put(firstField, new TextField("BBBBB"));

		final LayoutSizeDelimited layout = new LayoutSizeDelimited("Layout de teste", fields);
		Map<LayoutField, TypedFieldConvertable> convertedFields = layout.convertLine("11111BBBBB33333");
		
		assertEquals("Fields converted from line",expectedConvertedFields, convertedFields);
	}
	
	private void convertAndCheckLine(String line) {
		LayoutField firstField = getDefaultLayoutField(0, 10);
		LayoutField secondField = getDefaultLayoutField(10, 5);

		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add((LayoutFieldSizeDelimited) secondField);
		fields.add((LayoutFieldSizeDelimited) firstField);		

		HashMap<LayoutField,TypedFieldConvertable> expectedConvertedFields = new HashMap<LayoutField, TypedFieldConvertable>();
		expectedConvertedFields.put(firstField,new TextField("primeiro c"));
		expectedConvertedFields.put(secondField,new TextField("Segun"));
		
		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste", fields);
		
		Map<LayoutField, TypedFieldConvertable> convertedLine = layout.convertLine(line);
		
		assertEquals("Fields converted from line",expectedConvertedFields, convertedLine);
	}
	
	
	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoNome() {
		new LayoutSizeDelimited(null);
	}

	@Test(expected=RuntimeException.class)
	public void garanteQueONomeNaoPodeSerVazio() {
		new LayoutSizeDelimited("");
	}

	@Test
	public void garanteQueSeNaoForInformadoOsLayoutsFieldsElesSaoInicializadosVazis() {
		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste");
		assertEquals("Layout's fields", new ArrayList<LayoutField>(), layout.getLayoutFields());		
	}

	@Test
	public void garanteQueSeOsLayoutsFieldsInformadosForemNulosElesSaoInicializadosVazis() {
		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste", null);
		assertEquals("Layout's fields", new ArrayList<LayoutFieldCharDelimited>(), layout.getLayoutFields());		
	}

	@Override
	public void garanteQueInformandoOsLayoutsFieldsNoConstrutorElesNaoSaoReinicializados() {
		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add(getDefaultLayoutField(0, 10));
		
		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste", fields);
		assertEquals("Layout's fields", fields, layout.getLayoutFields());
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoCadastrarUmLayoutInformandoNoConstrutorFieldsComOMesmoInitialPosition() {

		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add(getDefaultLayoutField(0, 10));
		fields.add(getDefaultLayoutField(0, 5));
		
		new LayoutSizeDelimited("Layout de teste", fields);		
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoAdicionarFieldsComOMesmoInitialPosition() {

		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste");
		layout.addLayoutField(getDefaultLayoutField(0, 10));
		layout.addLayoutField(getDefaultLayoutField(0, 5));
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoCadastrarUmLayoutInformandoNoConstrutorFieldsComInterseccao() {

		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add(getDefaultLayoutField(0, 10));
		fields.add(getDefaultLayoutField(9, 5));
		
		new LayoutSizeDelimited("Layout de teste", fields);		
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoAdicionarFieldsComInterseccao() {

		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste");
		layout.addLayoutField(getDefaultLayoutField(0, 10));
		layout.addLayoutField(getDefaultLayoutField(9, 5));
	}	
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoCadastrarUmLayoutInformandoNoConstrutorFieldsCujoLimiteEstejaContidoDentroDeOutroField() {

		List<LayoutFieldSizeDelimited> fields = new ArrayList<LayoutFieldSizeDelimited>();
		fields.add(getDefaultLayoutField(0, 10));
		fields.add(getDefaultLayoutField(4, 2));
		
		new LayoutSizeDelimited("Layout de teste", fields);		
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoAdicionarFieldsCujoLimiteEstejaContidoDentroDeOutroField() {

		Layout<LayoutFieldSizeDelimited> layout = new LayoutSizeDelimited("Layout de teste");
		layout.addLayoutField(getDefaultLayoutField(0, 10));
		layout.addLayoutField(getDefaultLayoutField(4, 2));
	}

	private LayoutFieldSizeDelimited getDefaultLayoutField( int index, int size ){
		return new LayoutFieldSizeDelimited(new Field("test", Type.TEXT), false, index, size);
	}
}
