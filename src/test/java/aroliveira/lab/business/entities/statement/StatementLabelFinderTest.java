package aroliveira.lab.business.entities.statement;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatementLabelFinderTest {

	String defaultStatementDescription = "RSHOP-KI SABOR   -07/03";
	String defaultStatementValue = "1542";
	String defaultStatementPurchaseDate = "27/03/2012";

	private StatementLabel labelRestaurant = new StatementLabel("Restaurant", "Restaurant");
	private StatementLabel labelKiSabor = new StatementLabel("KI SABOR","SABOR", labelRestaurant);
	private StatementLabel labelMarch = new StatementLabel("March", "/03");
	
	@Before
	public void createLabels(){

		Collection<StatementLabel> labels = new ArrayList<StatementLabel>();
		
		labels.add(labelRestaurant);
		labels.add(labelKiSabor);
		labels.add(labelMarch);
		
		StatementLabelIdentifier.setupFinder(new StatementLabelFinderForTest());
		StatementLabelFinderForTest.allLabes(labels);
	}

	@Test
	public void createAStatementWithALabelHappyDay() {
		
		final String description = "RSHOP-KI SABOR ";
		List<StatementLabel> labels = StatementLabelIdentifier.findLabelsToDescription(description);
		assertEquals("Label quantity of statement", 1, labels.size());
		assertEquals("Label of description: " + description,labelKiSabor, labels.get(0));
	}
	
	@Test
	public void createAStatementWithMoreThanOneALabel() {
		
		HashSet<StatementLabel> labels = new HashSet<StatementLabel>(StatementLabelIdentifier.findLabelsToDescription(defaultStatementDescription));
		HashSet<StatementLabel> expectedLabels = new HashSet<StatementLabel>();
		expectedLabels.add(labelKiSabor);
		expectedLabels.add(labelMarch);
		assertEquals("Labels of description: " + defaultStatementDescription,expectedLabels, labels);
	}
}

/**
 * 		- Garantir que ao criar um statement o label é setado
		- Recuperar todos os lançamentos de um label específico
		- Recuperar todos os lançamentos de um label pai
		- Se um label é pai, ele pode ficar sem pattern
		- Somente labels filhos é que precisa de pattern, brincar com alterações na árvore
*/
