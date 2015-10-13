package uk.sky.paveljacko.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorEngineTest {

    @Mock
    private CalculatorEngineListener listener;

    private CalculatorEngine sut;

    @Before
    public void startUp() {
        sut = new CalculatorEngine(listener);
    }

    @Test
    public void testAddNumber() throws Exception {
        sut.addNumber(5);
        Mockito.verify(listener).updateResult("5");
        sut.addNumber(6);
        Mockito.verify(listener).updateResult("56");
        sut.addNumber(1);
        Mockito.verify(listener).updateResult("561");
    }

    @Test
    public void testPlusOperator() throws Exception {
        sut.addNumber(6);
        Mockito.verify(listener).updateResult("6");
        sut.addOperation(CalculatorEngine.Operator.Plus);
        sut.addNumber(4);
        Mockito.verify(listener).updateResult("4");
        sut.addOperation(CalculatorEngine.Operator.Equals);
        Mockito.verify(listener).updateResult("10.0");
    }

    @Test
    public void testMinusOperator() throws Exception {
        sut.addNumber(7);
        Mockito.verify(listener).updateResult("7");
        sut.addOperation(CalculatorEngine.Operator.Minus);
        sut.addNumber(3);
        Mockito.verify(listener).updateResult("3");
        sut.addOperation(CalculatorEngine.Operator.Equals);
        Mockito.verify(listener).updateResult("4.0");
    }

    @Test
    public void testMultiplyOperator() throws Exception {
        sut.addNumber(2);
        Mockito.verify(listener).updateResult("2");
        sut.addOperation(CalculatorEngine.Operator.Multiply);
        sut.addNumber(8);
        Mockito.verify(listener).updateResult("8");
        sut.addOperation(CalculatorEngine.Operator.Equals);
        Mockito.verify(listener).updateResult("16.0");
    }

    @Test
    public void testDivideOperator() throws Exception {
        sut.addNumber(1);
        Mockito.verify(listener).updateResult("1");
        sut.addNumber(5);
        Mockito.verify(listener).updateResult("15");
        sut.addOperation(CalculatorEngine.Operator.Divide);
        sut.addNumber(2);
        Mockito.verify(listener).updateResult("2");
        sut.addOperation(CalculatorEngine.Operator.Equals);
        Mockito.verify(listener).updateResult("7.5");
    }

    @Test
    public void testCommaOperator() throws Exception {
        sut.addNumber(1);
        Mockito.verify(listener).updateResult("1");
        sut.doAction(CalculatorEngine.Operator.Comma);
        Mockito.verify(listener).updateResult("1");
        sut.addNumber(5);
        Mockito.verify(listener).updateResult("1.5");
    }

    @Test
    public void testClearOperator() throws Exception {
        sut.addNumber(1);
        Mockito.verify(listener).updateResult("1");
        sut.addNumber(1);
        Mockito.verify(listener).updateResult("11");
        sut.doAction(CalculatorEngine.Operator.Clear);
        Mockito.verify(listener).updateResult("0");
    }
}