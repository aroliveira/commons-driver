package aroliveira.lab.structure.entities.layout;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import aroliveira.lab.structure.entities.base.EntityBean;

@NamedQuery(name = "LayoutField.findAll", query = "FROM LAYOUT_FIELD L")
@Entity(name = "LAYOUT_FIELD")
public class LayoutField extends EntityBean implements Comparable<LayoutField>{

	private static final long serialVersionUID = 724467954327666139L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_FIELD")
	Field field;

	//TODO Falta testar e implementar o controle de obrigatoriedade
	@Column(name="NULLABLE")
	Boolean nullable;

	@Column(name = "INITIAL_POSITION")
	Integer initialPosition;

	@Column(name = "SIZE")
	Integer size;

	public LayoutField(){}
	
	public LayoutField(Field field, Boolean nullable, Integer initialPosition,
			Integer size) {
		setField(field);
		this.nullable = nullable;
		setInitialPosition(initialPosition);
		setSize(size);
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		
		if ( field == null ) throw new RuntimeException("Field cannot be null");
		
		this.field = field;
	}

	public Integer getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(Integer initialPosition) {
		
		if ( initialPosition == null ) throw new RuntimeException("Initial Position cannot be null");
		
		if ( initialPosition < 0 ) throw new RuntimeException("Initial Position cannot be negative");
		
		this.initialPosition = initialPosition;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		
		if ( size == null ) throw new RuntimeException("Size cannot be null");
		
		if ( size <= 0 ) throw new RuntimeException("Size must be bigger than zero");
		
		this.size = size;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result
				+ ((initialPosition == null) ? 0 : initialPosition.hashCode());
		result = prime * result
				+ ((nullable == null) ? 0 : nullable.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LayoutField other = (LayoutField) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (initialPosition == null) {
			if (other.initialPosition != null)
				return false;
		} else if (!initialPosition.equals(other.initialPosition))
			return false;
		if (nullable == null) {
			if (other.nullable != null)
				return false;
		} else if (!nullable.equals(other.nullable))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	public int compareTo(LayoutField other) {
		return this.getInitialPosition().compareTo(other.getInitialPosition());
	}
	
	@Override
	public String toString() {
		
		final String nullable = this.getNullable() ? " [Obligated]" : " [Nullable]";
		
		return this.getField() + " From: " + this.getInitialPosition() + " Size: " + this.getSize() + nullable + "\n";
	}	
}
