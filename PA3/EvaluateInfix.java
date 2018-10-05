
/**
 * Write a description of class EvaluateInfix here.
 * 
 * @author (Qi Fen Chen) 
 * @version (4/24/2012)
 */
import java.util.*;
public class EvaluateInfix
{
    private double answer;
    private Stack<Character> operatorStack = new Stack<Character>();
    private Stack<Double> operandStack = new Stack<Double>();
    private Stack<Character> parenStack = new Stack<Character>();
    private Stack<Character> tempOPStack = new Stack<Character>();
    private Stack<Double> tempOperandStack = new Stack<Double>();
    private static final String JUSTOPS = "+-*/&^";
    private static final String OPERATORS = "+-*/%^(){}[]";
    private static final String PARENTHESES = "(){}[]";
    private static final int [] PRECEDENCE = {1,1,2,2,2,3,-1,-1,-1,-1,-1,-1};
    private StringBuilder postfix;
    private String in;

    //Main method
    public static void main(String [] args) throws Exception
    {
        EvaluateInfix a = new EvaluateInfix();
    }
    //clears all the stacks
    public void clear ()
    {
        operandStack.clear();
        parenStack.clear();
        operatorStack.clear();
        tempOPStack.clear();
        tempOperandStack.clear();
    }
    //Constructor for Evaluating Infix
    public EvaluateInfix () throws Exception
    {
        Scanner input = new Scanner (System.in);
        System.out.println("WELCOME TO THE SUPER DUPER JAVA CALCULATOR by Chen");
        String s ="";
        String m ="";
        do {
            clear();
            System.out.println("Enter the infix that you'd want to evaluate (enter X  to quit):");
            s = input.nextLine();
            String f="";
            for (int i =0; i< s.length(); i++)
            {
                if (s.equals("x") ||s.equals("X"))
                    break;
                if (!isOperator(s.charAt(i)))
                    f+= s.charAt(i);
                if (isOperator(s.charAt(i)))
                    f+= " " + s.charAt(i) + " ";
            }
            int count =0;
            for (int i = 0 ; i<f.length(); i++)
            {
                if ( f.charAt(i) != ' '){
                    count = i;
                    break;
                }
            }
            m = f.substring(count,f.length());
            f = m;
            String [] tokens = s.split("\\s+");
            if (s.equals("x") || s.equals("X"))
                System.out.println("Calculation complete!");
            else {
                if (valid(m) ){
                    if (validParen(m))
                    {
                        if (numberValid(m)) 
                        {
                            try {
                                double ans = eval(m);
                                System.out.println("Answer = " + ans);
                            } catch (EmptyStackException erhi) {
                                System.out.println("Not enough is entered to be evaluated!");
                            }
                        } else
                            System.out.println("Too many operators/operands in a row!");
                    } else
                        System.out.println("Parentheses/Brackets/Braces is not matching up!");
                } else
                    System.out.println("Unrecognized Character(s) appeared!");
            }
        } while (!s.equals("x") && !s.equals("X"));
    }
    //Evaluates each individual operations
    private void evalOP(char op)
    {
        if (op!='(' && op!='[' && op!= '{'){
            double rhs = operandStack.pop();
            double lhs = operandStack.pop();
            double result =0;
            switch (op) {
                case '+': result = lhs +rhs;
                break;
                case '-': result = lhs-rhs;
                break;
                case '/': result = lhs/rhs;
                break;
                case '*': result = lhs*rhs;
                break;
                case '%': result = lhs%rhs;
                break;
                case '^': result = (Math.pow(lhs,rhs));
                break;
            }
            operandStack.push(result);
        }
    }
    //checks if it's a operator not including parentheses
    private boolean isOP(char ch) {
        return JUSTOPS.indexOf(ch) !=-1;
    }
    //checks if it's a operator including parentheses
    private boolean isOperator(char ch) {
        return OPERATORS.indexOf(ch) !=-1;
    }
    //checks if it's a parentheses
    private boolean isParen(char ch) {
        return PARENTHESES.indexOf(ch) !=-1;
    }
    //checks for precedency
    private int precedence(char op)
    {
        return PRECEDENCE[OPERATORS.indexOf(op)] ;
    }
    //checks if all the parentheses is valid
    public boolean validParen(String ex)
    {
        String [] tokens = ex.split("\\s+");
        try {
            for (String nextToken:tokens)
            {
                char firstChar = nextToken.charAt(0);
                if (isParen(firstChar))
                {
                    if ( firstChar == '(' || firstChar =='[' || firstChar == '{')
                        parenStack.push(firstChar);
                    else if (firstChar==')') {
                        char topOP = parenStack.peek();
                        while( topOP != '(')
                        {
                            if (!operatorStack.empty()) {
                                topOP = parenStack.pop();
                            } else {
                                return false;
                            }
                        }
                        parenStack.pop();
                    } else if (firstChar ==']') {
                        char topOP = parenStack.peek();
                        while( topOP != '[')
                        {
                            if (!operatorStack.empty()) {
                                topOP = parenStack.pop();
                            } else {
                                return false;
                            }
                        }
                        parenStack.pop();
                    } else if (firstChar =='}') {
                        char topOP = parenStack.peek();
                        while( topOP != '{')
                        {
                            if (!operatorStack.empty()) {
                                topOP = parenStack.pop();
                            } else {
                                return false;
                            }
                        }
                        parenStack.pop();
                    }
                }
            }
            if (!parenStack.empty())
                return false;
        }catch (EmptyStackException ui){
            return false;
        }
        return true;
    }
    //checks if the number and operations are valid
    public boolean numberValid(String s)
    {
        String [] tokens = s.split("\\s+");
        for (int i =1; i<tokens.length; i++)
        {
            char firstChar = tokens[i-1].charAt(0);
            char secondChar = tokens[i].charAt(0);
            if (Character.isDigit(firstChar) && Character.isDigit(secondChar)) {
                return false;
            }
            if (isOP(firstChar) && isOP(secondChar))
                return false;
        }
        return true;
    }
    //checks if the whole string makes sense
    public boolean valid(String expression)
    {
        String [] tokens = expression.split("\\s+");
        try {
            for (String nextToken:tokens)
            {
                char firstChar = nextToken.charAt(0);
                if(Character.isDigit(firstChar) || firstChar == '.') {
                    try {
                        double value = Double.parseDouble(nextToken);
                    }catch (Exception uiuh){
                        return false;
                    }
                } else if(isOperator(firstChar)) {

                } else {
                    return false;
                }
            }
        }catch (Exception iuiweh){
            System.out.println("");
            return false;
        }
        return true;
    }
    //evaluates the correct formatted equation
    public double eval (String expression) throws EmptyStackException
    {
        String [] tokens = expression.split("\\s+");
        for (String nextToken: tokens)
        {
            char firstChar = nextToken.charAt(0);
            if(Character.isDigit(firstChar) || firstChar == '.') {
                double value = Double.parseDouble(nextToken);
                operandStack.push(value);
            } 
            else {
                if (operatorStack.empty() || firstChar=='(' ||firstChar=='{' ||firstChar=='['){
                    operatorStack.push(firstChar);
                } else {
                    processOperator(firstChar);
                }
            }
        }
        while (!operatorStack.empty())
        {
            evalOP(operatorStack.pop());
        }
        double answer = operandStack.pop();
        if (!operandStack.empty())
            throw new EmptyStackException();
        return answer;
    }
    //processes the operator
    public void processOperator(char op)
    {
        char topOP = operatorStack.peek();
        int count =0;
        if (precedence(op) > precedence(topOP)) {
            operatorStack.push(op);
        }
        else {
            if (op==')' || op=='}' || op==']'){
                while (topOP!='(' && topOP!='[' && topOP!= '{'){
                    evalOP(operatorStack.pop());
                    if (!operatorStack.empty()) {
                        topOP = operatorStack.peek();
                    }
                }
            }
            else {
                while(!operatorStack.empty() && precedence(op) <= precedence(topOP)){
                    evalOP(operatorStack.pop());
                    if (topOP =='(' ||topOP=='[' ||topOP=='{')
                        break;
                    if (!operatorStack.empty()) 
                        topOP = operatorStack.peek();
                }
                if (op!= ')' && op !=']' && op!='}')
                    operatorStack.push(op);
            }
        }
    }
}