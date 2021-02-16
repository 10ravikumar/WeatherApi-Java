package weather.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateField {
	
	
	public String getDateFormatValue(String input) {
		String dayValue ="";
		switch(input) {
		case "tomorrows":
		 dayValue = getDayPlusValue(1);
		 break;
		case "todays":
		 dayValue = getDayPlusValue(0);
		 break;
		case "dayAfterTomorrows": 
		 dayValue = getDayPlusValue(2);
		 break;	
	}
		return dayValue;
	}
	
	private String getDayPlusValue(int dayValue) {
		return LocalDate.now().plusDays(dayValue).format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
		
	}

}
