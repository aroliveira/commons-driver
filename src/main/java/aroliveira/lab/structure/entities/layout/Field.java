package aroliveira.lab.structure.entities.layout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;

import aroliveira.lab.business.entities.EntityBean;
import aroliveira.lab.structure.util.Type;

@NamedQuery(name = "FieldEntity.findAll", query = "FROM FIELD F")
@Entity(name = "FIELD")
public class Field extends EntityBean {

	private static final long serialVersionUID = 5107923237097495756L;

	@Column(name = "NAME", nullable = false, length = 100, unique = true)
	private String name = ""; 

	@Column(name = "TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	Type type;
	
	public Field() {}
	
	public Field(String name, Type type) {
		setName(name);
		setType(type);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		if ( name == null ) throw new RuntimeException("name cannot be null");
		
		if ( name.isEmpty() ) throw new RuntimeException("name cannot be empty");
		
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		
		if ( type == null ) throw new RuntimeException("type cannot be null");
		
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Field other = (Field) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return "Field: '" + this.getName() + "' Type: '" + this.getType() + "'";
	}
}