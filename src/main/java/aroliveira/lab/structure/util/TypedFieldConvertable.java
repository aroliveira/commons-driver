package aroliveira.lab.structure.util;

import java.text.Format;


public interface TypedFieldConvertable {

	Object getValue();
	
	Object getValueFormatted(final String readedField, final Format format);
}