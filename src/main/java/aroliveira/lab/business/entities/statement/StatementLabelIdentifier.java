package aroliveira.lab.business.entities.statement;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StatementLabelIdentifier {

	@Inject
	private static StatementLabelFinder finder;

	public static void setupFinder(StatementLabelFinder especificFinder) {
		finder = especificFinder;
	}
	
	public static List<StatementLabel> findLabelsToDescription(String statementDescription) {

		ArrayList<StatementLabel> findedLabels = new ArrayList<StatementLabel>();
		
		if (null == finder){
			//TODO: logar que o finder está nulo
			System.out.println("logar que o finder está nulo");
		}else {
			
			for (StatementLabel statementLabel : finder.allLabels()) {
				if (statementLabel.match(statementDescription)){
					findedLabels.add(statementLabel);
				}
			}
		}
		
		return findedLabels;
	}
}
