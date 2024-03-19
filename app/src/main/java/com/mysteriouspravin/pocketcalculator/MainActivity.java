package com.mysteriouspravin.pocketcalculator;
import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultsScreen;
    private TextView solutionView;
    private String currentNumber = "";
    private String operator = "";
    private double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultsScreen = findViewById(R.id.results_screen);
        solutionView = findViewById(R.id.solution_view);

        // Attach onClickListeners to all number buttons
        int[] numberButtonIds = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};
        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNumberClick(v);
                }
            });
        }

        // Attach onClickListeners to operator buttons
        int[] operatorButtonIds = {R.id.button_plus, R.id.button_minus, R.id.button_multiply, R.id.button_divide};
        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOperatorClick(v);
                }
            });
        }

        // Clear button onClickListener
        findViewById(R.id.button_AC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        // Equal button onClickListener
        findViewById(R.id.button_equalto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        currentNumber += button.getText().toString();
        updateDisplay();
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();
        result = Double.parseDouble(currentNumber);
        currentNumber = "";
        updateDisplay();
    }

    private void calculate() {
        double secondNumber = Double.parseDouble(currentNumber);
        switch (operator) {
            case "+":
                result += secondNumber;
                break;
            case "-":
                result -= secondNumber;
                break;
            case "*":
                result *= secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result /= secondNumber;
                } else {
                    solutionView.setText("Error");
                    return;
                }
                break;
        }
        currentNumber = String.valueOf(result);
        updateDisplay();
    }

    private void clear() {
        currentNumber = "";
        operator = "";
        result = 0.0;
        updateDisplay();
    }

    private void updateDisplay() {
        resultsScreen.setText(currentNumber);
        solutionView.setText(String.valueOf(result));
    }
}


