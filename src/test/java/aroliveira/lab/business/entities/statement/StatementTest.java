package aroliveira.lab.business.entities.statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class StatementTest {

	String defaultStatementDescription = "Business Lunch";
	String defaultStatementValue = "1542";
	String defaultStatementPurchaseDate = "27/03/2012";
	String statementDefaultPrint = defaultStatementDescription + " R$ 15.42 "
			+ defaultStatementPurchaseDate;

	@Test
	public void createStatementHappyDay() {

		Statement statement = defaultStatement();

		assertNotNull("Should be created a Statement", statement);
		assertEquals("Statement detail", statementDefaultPrint,
				statement.toString());
		assertEquals("Quantity of labels in a default statement",0, statement.getLabels().size());
	}

	@Test()
	public void createAStatementWithPointedValueAndAssertThatThisPointWasRemoved() {

		Statement statementWithCommonInValue = new Statement(
				defaultStatementDescription, "15,42",
				defaultStatementPurchaseDate);
		Statement statementWithPointInValue = new Statement(
				defaultStatementDescription, "15.42",
				defaultStatementPurchaseDate);

		assertEquals("Statement detail", statementDefaultPrint,
				statementWithCommonInValue.toString());
		assertEquals("Statement detail", statementDefaultPrint,
				statementWithPointInValue.toString());
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithNullDescription() {
		new Statement(null, defaultStatementValue, defaultStatementPurchaseDate);
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithEmptyDescription() {
		new Statement("", defaultStatementValue, defaultStatementPurchaseDate);
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithBlankDescription() {
		new Statement("   ", defaultStatementValue,
				defaultStatementPurchaseDate);
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithNullValue() {
		new Statement(defaultStatementDescription, null,
				defaultStatementPurchaseDate);
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithNullPurchaseDate() {
		new Statement(defaultStatementDescription, defaultStatementValue, null);
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithEmptyPurchaseDate() {
		new Statement(defaultStatementDescription, defaultStatementValue, "");
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithBlankPurchaseDate() {
		new Statement(defaultStatementDescription, defaultStatementValue,
				"    ");
	}

	@Test(expected = RuntimeException.class)
	public void dontCreateAStatementWithInvalidFormartPurchaseDate() {
		new Statement(defaultStatementDescription, defaultStatementValue,
				"invalid");
	}
	
	@Test
	public void addALabelManually(){
		
		StatementLabel label = defaltStatementLabel();
		Statement statement = defaultStatement();
		statement.addLabel(label);

		assertEquals("Label size",1, statement.getLabels().size());
		assertEquals("Label added manually",label, statement.getLabels().get(0));
	}

	@Test
	public void dontAddSomeLabelManually(){
		
		StatementLabel label = defaltStatementLabel();
		Statement statement = defaultStatement();
		statement.addLabel(label);
		statement.addLabel(label);

		assertEquals("Label size",1, statement.getLabels().size());
		assertEquals("Label added manually",label, statement.getLabels().get(0));
	}
	
	@Test
	public void removeALabelManually(){
		
		StatementLabel label = defaltStatementLabel();
		Statement statement = defaultStatement();
		statement.addLabel(label);

		assertEquals("Label size",1, statement.getLabels().size());
		assertEquals("Label added manually",label, statement.getLabels().get(0));
		
		statement.removeLabel(label);
		assertEquals("Label size",0, statement.getLabels().size());
	}

	@Test(expected=RuntimeException.class)
	public void dontRemoveManuallyAInexistentLabel(){

		StatementLabel label = defaltStatementLabel();
		Statement statement = defaultStatement();
		statement.addLabel(label);
		
		StatementLabel inexistentLabel = new StatementLabel("Inexistent Remove", "Remove test");
		statement.removeLabel(inexistentLabel);
	}
	
	@Test(expected=RuntimeException.class)
	public void dontRemoveManuallyAInexistentLabelFromAStatementThatDoenstHaveLabels(){
		
		StatementLabel label = defaltStatementLabel();
		Statement statement = defaultStatement();
		statement.removeLabel(label);
	}	
	
	private StatementLabel defaltStatementLabel() {
		return new StatementLabel("Manually label test", "test");
	}

	private Statement defaultStatement() {
		return new Statement(defaultStatementDescription,
				defaultStatementValue, defaultStatementPurchaseDate);
	}	
	
}
