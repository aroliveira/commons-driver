package aroliveira.lab.structure.entities.layout;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name = "LAYOUT_FIELD_SIZE_DELIMITED")
public class LayoutFieldSizeDelimited extends LayoutField {
	
	private static final long serialVersionUID = -6850314042301329913L;
	
	@Column(name = "INITIAL_POSITION")
	Integer initialPosition;

	@Column(name = "SIZE")
	Integer size;

	public LayoutFieldSizeDelimited(Field field, Boolean nullable, Integer initialPosition, Integer size) {
		super(field, nullable);
		setInitialPosition(initialPosition);
		setSize(size);
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
	public int compareTo(LayoutField other) {
		return this.getInitialPosition().compareTo(((LayoutFieldSizeDelimited)other).getInitialPosition());
	}
	
	@Override
	public String toString() {
		
		final String nullable = this.getNullable() ? " [Obligated]" : " [Nullable]";
		
		return this.getField() + " From: " + this.getInitialPosition() + " Size: " + this.getSize() + nullable + "\n";
	}
}
