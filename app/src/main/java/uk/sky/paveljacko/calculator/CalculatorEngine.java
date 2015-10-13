package uk.sky.paveljacko.calculator;

public class CalculatorEngine {

    private String result = "";
    private Float value = 0.0f;
    private Operator lastOperator;
    private boolean needsClear = true;

    private final CalculatorEngineListener listener;

    public CalculatorEngine(CalculatorEngineListener listener) {
        this.listener = listener;
    }

    public void addNumber(int number) {
        if (needsClear) {
            result = "";
            needsClear = false;
        }
        result += number;
        listener.updateResult(result);
    }

    public void addOperation(Operator operator) {
        needsClear = true;
        calculate();
        lastOperator = operator;
        listener.updateResult(value.toString());
    }

    private void calculate() {
        if (lastOperator == null) {
            value = Float.parseFloat(result);
            return;
        }

        switch (lastOperator) {
            case Plus:
                value += Float.parseFloat(result);
                break;
            case Minus:
                value -= Float.parseFloat(result);
                break;
            case Multiply:
                value *= Float.parseFloat(result);
                break;
            case Divide:
                value /= Float.parseFloat(result);
                break;
        }
    }

    public void doAction(Operator operator) {
        switch (operator) {
            case Comma:
                result += ".";
                break;
            case Clear:
                result = "0";
                needsClear = true;
                value = 0.0f;
                lastOperator = null;
                listener.updateResult(result);
                break;
        }
    }

    public enum Operator {
        Plus,
        Minus,
        Divide,
        Multiply,
        Equals,
        Comma,
        Clear
    }
}