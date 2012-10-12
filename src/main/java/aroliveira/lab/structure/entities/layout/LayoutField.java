package aroliveira.lab.structure.entities.layout;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import aroliveira.lab.business.entities.EntityBean;

@NamedQuery(name = "LayoutField.findAll", query = "FROM LAYOUT_FIELD L")
@Entity(name = "LAYOUT_FIELD")
public abstract class LayoutField extends EntityBean implements Comparable<LayoutField>{

	private static final long serialVersionUID = 724467954327666139L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_FIELD")
	Field field;

	@Column(name="NULLABLE")
	Boolean nullable;

	public LayoutField(){}

	public LayoutField(Field field, Boolean nullable) {
		setField(field);
		this.nullable = nullable;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result
				+ ((nullable == null) ? 0 : nullable.hashCode());
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
		if (nullable == null) {
			if (other.nullable != null)
				return false;
		} else if (!nullable.equals(other.nullable))
			return false;
		return true;
	}
}
