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
	
	public static class NegativeException extends Exception {
		public NegativeException(){super();}
		public NegativeException(String message){
			super(message);
		}
	}
      
    	private static int sum(String[] numbers) throws Calculator.NegativeException{
 	    	int total = 0;
		StringBuffer negatives = new StringBuffer();;
		boolean contains = false;
        	for(String number : numbers){
		    if(number.contains("-")){
			contains = true;
		    	negatives.append(number + ",");
		    }
		    else{
		    	total += toInt(number);
		    }
		}
		if(contains) {
		    String newNegatives = negatives.toString();
		    throw new NegativeException("Negatives not allowed:" + newNegatives.substring(0, newNegatives.length() - 1));
		}
		else{
		    return total;
		}
    	}
}
