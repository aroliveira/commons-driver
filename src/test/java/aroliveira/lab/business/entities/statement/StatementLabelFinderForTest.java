package aroliveira.lab.business.entities.statement;

import java.util.Collection;

import javax.enterprise.inject.Alternative;
import org.junit.Test;

@Alternative
public class StatementLabelFinderForTest implements StatementLabelFinder {

	private static Collection<StatementLabel> labels;
	
	public static void allLabes(Collection<StatementLabel> especifcLabels){
		labels = especifcLabels;
	}
	
	@Override
	public Collection<StatementLabel> allLabels() {
		return labels;
	}
	
	@Test
	public void opa() {
	}	
}
