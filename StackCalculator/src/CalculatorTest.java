import java.io.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorTest
{
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
			int answer = calcPostfix(postfix);
			System.out.println(postfix);
			System.out.println(answer);
		}
		catch (Exception e) {
			//FIXME : Change after finish
			System.out.println("ERROR" + " : " + e.toString());
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
		if(!input.matches("(\\d+|\\s|[\\(\\)\\^\\+\\-\\*/%])*"))
			throw new Exception("Undefined symbol in input");
		Stack<Character> stack = new Stack();
		StringBuilder sb = new StringBuilder();
		boolean lastWasOp = true;
		Pattern pattern = Pattern.compile("(\\d+|[\\(\\)\\^\\+\\-\\*/%])");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
		    String token = matcher.group();
			if (token.matches("\\d+")) {
				sb.append(token + " ");
				lastWasOp = false;
			} else {
				if(token.length() != 1)
					throw new Exception("Invalid operator");
				char op = token.charAt(0);
				if (lastWasOp && op == '-')
					op = '~';
				if (op == ')') {
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.peek() + " ");
						stack.pop();
					}
					if (stack.isEmpty())
						throw new Exception("Parenthesis does not match");
					else
						stack.pop();
				} else {
					while (!stack.isEmpty() && getWeight(stack.peek()) >= getWeight(op)) {
						sb.append(stack.peek() + " ");
						stack.pop();
					}
					stack.push(op);
					lastWasOp = true;
				}
			}
		}
		while (!stack.isEmpty()) {
            if(stack.peek() == '(')
                throw new Exception("Parenthesis does not match");
            sb.append(stack.peek() + " ");
            stack.pop();
        }
        return sb.toString().trim();
	}

	private static int calcPostfix(String input) throws Exception {
		String[] tokens = input.split(" ");
		Stack<Integer> stack = new Stack();
		for (String token : tokens) {
			if (token.matches("\\d+")) {
				stack.push(Integer.parseInt(token));
			} else {
				if (stack.isEmpty())
					throw new Exception("Invalid expression");
			    char op = token.charAt(0);
			    if (op == '~') {
                    int num = stack.peek();		stack.pop();
                    stack.push(-num);
				} else {
			        int num1 = stack.peek();	stack.pop();
			        if (stack.isEmpty())
			        	throw new Exception("Invalid expression");
			        int num2 = stack.peek();	stack.pop();
			        switch (op) {
						case '^' :
							//TODO
						case '*' :
							stack.push(num1 * num2);	break;
						case '/' :
							if (num2 == 0)
								throw new Exception("Divide by zero in /");
							else
								stack.push(num1  / num2);
							break;
						case '%' :
							if (num2 == 0)
								throw new Exception("Divide by zero in %");
							else
								stack.push(num1 % num2);
							break;
						case '+' :
							stack.push(num1 + num2);	break;
						case '-' :
							stack.push(num1 - num2);	break;
						default :
							throw new Exception("Invalid expression");
					}
				}

			}
		}
		if(stack.size() != 1)
			throw new Exception("Invalid expression");
		return stack.peek();
	}

}
