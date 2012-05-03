package aroliveira.lab.structure.entities.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import aroliveira.lab.business.entities.EntityBean;
import aroliveira.lab.structure.util.TypedFieldConvertable;
import aroliveira.lab.structure.util.TypedFieldConverter;

@Entity(name="LAYOUT")

public class Layout extends EntityBean {

	private static final long serialVersionUID = -1659962669068169888L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_LAYOUT")
	List<LayoutField> layoutFields;

	@Column(name="NAME")
	String name;

	public Layout(String name) {
		setName(name);
		setLayoutFields(new ArrayList<LayoutField>());
	}
	
	public Layout(List<LayoutField> layoutFields, String name) {
		
		this(name);
		setLayoutFields(layoutFields);
	}

	void addLayoutField(LayoutField layoutField){
		
		List<LayoutField> aux = new ArrayList<LayoutField>(getLayoutFields());
		aux.add(layoutField);
		Collections.sort(aux);
		checkIfExistsFieldsWithSameInitialPosition(aux);
		
		layoutFields.add(layoutField);
		Collections.sort(layoutFields);
	}

	void removeLayoutField(LayoutField layoutField){
		layoutFields.remove(layoutField);
	}

	public List<LayoutField> getLayoutFields(){
		return layoutFields;
	}

	private void setLayoutFields(List<LayoutField> layoutFields) {
		
		if (layoutFields == null) return;

		Collections.sort(layoutFields);

		if (layoutFields.size() > 0) {
			checkIfExistsFieldsWithSameInitialPosition(layoutFields);
		}
		
		this.layoutFields = layoutFields;
	}

	private void checkIfExistsFieldsWithSameInitialPosition(List<LayoutField> layoutFields) {
		
		LayoutField field = layoutFields.get(0);

		int indexPosition = field.getInitialPosition() + field.getSize();

		for (int i = 1; i < layoutFields.size(); i++) {
			
			if ( layoutFields.get(i).getInitialPosition().equals(field.getInitialPosition()))
				throw new RuntimeException("cannot exists two fields with same initial position");
			
			if ( layoutFields.get(i).getInitialPosition() < indexPosition )
				throw new RuntimeException("cannot exists two fields in the same position");
		}
	}
	
	void setName(String name){
		
		if ( name == null ) throw new RuntimeException("name cannot be null");
		
		if ( name.isEmpty() ) throw new RuntimeException("name cannot be empty");		
		
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((layoutFields == null) ? 0 : layoutFields.hashCode());
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
		Layout other = (Layout) obj;
		if (layoutFields == null) {
			if (other.layoutFields != null)
				return false;
		} else if (!layoutFields.equals(other.layoutFields))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Layout: " + this.getName() + " fields: \n" + this.getLayoutFields();
	}

	public Map<String, TypedFieldConvertable> convertLine(String readLine) {

		throwsAExceptionIfLineIsNotValid(readLine);
		
		Map<String, TypedFieldConvertable> mapFields = new HashMap<String, TypedFieldConvertable>();

		for (LayoutField layoutField : layoutFields) {

			int initialPosition = layoutField.getInitialPosition();
			int size = layoutField.getSize();
			
			String fieldName = layoutField.getField().getName();
			TypedFieldConvertable fieldConverted = TypedFieldConverter.convertToCorrectType(readLine.substring(initialPosition, initialPosition + size), layoutField.getField()); 

			mapFields.put(fieldName, fieldConverted);
		}
		
		return mapFields;
	}

	private void throwsAExceptionIfLineIsNotValid(final String readLine) {
		
		LayoutField field = layoutFields.get(layoutFields.size() - 1);

		if ( field.getInitialPosition() + field.getSize() > readLine.length() )
			throw new RuntimeException("Invalid line");
		
	}	
}
