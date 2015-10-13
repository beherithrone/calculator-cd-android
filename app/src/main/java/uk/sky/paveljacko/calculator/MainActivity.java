package uk.sky.paveljacko.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.paveljacko.calculator.R;

public class MainActivity extends AppCompatActivity implements CalculatorEngineListener {

    private CalculatorEngine calculatorEngine;
    private TextView textView;

    ButtonHolder[] numbers = new ButtonHolder[]{
            new ButtonHolder(R.id.btn_0, 0),
            new ButtonHolder(R.id.btn_1, 1),
            new ButtonHolder(R.id.btn_2, 2),
            new ButtonHolder(R.id.btn_3, 3),
            new ButtonHolder(R.id.btn_4, 4),
            new ButtonHolder(R.id.btn_5, 5),
            new ButtonHolder(R.id.btn_6, 6),
            new ButtonHolder(R.id.btn_7, 7),
            new ButtonHolder(R.id.btn_8, 8),
            new ButtonHolder(R.id.btn_9, 9)
    };

    ButtonHolder[] operators = new ButtonHolder[]{
            new ButtonHolder(R.id.btn_divide, CalculatorEngine.Operator.Divide),
            new ButtonHolder(R.id.btn_minus, CalculatorEngine.Operator.Minus),
            new ButtonHolder(R.id.btn_multiply, CalculatorEngine.Operator.Multiply),
            new ButtonHolder(R.id.btn_equals, CalculatorEngine.Operator.Equals),
            new ButtonHolder(R.id.btn_plus, CalculatorEngine.Operator.Plus)
    };

    ButtonHolder[] actions = new ButtonHolder[]{
            new ButtonHolder(R.id.btn_clear, CalculatorEngine.Operator.Clear),
            new ButtonHolder(R.id.btn_comma, CalculatorEngine.Operator.Comma),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txt_result);
        calculatorEngine = new CalculatorEngine(this);

        for (final ButtonHolder buttonHolder : numbers) {
            findViewById(buttonHolder.id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculatorEngine.addNumber(buttonHolder.value);
                }
            });
        }

        for (final ButtonHolder buttonHolder : operators) {
            findViewById(buttonHolder.id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculatorEngine.addOperation(buttonHolder.operator);
                }
            });
        }

        for (final ButtonHolder buttonHolder : actions) {
            findViewById(buttonHolder.id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculatorEngine.doAction(buttonHolder.operator);
                }
            });
        }
    }

    @Override
    public void updateResult(String value) {
        textView.setText("Result is: " + value);
    }

    private class ButtonHolder {
        public int id;
        public CalculatorEngine.Operator operator;
        public int value;

        public ButtonHolder(int id, CalculatorEngine.Operator operator) {
            this.id = id;
            this.operator = operator;
        }

        public ButtonHolder(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }
}
