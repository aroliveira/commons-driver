package aroliveira.lab.structure.entities.layout;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "LAYOUT_FIELD_CHAR_DELIMITED")
public class LayoutFieldCharDelimited extends LayoutField {

	private static final long serialVersionUID = -5937964979009210805L;

	@Column(name = "INDEX")
	Integer index;

	public LayoutFieldCharDelimited(Field field, Boolean nullable, Integer index) {
		super(field, nullable);
		setIndex(index);
	}

	private void setIndex(Integer index) {
		
		if ( index == null ) throw new RuntimeException("Index cannot be null");
		
		if ( index <= 0 ) throw new RuntimeException("Index cannot be zero or negative");
		
		this.index = index;		
	}

	@Override
	public int compareTo(LayoutField other) {		
		return getIndex().compareTo(((LayoutFieldCharDelimited)other).getIndex());
	}

	public Integer getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		
		final String nullable = this.getNullable() ? " [Obligated]" : " [Nullable]";
		
		return this.getField() + " Index: " + this.getIndex() + nullable + "\n";
	}
}
