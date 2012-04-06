package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import aroliveira.lab.structure.util.TextField;
import aroliveira.lab.structure.util.Type;
import aroliveira.lab.structure.util.TypedFieldConvertable;

public class LayoutTest {

	@Test(expected=RuntimeException.class)
	public void garanteObrigatoriedadeDoNome() {
		new Layout(null);
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueONomeNaoPodeSerVazio() {
		new Layout(null);
	}
	
	@Test
	public void garanteQueSeNaoForInformadoOsLayoutsFieldsElesSaoInicializadosVazis() {
		Layout layout = new Layout("Layout de teste");
		assertEquals("Layout's fields", new ArrayList<LayoutField>(), layout.getLayoutFields());
	}
	
	@Test
	public void garanteQueSeOsLayoutsFieldsInformadosForemNulosElesSaoInicializadosVazis() {
		Layout layout = new Layout(null, "Layout de teste");
		assertEquals("Layout's fields", new ArrayList<LayoutField>(), layout.getLayoutFields());
	}
	
	@Test
	public void garanteQueInformandoOsLayoutsFieldsNoConstrutorElesNaoSaoReinicializados() {
		
		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(0, 10));
		
		Layout layout = new Layout(fields,"Layout de teste");
		assertEquals("Layout's fields", fields, layout.getLayoutFields());
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoCadastrarUmLayoutInformandoNoConstrutorFieldsComOMesmoInitialPosition() {

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(0, 10));
		fields.add(getLayoutField(0, 5));
		
		new Layout(fields,"Layout de teste");		
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoAdicionarFieldsComOMesmoInitialPosition() {

		Layout layout = new Layout("Layout de teste");
		layout.addLayoutField(getLayoutField(0, 10));
		layout.addLayoutField(getLayoutField(0, 5));
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoCadastrarUmLayoutInformandoNoConstrutorFieldsComInterseccao() {

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(0, 10));
		fields.add(getLayoutField(9, 5));
		
		new Layout(fields,"Layout de teste");		
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoAdicionarFieldsComInterseccao() {

		Layout layout = new Layout("Layout de teste");
		layout.addLayoutField(getLayoutField(0, 10));
		layout.addLayoutField(getLayoutField(9, 5));
	}	
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoCadastrarUmLayoutInformandoNoConstrutorFieldsCujoLimiteEstejaContidoDentroDeOutroField() {

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(0, 10));
		fields.add(getLayoutField(4, 2));
		
		new Layout(fields,"Layout de teste");		
	}
	
	@Test(expected=RuntimeException.class)
	public void garanteQueNaoEhPermitidoAdicionarFieldsCujoLimiteEstejaContidoDentroDeOutroField() {

		Layout layout = new Layout("Layout de teste");
		layout.addLayoutField(getLayoutField(0, 10));
		layout.addLayoutField(getLayoutField(4, 2));
	}	
	
	
	@Test
	public void happyDayUsingOnlyTheName() {
		
		final String expectedName = "Layout de teste";
		final Layout layout = new Layout(expectedName);
		List<LayoutField> expectedFields = new ArrayList<LayoutField>();
		expectedFields.add(getLayoutField(0, 10));
		expectedFields.add(getLayoutField(10, 5));

		layout.addLayoutField(getLayoutField(10, 5));
		layout.addLayoutField(getLayoutField(0, 10));
		
		assertNotNull("Should exists a Layout", layout);
		assertEquals("Name of Layout", expectedName, layout.getName());
		assertEquals("Fields of Layout", expectedFields, layout.getLayoutFields());
		assertEquals("First field of Layout", getLayoutField(0, 10), layout.getLayoutFields().get(0));
		assertEquals("Second field of Layout", getLayoutField(10,5), layout.getLayoutFields().get(1));
	}
	
	@Test
	public void happyDayInformingAllAttributes() {
		
		List<LayoutField> fields = new ArrayList<LayoutField>();		
		fields.add(getLayoutField(10, 5));
		fields.add(getLayoutField(0, 10));
		
		final String expectedName = "Layout de teste";
		final Layout layout = new Layout(fields, expectedName);
		
		assertNotNull("Should exists a Layout", layout);
		assertEquals("Name of Layout", expectedName, layout.getName());
		assertEquals("Fields of Layout", fields, layout.getLayoutFields());
		assertEquals("First field of Layout", getLayoutField(0, 10), layout.getLayoutFields().get(0));
		assertEquals("Second field of Layout", getLayoutField(10,5), layout.getLayoutFields().get(1));
		
		assertEquals("Print of Layout", "Layout: Layout de teste fields: \n" + 
		"[Field: 'test' Type: 'TEXT' From: 0 Size: 10 [Nullable]\n" +
		", Field: 'test' Type: 'TEXT' From: 10 Size: 5 [Nullable]\n" +
		"]", layout.toString());
	}

	@Test
	public void happyDayConvertLine() {

		Map<String, TypedFieldConvertable> expectedConvertedFields = new HashMap<String, TypedFieldConvertable>();
		expectedConvertedFields.put("test",new TextField("primeiro c"));
		expectedConvertedFields.put("test",new TextField("Segun"));

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(10, 5));
		fields.add(getLayoutField(0, 10));

		final Layout layout = new Layout(fields, "Layout de teste");
		Map<String, TypedFieldConvertable> convertedFields = layout.convertLine("primeiro cSegun");

		assertEquals("Fields converted from line",expectedConvertedFields, convertedFields);
	}	

	@Test
	public void converteLinhaMaiorQueONecessarioComUmCaracterSobrando() {

		Map<String, TypedFieldConvertable> expectedConvertedFields = new HashMap<String, TypedFieldConvertable>();
		expectedConvertedFields.put("test",new TextField("primeiro c"));
		expectedConvertedFields.put("test",new TextField("Segun"));

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(10, 5));
		fields.add(getLayoutField(0, 10));

		final Layout layout = new Layout(fields, "Layout de teste");
		Map<String, TypedFieldConvertable> convertedFields = layout.convertLine("primeiro cSegun_");

		assertEquals("Fields converted from line",expectedConvertedFields, convertedFields);
	}

	@Test(expected=RuntimeException.class)
	public void tentaConverterOResultadoDeUmaLinhaMenorQueOEsperadoFaltandoUmCharacter() {

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(10, 5));
		fields.add(getLayoutField(0, 10));

		final Layout layout = new Layout(fields, "Layout de teste");
		layout.convertLine("primeiro cSegu");
	}

	@Test
	public void converteApenasOFieldDoMeioDaLinha() {

		Map<String, TypedFieldConvertable> expectedConvertedFields = new HashMap<String, TypedFieldConvertable>();
		expectedConvertedFields.put("test",new TextField("BBBBB"));

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(5, 5));

		final Layout layout = new Layout(fields, "Layout de teste");
		Map<String, TypedFieldConvertable> convertedFields = layout.convertLine("11111BBBBB33333");
		assertEquals("Fields converted from line",expectedConvertedFields, convertedFields);
	}

	@Test
	public void converteApenasOsFieldsDaPontaDaLinha() {

		Map<String, TypedFieldConvertable> expectedConvertedFields = new HashMap<String, TypedFieldConvertable>();		
		expectedConvertedFields.put("test",new TextField("AAAAA"));
		expectedConvertedFields.put("test",new TextField("CCCCC"));

		List<LayoutField> fields = new ArrayList<LayoutField>();
		fields.add(getLayoutField(0, 5));
		fields.add(getLayoutField(10, 5));

		final Layout layout = new Layout(fields, "Layout de teste");
		Map<String, TypedFieldConvertable> convertedFields = layout.convertLine("AAAAA22222CCCCC");
		assertEquals("Fields converted from line",expectedConvertedFields, convertedFields);
	}

	private LayoutField getLayoutField( int initialPosition, int size ){
		return new LayoutField(new Field("test", Type.TEXT), false, initialPosition, size);
	}
}
