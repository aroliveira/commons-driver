package aroliveira.lab.structure.entities.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import aroliveira.lab.business.entities.EntityBean;
import aroliveira.lab.structure.util.TypedFieldConvertable;

@Entity(name="LAYOUT")
public abstract class Layout<T extends LayoutField> extends EntityBean {

	private static final long serialVersionUID = -1659962669068169888L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_LAYOUT")
	List<T> layoutFields;

	@Column(name="NAME")
	String name;

	abstract void addLayoutField(T x);
	
	public abstract Map<LayoutField, TypedFieldConvertable> convertLine(String readLine);	
	
	public Layout(String name) {
		this(null, name);
	}
	
	public Layout(List<T> layoutFields, String name) {
		setName(name);
		setLayoutFields(layoutFields);
	}
	
	private void setLayoutFields(List<T> layoutFields) {
		
		if (layoutFields == null) 
			layoutFields = new ArrayList<T>();
		
		checkFields(layoutFields);
		
		this.layoutFields = layoutFields;
	}

	public List<T> getLayoutFields() {
		return layoutFields;
	}
	
	void setName(String name){
		
		if ( name == null ) throw new RuntimeException("name cannot be null");
		
		if ( name.isEmpty() ) throw new RuntimeException("name cannot be empty");		
		
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	
	
	protected void checkFields(List<T> layoutFields) {}
	
	@Override
	public String toString() {
		return "Layout: " + this.getName();
	}
}