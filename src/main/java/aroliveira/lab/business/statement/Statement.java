package aroliveira.lab.business.statement;

import java.text.ParseException;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import aroliveira.lab.structure.entities.base.EntityBean;
import aroliveira.lab.util.DateConverter;

@NamedQuery(name = "Statement.findAll", query = "FROM STATEMENT S")
@Entity(name = "STATEMENT")
public class Statement extends EntityBean{

	private static final long serialVersionUID = 1523603678276508849L;

	@Column(name = "DESCRIPTION", nullable = false, length = 254, unique = false)
	private String description;

	@Column(name = "VALUE", nullable = false, unique = false)
	private Double statementValue;

	@Column (name = "DATE", nullable = false, unique = false)
	@Temporal (TemporalType.DATE)
	private Date purchaseDate;

	public Statement(String description, String statementValue, String purchaseDate) {
		this.setDescription(description);
		this.setStatementValue(statementValue);
		this.setPurchaseDate(purchaseDate);
	}
	
	private void setPurchaseDate(String purchaseDate) {
		
		if ( ( purchaseDate == null ) || (purchaseDate.trim().isEmpty()))
			throw new RuntimeException("Statement purchase date can't be null or empty");
		
		try {
			this.purchaseDate = DateConverter.convertToDate(purchaseDate);
		} catch (ParseException e) {
			throw new RuntimeException("Invalid statement purchase date");
		}
	}

	private void setStatementValue(String statementValue) {

		if (statementValue == null) 
			throw new RuntimeException("Statement value can't be null");
		
		this.statementValue = new Double(statementValue.replaceAll("[,.]", ""));
	}

	private void setDescription(String description) {
		
		if ( ( description == null ) || (description.trim().isEmpty()))
			throw new RuntimeException("Statement description can't be null or empty");
		
		this.description = description;
	}

	@Override
	public String toString() {
		return this.description + " " + this.formattedStatementValue(this.statementValue) + " " + DateConverter.convertToString(this.purchaseDate);		
	}
	
	private String formattedStatementValue(double statementValue) {
		return Currency.getInstance(Locale.getDefault()).getSymbol() + " " + Double.valueOf(statementValue / 100).toString();
	}
}
