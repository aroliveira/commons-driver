package aroliveira.lab.business.entities.statement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import aroliveira.lab.structure.entities.layout.DriverTest;
import aroliveira.lab.structure.entities.layout.FieldTest;
import aroliveira.lab.structure.entities.layout.FileManagerTest;
import aroliveira.lab.structure.entities.layout.LayoutCharDelimitedTest;
import aroliveira.lab.structure.entities.layout.LayoutFieldCharDelimitedTest;
import aroliveira.lab.structure.entities.layout.LayoutFieldSizeDelimitedTest;
import aroliveira.lab.structure.entities.layout.LayoutSizeDelimitedTest;
import aroliveira.lab.structure.util.CurrencyFieldTest;
import aroliveira.lab.structure.util.DateFieldTest;
import aroliveira.lab.structure.util.IntegerFieldTest;
import aroliveira.lab.structure.util.TypedFieldConverterTest;

@RunWith(Suite.class)
@SuiteClasses({
	CurrencyFieldTest.class, 
	DateFieldTest.class, 
	IntegerFieldTest.class,
	TypedFieldConverterTest.class, 
	DriverTest.class, 
	FieldTest.class, 
	FileManagerTest.class, 
	LayoutCharDelimitedTest.class, 
	LayoutFieldCharDelimitedTest.class, 
	LayoutFieldSizeDelimitedTest.class, 
	LayoutSizeDelimitedTest.class,
	StatementLabelFinderForTest.class, 
	StatementLabelFinderTest.class, 
	StatementLabelTest.class, 
	StatementTest.class} 
	)
public class AllTests {
}