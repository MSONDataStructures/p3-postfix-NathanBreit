package evaluator.arith;

import evaluator.Evaluator;
import evaluator.IllegalPostfixExpressionException;
import language.BinaryOperator;
import language.Operand;
import language.Operator;
import parser.PostfixParser;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;

/**
 * An {@link ArithPostfixEvaluator} is a post fix evaluator
 * over simple arithmetic expressions.
 */
public class ArithPostfixEvaluator implements Evaluator<Integer> {

    private final StackInterface<Operand<Integer>> stack;

    /**
     * Constructs an {@link ArithPostfixEvaluator}.
     */
    public ArithPostfixEvaluator() {
        //TODO Initialize to your LinkedStack
        stack = new LinkedStack<>();
    }

    /**
     * Evaluates a postfix expression.
     * @return the result
     */
    @Override
    public Integer evaluate(String expr) {
        // TODO Use all the things built so far to create
        //   the algorithm for postfix evaluation
        ArithPostfixParser parser = new ArithPostfixParser(expr);
        while (parser.hasNext()) {
            switch (parser.nextType()) {
                case OPERAND:
                    Operand<Integer> operand = parser.nextOperand();
                    stack.push(operand);
                    break;
                case OPERATOR:
                    Operator<Integer> operator = parser.nextOperator();
                    Operand<Integer> returnValue;
                    Operand<Integer> operand1 = stack.pop();
                    if (operator.getNumberOfArguments() == 2) {
                        if (stack.isEmpty()) {
                            throw new IllegalPostfixExpressionException("Missing operand");
                        }
                        Operand<Integer> operand2 = stack.pop();
                        operator.setOperand(0, operand2);
                        operator.setOperand(1, operand1);
                        returnValue = operator.performOperation();
                    } else {
                        operator.setOperand(0, operand1);
                        returnValue = operator.performOperation();
                    }
                    stack.push(returnValue);
                    break;
                default:
                    throw new IllegalPostfixExpressionException("You input is bad. My code is perfect.");
            }
        }
        if (stack.size() != 1) {
            throw new IllegalPostfixExpressionException("You input is bad. My code is perfect.");
        }
        return stack.pop().getValue();
    }
}
