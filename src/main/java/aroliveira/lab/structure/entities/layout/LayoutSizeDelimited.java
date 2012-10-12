package aroliveira.lab.structure.entities.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import aroliveira.lab.structure.util.TypedFieldConvertable;
import aroliveira.lab.structure.util.TypedFieldConverter;

@Entity(name = "LAYOUT_SIZE_DELIMITED")
public class LayoutSizeDelimited extends Layout<LayoutFieldSizeDelimited> {

	private static final long serialVersionUID = 1932517761080487895L;

	public LayoutSizeDelimited(String name,
			List<LayoutFieldSizeDelimited> layoutFields) {
		super(layoutFields, name);
	}

	public LayoutSizeDelimited(String name) {
		this(name, new ArrayList<LayoutFieldSizeDelimited>());
	}

	@Override
	protected void checkFields(List<LayoutFieldSizeDelimited> layoutFields) {

		super.checkFields(layoutFields);
		
		if (layoutFields == null) return;

		Collections.sort(layoutFields);
		
		checkIfExistsFieldsWithSameInitialPosition(layoutFields);
	}

	private void checkIfExistsFieldsWithSameInitialPosition(
			List<LayoutFieldSizeDelimited> layoutFields) {

		if (layoutFields.isEmpty())
			return;

		LayoutFieldSizeDelimited field = layoutFields.get(0);

		int indexPosition = field.getInitialPosition() + field.getSize();

		for (int i = 1; i < layoutFields.size(); i++) {

			if (layoutFields.get(i).getInitialPosition()
					.equals(field.getInitialPosition()))
				throw new RuntimeException(
						"cannot exists two fields with same initial position");

			if (layoutFields.get(i).getInitialPosition() < indexPosition)
				throw new RuntimeException(
						"cannot exists two fields in the same position");
		}
	}

	@Override
	public
	void addLayoutField(LayoutFieldSizeDelimited layoutField) {
		List<LayoutFieldSizeDelimited> aux = new ArrayList<LayoutFieldSizeDelimited>(
				getLayoutFields());
		aux.add(layoutField);
		Collections.sort(aux);
		checkIfExistsFieldsWithSameInitialPosition(aux);

		layoutFields.add(layoutField);
		Collections.sort(layoutFields);
	}

	@Override
	public Map<LayoutField, TypedFieldConvertable> convertLine(String readLine) {

		Map<LayoutField, TypedFieldConvertable> mapFields = new LinkedHashMap<LayoutField, TypedFieldConvertable>();

		for (LayoutFieldSizeDelimited layoutField : layoutFields) {

			TypedFieldConvertable fieldConverted = TypedFieldConverter
					.convertToCorrectType(readLine.substring(
							layoutField.getInitialPosition(),
							layoutField.getInitialPosition()
									+ layoutField.getSize()), layoutField
							.getField());

			mapFields.put(layoutField, fieldConverted);
		}

		return mapFields;
	}
}
