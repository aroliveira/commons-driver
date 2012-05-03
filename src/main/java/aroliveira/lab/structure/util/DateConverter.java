package aroliveira.lab.structure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	
	public static Date convertToDate(String date) throws ParseException{
		return SimpleDateFormat.getDateInstance().parse(date);
	}

	public static String convertToString(Date purchaseDate) {
		return SimpleDateFormat.getDateInstance().format(purchaseDate);		
	}
}