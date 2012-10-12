package aroliveira.lab.structure.entities.layout;

import aroliveira.lab.structure.util.Type;

public abstract class LayoutFieldTest {

	public abstract void garanteObrigatoriedadeDoField();
	
	public abstract void happyDay();
	
	public abstract void garanteOPrintDeUmLayoutFieldObrigatorio();
	
	protected Field getDefaultField() {
		return new Field("Field test", Type.INTEGER);
	}	
}
