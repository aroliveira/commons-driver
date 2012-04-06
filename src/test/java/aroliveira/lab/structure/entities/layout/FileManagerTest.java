package aroliveira.lab.structure.entities.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileManagerTest {

	@Test(expected=RuntimeException.class)
	public void tentaCriarUmFileManagerInformandoONomeDoArquivoNulo() {
		new FileManager(null);
	}
	
	@Test(expected=RuntimeException.class)
	public void tentaCriarUmFileManagerInformandoONomeDoArquivoVazio() {
		new FileManager(null);
	}	
	
	@Test(expected=RuntimeException.class)
	public void tentaLerDeUmArquivoInexistente() {
		FileManager fileManager = new FileManager("testFile.txt");		
		fileManager.readLine();
	}
	
	@Test()
	public void lerDeUmArquivoVazio() {
		FileManager fileManager = new FileManager("emptyFile.txt");		
		String line = fileManager.readLine();
		assertNull("Line readed from a empty file", line);
	}

	@Test
	public void createAFileManagerHappyDay() {
		
		FileManager fileManager = new FileManager("onlyOneLine.txt");
		assertNotNull("Shold be created a FileManager", fileManager);
	}
	
	@Test
	public void readOnlyOneLineHappyDay() {

		final String expectedLine = "03/11/2011;RSHOP-AUTO POSTO -02/11 ;-50,00";
		
		FileManager fileManager = new FileManager("onlyOneLine.txt");
		String line = fileManager.readLine();
		
		assertEquals("Line readed", expectedLine, line);
	}
	
	@Test
	public void readFileWithManyLinesHappyDay() {
		
		final String expectedFirstLine = "07/11/2011;RSHOP-ACOUGUE MUL-05/11 ;-27,40";
		final String expectedSecondLine = "11/11/2011;PAGTO EM CONTA CORRENTE ;287,90";
		final String expectedThirdLine = "07/11/2011;RSHOP-DISK CERVEJ-05/11 ;-24,00";
		
		FileManager fileManager = new FileManager("manyLines.txt");
		
		String firstLine = fileManager.readLine();
		String secondLine = fileManager.readLine();
		String thirdLine = fileManager.readLine();

		assertEquals("First line readed", expectedFirstLine, firstLine);
		assertEquals("Second line readed", expectedSecondLine, secondLine);
		assertEquals("Third line readed", expectedThirdLine, thirdLine);
	}
	
	@Test
	public void leituraSimultaneaDoMesmoArquivoPorDoisClientesDiferentes() {
		
		final String expectedFirstLine = "07/11/2011;RSHOP-ACOUGUE MUL-05/11 ;-27,40";
		final String expectedSecondLine = "11/11/2011;PAGTO EM CONTA CORRENTE ;287,90";
		final String expectedThirdLine = "07/11/2011;RSHOP-DISK CERVEJ-05/11 ;-24,00";
		
		FileManager firstReadClient = new FileManager("manyLines.txt");
		FileManager secondReadClient = new FileManager("manyLines.txt");
		
		String firstLineFirstClient = firstReadClient.readLine();
		
		String firstLineSecondClient = secondReadClient.readLine();
		String secondLineSecondClient = secondReadClient.readLine();

		String secondLineFirstClient = firstReadClient.readLine();
		String thirdLineFirstClient = firstReadClient.readLine();

		assertEquals("First line readed from the first client", expectedFirstLine, firstLineFirstClient);
		assertEquals("Second line readed from the first client", expectedSecondLine, secondLineFirstClient);
		assertEquals("Third line readed from the first client", expectedThirdLine, thirdLineFirstClient);
		
		assertEquals("First line readed from the second client", expectedFirstLine, firstLineSecondClient);
		assertEquals("Second line readed from the second client", expectedSecondLine, secondLineSecondClient);
	}

	@Test
	public void leituraEmLote() {
		final List<String> expectedLines = new ArrayList<String>();
		expectedLines.add("07/11/2011;RSHOP-ACOUGUE MUL-05/11 ;-27,40");
		expectedLines.add("11/11/2011;PAGTO EM CONTA CORRENTE ;287,90");
		
		FileManager fileManager = new FileManager("manyLines.txt");

		List<String> lines = fileManager.readLines(2);
		assertEquals("Lines readead",expectedLines, lines);
	}
	
	@Test
	public void leEmLoteUmaQuantidadeSuperiorDeLinhasContidasNoArquivo() {
		
		final List<String> expectedLines = new ArrayList<String>();
		expectedLines.add("03/11/2011;RSHOP-AUTO POSTO -02/11 ;-50,00");
		
		FileManager fileManager = new FileManager("onlyOneLine.txt");

		List<String> lines = fileManager.readLines(3);
		assertEquals("Lines readead",expectedLines, lines);
	}	

	@Test
	public void leEmLoteUmArquivoVazio() {

		final List<String> expectedLines = new ArrayList<String>();

		FileManager fileManager = new FileManager("emptyFile.txt");

		List<String> lines = fileManager.readLines(3);
		assertEquals("Lines readead",expectedLines, lines);
	}
	
	@Test
	public void leEmLoteInfomandoQuantidadeDeLinhasIgualAZero() {

		final List<String> expectedLines = new ArrayList<String>();

		FileManager fileManager = new FileManager("onlyOneLine.txt");

		List<String> lines = fileManager.readLines(0);
		assertEquals("Lines readead",expectedLines, lines);
	}
	
	@Test(expected=RuntimeException.class)
	public void tentaLerEmLoteInfomandoQuantidadeNulaDeLinhas() {

		final List<String> expectedLines = new ArrayList<String>();

		FileManager fileManager = new FileManager("onlyOneLine.txt");

		List<String> lines = fileManager.readLines(null);
		assertEquals("Lines readead",expectedLines, lines);
	}
	
	@Test(expected=RuntimeException.class)
	public void tentaLerEmLoteInfomandoQuantidadeNegativaDeLinhas() {

		final List<String> expectedLines = new ArrayList<String>();

		FileManager fileManager = new FileManager("onlyOneLine.txt");

		List<String> lines = fileManager.readLines(-1);
		assertEquals("Lines readead",expectedLines, lines);
	}
}

