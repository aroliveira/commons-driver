package aroliveira.lab.business.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
abstract public class EntityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE,generator="IDGEN")	
	@TableGenerator(name="IDGEN", table="KEYGEN")
	private Long id;
	
	public abstract String toString();
	
	public Long getId() {
		return id;
	}
}
