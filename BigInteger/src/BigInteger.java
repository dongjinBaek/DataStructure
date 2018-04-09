import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BigInteger
{
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";

    public static final int MAX_DIGIT = 201;
    //abs[i] : 10^i 째 자리 수
    private int[] abs = new int[MAX_DIGIT];
    private int sign;

    public int getSign(){
        return sign;
    }

    public int AbsIndexOf(int i){
        return abs[i];
    }

    public void InverseSign(){
        sign = - sign;
    }

    public BigInteger(){
        this("0");
    }

    public BigInteger(int sg, int[] num1)
    {
        sign = sg;
        abs = num1;
    }

    public BigInteger(String s)
    {
        if('0' <= s.charAt(0) && s.charAt(0) <= '9')
            s = "+" + s;

        sign = s.charAt(0) == '+' ? 1 : -1;

        for(int i=1; i < s.length(); ++i)
            abs[i - 1] = s.charAt(s.length() - i) - '0';
    }

    //this가 num보다 절댓값이 크면 true, 작거나 같으면 false 반환
    boolean AbsGreaterThan(BigInteger num){
        for(int i = MAX_DIGIT - 1; i >= 0; --i){
            if(AbsIndexOf(i) > num.AbsIndexOf(i))
                return true;
            else if(AbsIndexOf(i) < num.AbsIndexOf(i))
                return false;
        }
        return false;
    }

    //받아올림, 받아내림
    private void AdvanceUp(int[] num){
        for(int i=0; i<MAX_DIGIT-1; ++i){
            if(num[i] < 0){
                num[i+1] -= 1;
                num[i] += 10;
            }
            else if(num[i] >= 10){
                num[i+1] += num[i] / 10;
                num[i] %= 10;
            }
        }
    }

    public BigInteger add(BigInteger num)
    {
        if(num.AbsGreaterThan(this))
            return num.add(this);

        int[] ret = new int[MAX_DIGIT];
        int sg = getSign() * num.getSign();

        // 연산하는 두 수의 부호가 같으면 절댓값을 더함.
        // 두 수의 부호가 다르면 절댓값이 큰 수 에서 작은 수를 뺀다.
        for(int i=0; i<MAX_DIGIT; ++i)
            ret[i] = AbsIndexOf(i) + sg * num.AbsIndexOf(i);
        AdvanceUp(ret);

        return new BigInteger(sign, ret);
    }

    //뺄셈은 부호를 바꿔 덧셈으로 변환하여 계산한다.
    public BigInteger subtract(BigInteger num)
    {
        num.InverseSign();
        return this.add(num);
    }

    public BigInteger multiply(BigInteger num)
    {
        int[] ret = new int[MAX_DIGIT];
        for(int i=0; i<MAX_DIGIT/2; ++i){
            for(int j=0; j<MAX_DIGIT/2; ++j)
                ret[i+j] += this.AbsIndexOf(i) * num.AbsIndexOf(j);
        }
        AdvanceUp(ret);

        return new BigInteger(sign * num.getSign(), ret);
    }

    @Override
    public String toString()
    {
        //앞에서부터 읽으면서 0이 아닌 숫자를 만나면 출력하기 시작
        //만약 모든 숫자가 0이면, 그냥 0 출력
        StringBuilder sb = new StringBuilder(MAX_DIGIT + 1);
        int i=MAX_DIGIT-1;
        while(i >= 0 && AbsIndexOf(i) == 0)
            --i;
        if(i < 0)
            return "0";
        else if(getSign() == -1)
            sb.append('-');

        while(i >= 0){
            sb.append(AbsIndexOf(i));
            --i;
        }
        return sb.toString();
    }

    static BigInteger evaluate(String input) throws IllegalArgumentException
    {
        // parse input
        // using regex is allowed
        Pattern EXPRESSION_PATTERN
                = Pattern.compile("[\\s]*([\\+\\-]?[\\s]*[0-9]*)[\\s]*([\\+\\-\\*])[\\s]*([\\+\\-]?[\\s]*[0-9]*)");
        Matcher m = EXPRESSION_PATTERN.matcher(input);

        if(!m.find())
            throw new IllegalArgumentException();

        BigInteger num1 = new BigInteger(m.group(1).replaceAll(" ", ""));
        BigInteger num2 = new BigInteger(m.group(3).replaceAll(" ", ""));
        char op = m.group(2).charAt(0);
        BigInteger result = new BigInteger();

        switch(op){
            case '+' :
                result = num1.add(num2);
                break;
            case '-' :
                result = num1.subtract(num2);
                break;
            case '*' :
                result = num1.multiply(num2);
                break;
            default:
        }

        return result;
    }

    public static void main(String[] args) throws Exception
    {
        try (InputStreamReader isr = new InputStreamReader(System.in))
        {
            try (BufferedReader reader = new BufferedReader(isr))
            {
                boolean done = false;
                while (!done)
                {
                    String input = reader.readLine();

                    try
                    {
                        done = processInput(input);
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.err.println(MSG_INVALID_INPUT);
                    }
                }
            }
        }
    }

    static boolean processInput(String input) throws IllegalArgumentException
    {
        boolean quit = isQuitCmd(input);

        if (quit)
        {
            return true;
        }
        else
        {
            BigInteger result = evaluate(input);
            System.out.println(result.toString());

            return false;
        }
    }

    static boolean isQuitCmd(String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
