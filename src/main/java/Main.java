import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import aroliveira.lab.structure.entities.layout.Field;
import aroliveira.lab.structure.entities.layout.LayoutCharDelimited;
import aroliveira.lab.structure.entities.layout.LayoutField;
import aroliveira.lab.structure.entities.layout.LayoutFieldCharDelimited;
import aroliveira.lab.structure.entities.layout.LayoutFieldSizeDelimited;
import aroliveira.lab.structure.entities.layout.LayoutSizeDelimited;
import aroliveira.lab.structure.util.Type;
import aroliveira.lab.structure.util.TypedFieldConvertable;

public class Main {

	public static void main(String[] args) {

		//charDelimited();
		sizeDelimited();
	}

	private static void sizeDelimited() {
		
		Field data = new Field("Data", Type.DATE);
		Field descricao = new Field("Descrição", Type.TEXT);
		Field valor = new Field("Valor", Type.CURRENCY);

		LayoutFieldSizeDelimited fieldData = new LayoutFieldSizeDelimited(data,
				false, 1, 10);
		LayoutFieldSizeDelimited fieldDescricao = new LayoutFieldSizeDelimited(
				descricao, false, 11, 24);
		LayoutFieldSizeDelimited fieldValor = new LayoutFieldSizeDelimited(
				valor, false, 36, 6);

		LayoutSizeDelimited layout = new LayoutSizeDelimited(
				"Layout lanctos ITAU");
		layout.addLayoutField(fieldData);
		layout.addLayoutField(fieldDescricao);
		layout.addLayoutField(fieldValor);

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/anderson/Downloads/extrato.txt"));
			String str;
			while (in.ready()) {
				str = in.readLine();
				Map<LayoutField, TypedFieldConvertable> convertLine = layout.convertLine(str);
				
				System.out.println(convertLine.get(fieldData));
				//System.out.println(convertLine.get(fieldData).getValue());
				
				System.out.println(convertLine.get(fieldDescricao));
				//System.out.println(convertLine.get(fieldDescricao).getValue());
				
				System.out.println(convertLine.get(fieldValor));
				//System.out.println(convertLine.get(fieldValor).getValue());
			}
			in.close();
		} catch (IOException e) {
		}
	}
	
	private static void charDelimited() {
		Field data = new Field("Data", Type.DATE);
		Field descricao = new Field("Descrição", Type.TEXT);
		Field valor = new Field("Valor", Type.CURRENCY);

		LayoutFieldCharDelimited fieldData = new LayoutFieldCharDelimited(data,
				false, 1);
		LayoutFieldCharDelimited fieldDescricao = new LayoutFieldCharDelimited(
				descricao, false, 2);
		LayoutFieldCharDelimited fieldValor = new LayoutFieldCharDelimited(
				valor, false, 3);

		LayoutCharDelimited layout = new LayoutCharDelimited(
				"Layout lanctos ITAU", ";");
		layout.addLayoutField(fieldData);
		layout.addLayoutField(fieldDescricao);
		layout.addLayoutField(fieldValor);

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/anderson/Downloads/extrato.txt"));
			String str;
			while (in.ready()) {
				str = in.readLine();
				Map<LayoutField, TypedFieldConvertable> convertLine = layout.convertLine(str);
				
				System.out.println(convertLine.get(fieldData));
				//System.out.println(convertLine.get(fieldData).getValue());
				
				System.out.println(convertLine.get(fieldDescricao));
				//System.out.println(convertLine.get(fieldDescricao).getValue());
				
				System.out.println(convertLine.get(fieldValor));
				//System.out.println(convertLine.get(fieldValor).getValue());
			}
			in.close();
		} catch (IOException e) {
		}
	}
}