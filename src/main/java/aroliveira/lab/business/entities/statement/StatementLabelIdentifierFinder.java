package aroliveira.lab.business.entities.statement;

import java.util.Collection;

public interface StatementLabelIdentifierFinder {
	Collection<StatementLabel> findLabelsToDescription(String description);
}