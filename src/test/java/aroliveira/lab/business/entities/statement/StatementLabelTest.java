package aroliveira.lab.business.entities.statement;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import org.junit.Test;

public class StatementLabelTest {
	
	private static final String grandFatherDescription = "Grand Father Description";
	private static final String grandFatherPattern = "Grand Father Pattern";
	
	private static final String defaultFatherDescription = "Father Description";
	private static final String defaultFatherPattern = "Father Pattern";
	
	private static final String defaultDescription = "Children Description";
	private static final String defaultPattern = "Children Pattern";

	@Test
	public void createStatementLabelWithoutFatherHappyDay() {

		StatementLabel statementLabel = new StatementLabel(defaultDescription,defaultPattern);
		
		ensureBasicDataFromStatementLabel(statementLabel);
		assertNull("Simple Statement Label shouldn't have a father", statementLabel.getFather());
		assertEquals("Expected print of Statement Label",defaultDescription + ": [" + defaultPattern + "]" , statementLabel.toString());
	}

	
	@Test
	public void createStatementLabelWithFatherHappyDay() {

		StatementLabel defaultFather = new StatementLabel(defaultFatherDescription,defaultFatherPattern);		
		
		StatementLabel statementLabel = new StatementLabel(defaultDescription,defaultPattern, defaultFather);
		ensureBasicDataFromStatementLabel(statementLabel);
		
		assertEquals("Father of Statement Label", defaultFather, statementLabel.getFather());
		assertEquals("Expected print of Statement Label with father",defaultFatherDescription + ": [" + defaultFatherPattern + "] -> " + defaultDescription + ": [" + defaultPattern + "]", statementLabel.toString());
	}

	@Test
	public void testPrintOfAHierarchyWithThreeLabels() {

		StatementLabel children = createAHierarchyWithThreeLabels();
		
		assertEquals("Expected print of complete hierarchy statement", 
				grandFatherDescription + ": [" + grandFatherPattern + "] -> " + 
				defaultFatherDescription + ": [" + defaultFatherPattern + "] -> " + 
				defaultDescription + ": [" + defaultPattern + "]",children.toString());
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementLabelWithNullDescription(){
		new StatementLabel(null,defaultFatherPattern);
	}
	
	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementLabelWithEmptyDescription(){
		new StatementLabel("  	 ",defaultFatherPattern);
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementLabelWithNullPattern(){
		new StatementLabel(defaultFatherDescription,null);
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementLabelWithEmptyPattern(){
		new StatementLabel(defaultFatherDescription,"	 ");
	}
	
	@Test(expected = RuntimeException.class)
	public void dontAlowSetStatementLabelFatherThatAlreadyExistsInHierarchy(){
		//A -> B -> C -> A or //A -> B -> A
		StatementLabel grandFather = new StatementLabel(grandFatherDescription,grandFatherPattern);
		StatementLabel father = new StatementLabel(defaultFatherDescription,defaultFatherPattern, grandFather);
		StatementLabel children = new StatementLabel(defaultDescription,defaultPattern, father);
		grandFather.setFather(children);
	}
	
	@Test(expected = RuntimeException.class)
	public void dontAlowAChildrenBecameFatherByYourSelf(){
		//A -> A
		StatementLabel children = new StatementLabel(defaultDescription,defaultPattern);
		children.setFather(children);
	}
	
	private StatementLabel createAHierarchyWithThreeLabels() {
		StatementLabel grandFather = new StatementLabel(grandFatherDescription,grandFatherPattern);
		StatementLabel father = new StatementLabel(defaultFatherDescription,defaultFatherPattern, grandFather);
		return new StatementLabel(defaultDescription,defaultPattern, father);
	}

	private void ensureBasicDataFromStatementLabel(StatementLabel statementLabel) {
		assertNotNull("Should be created a Statement Label", statementLabel);
		assertEquals("Expected label description",defaultDescription, statementLabel.getDescription());
		assertEquals("Expected label pattern",defaultPattern, statementLabel.getPattern());
	}
}