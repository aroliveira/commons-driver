package aroliveira.lab.business.entities.statement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import aroliveira.lab.structure.entities.layout.DriverTest;
import aroliveira.lab.structure.entities.layout.FieldTest;
import aroliveira.lab.structure.entities.layout.FileManagerTest;
import aroliveira.lab.structure.entities.layout.LayoutFieldTest;
import aroliveira.lab.structure.entities.layout.LayoutTest;
import aroliveira.lab.structure.util.CurrencyFieldTest;
import aroliveira.lab.structure.util.DateFieldTest;
import aroliveira.lab.structure.util.IntegerFieldTest;
import aroliveira.lab.structure.util.TypedFieldConverterTest;

@RunWith(Suite.class)
@SuiteClasses({
	FieldTest.class,
	LayoutFieldTest.class, 
	LayoutTest.class,
	FileManagerTest.class,
	DriverTest.class,
	DateFieldTest.class,
	CurrencyFieldTest.class,
	IntegerFieldTest.class,
	TypedFieldConverterTest.class,
	StatementTest.class,
	StatementLabelTest.class} 
	)
public class AllTests {
}