package aroliveira.lab.business.entities.statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import aroliveira.lab.business.entities.EntityBean;

@Entity(name="STATEMENT_LABEL")
@NamedQueries({ @NamedQuery(name = "StatementLabel.findAll", query = "FROM STATEMENT_LABEL S"), 
    @NamedQuery(name = "StatementLabel.findByDescription", query="FROM STATEMENT_LABEL S WHERE S.description = ?1") })
public class StatementLabel extends EntityBean{

	private static final long serialVersionUID = -2769387225847292957L;

	@Column(name = "DESCRIPTION", nullable = false, length = 254, unique = true)
	private String description;
	
	@Column(name = "PATTERN", nullable = false, length = 254, unique = false)
	private String pattern;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "ID_FATHER", insertable = true, updatable = true, nullable = true)
	private StatementLabel father;

	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER, mappedBy="labels")
	private Collection<Statement> statements = new ArrayList<Statement>();

	public StatementLabel() {}
	
	public StatementLabel(String description, String pattern,
			StatementLabel father) {
		
		setDescription(description);
		setPattern(pattern);
		this.father = father;
	}
	
	public StatementLabel(String description, String pattern) {
		this(description, pattern, null);
	}

	public void setDescription(String description) {
		
		if ((description == null) || (description.trim().isEmpty()))
			throw new RuntimeException(
					"Statement Label description can't be null or empty");
		
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setPattern(String pattern) {
		
		if ((pattern == null) || (pattern.trim().isEmpty()))
			throw new RuntimeException(
					"Statement Label pattern can't be null or empty");
		
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return this.pattern;
	}
	
	public void setFather(StatementLabel father) {
		if (father.getParentsHierarchy().contains(this)) 
			throw new RuntimeException("A children can't become a statement label father in own hierarchy");
	}

	private List<StatementLabel> getParentsHierarchy() {

		List<StatementLabel> parentHierarchy = getFather() == null ? new ArrayList<StatementLabel>() : getFather().getParentsHierarchy();
		parentHierarchy.add(this);
		return parentHierarchy;
	}

	public StatementLabel getFather() {
		return this.father;
	}
	
	public Collection<Statement> getStatements() {
		return statements;
	}
	
	@Override
	public String toString() {
		
		String fatherPrint = this.father == null ? "" : father.toString() + " -> ";
		return fatherPrint + this.description + ": [" + this.pattern + "]";
	}

	public boolean match(String statementDescription) {
		return statementDescription.toUpperCase().contains(getPattern().toUpperCase());
	}
	
	@Override
	public boolean equals(Object otherLabel) {
		return getDescription().equals(((StatementLabel) otherLabel).getDescription());
	}
}
