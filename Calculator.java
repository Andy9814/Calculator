/*
 * Nripdeep singh
 * 12 August,2017
 * Calculator
 */
import java.util.ArrayList;

public class Calculator{
	//field
	
	private String operator;
	private String operand;
	private boolean decimalPressed;
	private ArrayList<String> list;

	//constructor
	public Calculator(){
		super();
		this.operator = "";
		
		this.operand = "";
		
		this.decimalPressed = false;
		this.list = new ArrayList<String>();
	}

	/**
	* Method Name: getoperand
	* Purpose: to get operand
	* Returns: String
	*/
	
	public String getOperand(){
		return operand;
	}

	/**
	* Method Name: setOperand
	* Purpose: to set operand
	* Accepts: String
	*/
	
	public void setOperand(String operand){
		this.operand = operand;
	}

	/**
	* Method Name: getOperator
	* Purpose: to get operator
	* Returns: String
	*/
	public String getOperator(){
		return operator;
	}
	/**
	* Method Name: setOperator
	* Purpose: to set Operator
	* Accepts: String
	*/

	public void setOperator(String operator){
		this.operator = operator;
	}

	
	/**
	* Method Name: getDecimalPressed
	* Purpose: to get getDecimalPressed
	* Returns: Boolean
	*/
	public boolean getDecimalPressed()
	{
		return decimalPressed;
	}

	/**
	* Method Name: setDecimalPressed
	* Purpose: to set DecimalPressed
	* Accepts: boolean
	*/
	public void setDecimalPressed(boolean decimalPressed){
		this.decimalPressed = decimalPressed;
	}

	/**
	* Method Name: getlist
	* Purpose: to get list
	* Returns: list
	*/
	
	public ArrayList<String> getList()
	{
		return list;
	}

	
	
    
	
	
	/**
	* Method Name: clear
	* Purpose: clear
	* Returns: none
	*/
	
	void clear()
	{
		this.operand = "";
		this.operator = "";
		this.decimalPressed = false;

	}

	/**
	* Method Name:backSpace
	* Purpose: delete the last character of operand
	* Accepts: nothing
	* Returns: String
	*/
	
	String backspace() 
	{

		try{
		
		if (operand != "")
		{
			if(operand.charAt(operand.length()-1) == '.'){
				this.decimalPressed = false;
			}
			operand = operand.substring(0, operand.length() - 1);	
		}
		return operand;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0 + "";
		}

	}

	/**
	* Method Name: findPercentage
	* Purpose: to find the percentage
	* Accepts: nothing
	* Returns: String
	*/
	String findPercentage(String value)
	{

		try{
		return Double.parseDouble(value) / 100 + "";
		}catch(Exception e){
			System.out.println(e.getMessage());
			return "";
		}
	}

	/**
	* Method Name: togglePlusMinus
	* Purpose: to toggle plus minus 
	* Accepts: boolean
	* Returns: String
	*/
	
	
	String togglePlusMinus(Boolean flag)
	{

		try{
			return (0 - Double.parseDouble(this.operand)) + "";
		}catch(Exception e){
			System.out.println(e.getMessage());
			return "";
		}
		
	}

	/**
	* Method Name: findSquare
	* Purpose: to find square of the value
	* Accepts: boolean
	* Returns: String
	*/
	
	String findSquared(String value){
		try{
			return Math.pow(Double.parseDouble(value), 2) + "";
		}catch(Exception e){
			System.out.println(e.getMessage());
			return "";
		}

		
	}

	/**
	* Method Name: FindSquareRoot
	* Purpose: to find square root of the value
	* Accepts: boolean
	* Returns: String
	*/
	
	String findSquareRoot(String value){

		
		
		try{
			if (Double.parseDouble(value) < 0){
				return 0 + "";
			}else{
			
				return Math.sqrt(Double.parseDouble(value)) + "";
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return "";
		}
	}

	/**
	* Method Name: buildOperand
	* Purpose: to build the the Operand 
	* Accepts: String
	* Returns: String
	*/
	
	
	String buildOperand(String value) throws LongOprandException{
		if(operand.length() > 19){
			throw new LongOprandException();
		}
		else
		{
			this.operand += value;
		}
		
		return operand;
	}

	/**
	* Method Name: buildExpersion
	* Purpose: to build the the expression
	* Accepts: String
	* Returns: String
	*/
	
	void buildExpression(String value){

		
			
			if (operand != "")
			{
				list.add(operand);
			}
			operator = value;

			list.add(value);
			clear();
		
	}

	/**
	* Method Name: calculate
	* Purpose: to do the calculation
	* Accepts: none
	* Returns: double
	*/
	
	double calculate() throws ArithmeticException {
		double result = 0;
		try{

		list.add(this.operand);
		double o1 = 0;
		double o2 = 0;
		String s = list.get(list.size() - 2);

		
		o1 = Double.parseDouble(list.get(list.size() - 3));
		o2 = Double.parseDouble(list.get(list.size() - 1));

		
		switch (s)
		{

		case "+":
			result = o1 + o2;
			break;
		case "-":
			result = o1 - o2;
			break;
		case "x":
			result = o1 * o2;
			break;
		case "/":
			
			if(o2 == 0){
				throw new ArithmeticException("Divided by zero");
			}else{
			
				result = o1 / o2;
			}
				

		}
		}catch(Exception e){
			System.out.println (e.getMessage());
			clear();
		}

		clear();
		return result;

	}

	//converter
	
	/**
	* Method Name: convertHex
	* Purpose: to convert decimal to hex
	* Accepts: String
	* Returns: String
	*/
	
	public String convertHex(String value){

		
		if(value == "0"){
			return "0";
		}
		
		int x = Integer.parseInt(value);

		String s = "";
		if(x%16 > 9){
			switch(x%16){
			case 10: s = "A";
			break;
			case 11: s = "B";
			break;
			case 12: s = "C";
			break;
			case 13: s = "D";
			break;
			case 14: s = "E";
			break;
			case 15: s = "F";
			
			}
		}else{
			
			s = x%16 + "";
			
		}
		
		if(x < 16){
			return s;
		}
		
		
		return convertHex(x/16 + "") + s;

	}
	
	
	/**
	* Method Name: convertDec
	* Purpose: to convert decimal to decimal
	* Accepts: String
	* Returns: String
	*/

	public String convertDec(String value){

		return value;

	}
	
	
	/**
	* Method Name: convertOct
	* Purpose: to convert decimal to octal
	* Accepts: String
	* Returns: String
	*/

	public String convertOct(String value){
		int x = Integer.parseInt(value);
		String s =  x%8 + "";
		if(x < 8){
			return x + "";
		}else{
			return  convertOct(x/8 + "") +  s + "";
		}
		
	}
	
	
	/**
	* Method Name: convertBin
	* Purpose: to convert decimal to binary
	* Accepts: String
	* Returns: String
	*/

	public String convertBin(String value){
		
		
		
		
		int decimalNum = Integer.parseInt(value);
		
		int initiater = -1;
		int checker = -1;
		int ex = 1;
		String s = "";
		
		
		
		while (initiater != 0)
		{
			checker = decimalNum - ex;
			if (checker >= 0)
			{
				ex = ex * 2;

			} else
			{
				ex = ex / 2;
				initiater++;
			}

		}
		int runNum = decimalNum;
		
		while (ex >= 1)
		{

			if (runNum >= ex)
			{

				s += "1";
				runNum = runNum - ex;
			}

			else
			{
				s += "0";

			}
			ex = ex / 2;
		}

		if(s == ""){
			return 0 + "";
		}
	
		return s;

		
		
		//return Integer.toBinaryString((Integer.valueOf(value)));
		
	}

	
	/**
	* Method Name: toString
	* Purpose: return the value of the field
	* Accepts: none
	* Returns: String
	*/
	
	public String toString()
	{
		return "Calculator [operand=" + operand + ", operator=" + operator + ", decimalPressed=" + decimalPressed
				+ ", list=" + list + "]";
	}
	
	

}
