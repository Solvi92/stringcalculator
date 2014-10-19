package is.ru.stringcalculator;
import java.util.regex.*;

public class Calculator {

	public static int add(String text) throws Calculator.NegativeException{
		if(text.equals("")){
			return 0;
		}
		else if(text.contains("-") || text.contains(",") || text.contains("\n") || text.contains("//")){
			return sum(splitNumbers(text));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers)throws Calculator.NegativeException{
	    if(numbers.contains("//")) {
		Pattern pattern = Pattern.compile("//(.)\n(.+)");
		Matcher matcher = pattern.matcher(numbers);
		if(matcher.matches()){
			String delimiter = matcher.group(1);
			String NewNumbers = matcher.group(2);

			return NewNumbers.split(",|\n|" + delimiter);
		}
	    }
	    return numbers.split(",|\n");
	}
	
	private static class NegativeException extends Exception {
		public NegativeException(){super();}
		public NegativeException(String message){
			super(message);
		}
	}
      
    	private static int sum(String[] numbers) throws Calculator.NegativeException{
 	    	int total = 0;
		String negatives = "";
        	for(String number : numbers){
		    if(number.contains("-")){
		    	negatives = numbers + ", ";
		    }
		    else{
		    	total += toInt(number);
		    }
		}
		if(!negatives.isEmpty()){
		    throw new NegativeException("Negatives not allowed: " + negatives.substring(0, negatives.length() - 2));
		}
		return total;
    	}
}
