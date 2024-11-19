package com.vivek.calculator;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button ac = findViewById(R.id.ac);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button zero = findViewById(R.id.zero);
        Button plus = findViewById(R.id.plus);
        Button sub = findViewById(R.id.subtract);
        Button multi = findViewById(R.id.multiply);
        Button div = findViewById(R.id.division);
        Button equals = findViewById(R.id.equals);
        Button percentage = findViewById(R.id.percentage);
        Button c = findViewById(R.id.c);
        Button dot = findViewById(R.id.dot);
        Button doubleZero = findViewById(R.id.doubleZero);

        TextView screen = findViewById(R.id.screen);

        screen.setText("Welcome");
        new Handler().postDelayed(() -> {
            screen.setText("0");
        }, 1000);

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(one);
        nums.add(two);
        nums.add(three);
        nums.add(four);
        nums.add(five);
        nums.add(six);
        nums.add(seven);
        nums.add(eight);
        nums.add(nine);
        nums.add(zero);
        nums.add(doubleZero);

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if(!screen.getText().toString().equals("0"))
                    screen.setText(screen.getText().toString() + b.getText().toString());
                else
                    screen.setText(b.getText().toString());
            });
        }

        ac.setOnClickListener(view -> {
            screen.setText("0");
        });

        ArrayList<Button> op = new ArrayList<>();
        op.add(plus);
        op.add(sub);
        op.add(multi);
        op.add(div);
        op.add(percentage);

        final double[] a = new double[1];
        final double[] b = new double[1];
        final char[] operator = new char[1];

        for (Button ops : op) {
            ops.setOnClickListener(view -> {
                try {
                    a[0] = Double.parseDouble(screen.getText().toString());
                    operator[0] = ops.getText().toString().charAt(0);
                    screen.setText("0");
                } catch (NumberFormatException e) {
                    screen.setText("Error");
                }
            });
        }

        final double[] result = new double[1];

        equals.setOnClickListener(view -> {
            try {
                b[0] = Double.parseDouble(screen.getText().toString());
                switch (operator[0]) {
                    case ('+'):
                        result[0] = a[0] + b[0];
                        break;
                    case ('-'):
                        result[0] = a[0] - b[0];
                        break;
                    case ('X'):
                        result[0] = a[0] * b[0];
                        break;
                    case ('÷'):
                        if (b[0] != 0) {
                            result[0] = a[0] / b[0];
                        } else {
                            screen.setText("Not Allowed 😒");
                            return;
                        }
                        break;
                    case ('%'):
                        result[0] = a[0] % b[0];
                        break;
                    default:
                        break;
                }
                screen.setText(String.valueOf(result[0]));
            } catch (NumberFormatException e) {
                screen.setText("Error");
            }
        });

        dot.setOnClickListener(view -> {
            String currentText = screen.getText().toString();

            if (!currentText.contains(".")) {
                screen.setText(currentText + ".");
            }
        });

        c.setOnClickListener(view -> {
            if (screen.getText().toString().length() > 1) {
                screen.setText(screen.getText().toString().substring(0,screen.getText().toString().length() - 1));
            } else {
                screen.setText("0");
            }
        });
    }
}
