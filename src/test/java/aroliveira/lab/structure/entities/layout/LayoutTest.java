package aroliveira.lab.structure.entities.layout;


public abstract class LayoutTest {
	
	public abstract void happyDayConvertLine(); 
	
	public abstract void garanteObrigatoriedadeDoNome();
	
	public abstract void garanteQueONomeNaoPodeSerVazio();

	public abstract void garanteQueSeNaoForInformadoOsLayoutsFieldsElesSaoInicializadosVazis();
	
	public abstract void garanteQueSeOsLayoutsFieldsInformadosForemNulosElesSaoInicializadosVazis();
	
	public abstract void garanteQueInformandoOsLayoutsFieldsNoConstrutorElesNaoSaoReinicializados();
	
}
