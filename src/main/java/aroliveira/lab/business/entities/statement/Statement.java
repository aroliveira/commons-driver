package aroliveira.lab.business.entities.statement;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import aroliveira.lab.business.entities.EntityBean;
import aroliveira.lab.structure.util.DateConverter;

@NamedQueries({ @NamedQuery(name = "Statement.findAll", query = "FROM STATEMENT S"), 
			    @NamedQuery(name = "Statement.findByDescription", query="FROM STATEMENT S WHERE S.description = ?1") })
@Entity(name = "STATEMENT")
public class Statement extends EntityBean {

	private static final long serialVersionUID = 1523603678276508849L;

	@Column(name = "DESCRIPTION", nullable = false, length = 254, unique = false)
	private String description;

	@Column(name = "VALUE", nullable = false, unique = false)
	private Double statementValue;

	@Column(name = "DATE", nullable = false, unique = false)
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<StatementLabel> labels = new ArrayList<StatementLabel>();

	public Statement() {}

	public Statement(String description, String statementValue,
			String purchaseDate) {
		this.setDescription(description);
		this.setStatementValue(statementValue);
		this.setPurchaseDate(purchaseDate);
		this.initializeLabels();		
	}

	public void addLabel(StatementLabel label) {
		
		if (this.getLabels().contains(label)) 
			return;
		
		this.getLabels().add(label);
	}
	
	private void setPurchaseDate(String purchaseDate) {

		if ((purchaseDate == null) || (purchaseDate.trim().isEmpty()))
			throw new RuntimeException(
					"Statement purchase date can't be null or empty");

		try {
			this.purchaseDate = DateConverter.convertToDate(purchaseDate);
		} catch (ParseException e) {
			throw new RuntimeException("Invalid statement purchase date");
		}
	}
	
	private void initializeLabels() {
		for (StatementLabel statementLabel : StatementLabelIdentifier.findLabelsToDescription(description)) 
			this.addLabel(statementLabel);
	}

	private void setStatementValue(String statementValue) {

		if (statementValue == null)
			throw new RuntimeException("Statement value can't be null");

		this.statementValue = new Double(statementValue.replaceAll("[,.]", ""));
	}

	public void setDescription(String description) {

		if ((description == null) || (description.trim().isEmpty()))
			throw new RuntimeException(
					"Statement description can't be null or empty");

		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public Double getStatementValue() {
		return statementValue;
	}

	public List<StatementLabel> getLabels(){
		return labels;
	}
	
	@Override
	public String toString() {
		return this.description + " "
				+ this.formattedStatementValue(this.statementValue) + " "
				+ DateConverter.convertToString(this.purchaseDate);
	}

	private String formattedStatementValue(double statementValue) {
		return Currency.getInstance(Locale.getDefault()).getSymbol() + " "
				+ Double.valueOf(statementValue / 100).toString();
	}

	public void removeLabel(StatementLabel label) {
		
		if (getLabels().isEmpty())
			throw new RuntimeException("This statement doesn't have labels!");
		
		if (!getLabels().contains(label))
			throw new RuntimeException("The label " + label.getDescription() + " isn't associate with this statement!");
			
		getLabels().remove(label);
	}
}
