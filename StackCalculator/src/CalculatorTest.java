import java.io.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorTest
{
    public static final Pattern tokenPattern = Pattern.compile("(\\d+|[()^+\\-*/%])");
    public static final Pattern numberPattern = Pattern.compile("\\d+");

	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;
				command(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		try {
			String postfix = convertToPostfix(input);
			long answer = calcPostfix(postfix);
			System.out.println(postfix);
			System.out.println(answer);
		}
		catch (Exception e) {
			System.out.println("ERROR");
			//System.out.println(e.toString());
		}
	}

	private static int getWeight(char op) {
		switch (op) {
			case '(':
				return 0;
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
			case '%':
				return 2;
			case '~':
				return 3;
			case '^':
				return 4;
			default:
				return -1;
		}
	}

	private static String convertToPostfix(String input) throws Exception{
		Stack<Character> stack = new Stack();
		StringBuilder sb = new StringBuilder();
		boolean lastWasOp = true;
		Matcher tokenMatcher = tokenPattern.matcher(input);
		while (tokenMatcher.find()) {
			//token is a string of a number(long) or an operator
		    String token = tokenMatcher.group();
		    //if number, put it into postfix exp directly
            if (numberPattern.matcher(token).matches()) {
				//invalid exp if two numbers are adjacent without operator
			    if (!lastWasOp)
			    	throw new Exception("Invalid expression1");
				sb.append(token).append(" ");
				lastWasOp = false;
			} else {	//if operator, put it in operator stack
				char op = token.charAt(0);
				if (lastWasOp && op == '-')
					op = '~';
				if (op == '(') {
					stack.push('(');
					//invalid exp if left parenthesis come after number
					if (!lastWasOp)
						throw new Exception("Invalid expression2");
					lastWasOp = true;
				} else if (op == ')') {
					//invalid exp if exp inside parenthesis does not end with number
					if (lastWasOp)
						throw new Exception("Invalid expression3");
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.peek()).append(" ");
						stack.pop();
					}
					//invalid exp if there are more right parenthesis than left ones
					if (stack.isEmpty())
						throw new Exception("Parenthesis does not match");
					else
						stack.pop();
				} else {
					//pop operators in stack with greater than or equal weight (to implement precedence)
                    while (!stack.isEmpty() && getWeight(stack.peek()) >= getWeight(op)) {
                    	//in case of ^ and ~, pop operators in stack with greater wieght only (to implement right associative)
                    	if(stack.peek() == op && (op == '^' || op == '~'))
                    		break;
                        sb.append(stack.peek()).append(" ");
                        stack.pop();
                    }
                    stack.push(op);
                    lastWasOp = true;
				}
			}
		}
		while (!stack.isEmpty()) {
			//invalid exp if left parenthesis is more than right
            if(stack.peek() == '(')
                throw new Exception("Parenthesis does not match");
            sb.append(stack.peek()).append(" ");
            stack.pop();
        }
        return sb.toString().trim();
	}

	private static long calcPostfix(String input) throws Exception {
		String[] tokens = input.split(" ");
		Stack<Long> stack = new Stack();
		//each token contains a string of number(long) or an operator
		for (String token : tokens) {
			if (numberPattern.matcher(token).matches()) {
				stack.push(Long.parseLong(token));
			} else {
				//at least one number is needed for any operator
				if (stack.isEmpty())
					throw new Exception("Invalid expression4");
			    char op = token.charAt(0);
			    if (op == '~') {
                    long num = stack.peek();	stack.pop();
                    stack.push(-num);
				} else {
			        long num2 = stack.peek();	stack.pop();
			        //at least two numbers are needed for binary operator
			        if (stack.isEmpty())
			        	throw new Exception("Invalid expression5");
			        long num1 = stack.peek();	stack.pop();
			        switch (op) {
						case '^' :
							if(num1 == 0 && num2 < 0)
								throw new Exception("0^neg form");
							else
                                stack.push((long)Math.pow(num1, num2));
						    break;
						case '*' :
							stack.push(num1 * num2);	break;
						case '/' :
							if (num2 == 0)
								throw new Exception("Divide by zero in /");
                            stack.push(num1  / num2);
							break;
						case '%' :
							if (num2 == 0)
								throw new Exception("Divide by zero in %");
                            stack.push(num1 % num2);
							break;
						case '+' :
							stack.push(num1 + num2);	break;
						case '-' :
							stack.push(num1 - num2);	break;
						default :
							//should never reach here if there are only valid operators in exp
							throw new Exception("Invalid expression6");
					}
				}

			}
		}
		//only final result should remain in the stack if it is a valid exp
		if(stack.size() != 1)
			throw new Exception("Invalid expression");
		return stack.peek();
	}
}
