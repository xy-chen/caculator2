package com.example.caculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String expression = "";
    double result;
    double ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button num1 = (Button) findViewById(R.id.button_num1);
        Button num2 = (Button) findViewById(R.id.button_num2);
        Button num3 = (Button) findViewById(R.id.button_num3);
        Button num4 = (Button) findViewById(R.id.button_num4);
        Button num5 = (Button) findViewById(R.id.button_num5);
        Button num6 = (Button) findViewById(R.id.button_num6);
        Button num7 = (Button) findViewById(R.id.button_num7);
        Button num8 = (Button) findViewById(R.id.button_num8);
        Button num9 = (Button) findViewById(R.id.button_num9);
        Button num0 = (Button) findViewById(R.id.button_num0);
        Button numjian = (Button) findViewById(R.id.button_numjian);
        Button numdian = (Button) findViewById(R.id.button_numdian);
        Button numjia = (Button) findViewById(R.id.button_numjia);
        Button numcheng = (Button) findViewById(R.id.button_numcheng);
        Button numchu = (Button) findViewById(R.id.button_numchu);
        Button numzuokuahu = (Button) findViewById(R.id.button_numzuokuahu);
        Button numyoukuahu = (Button) findViewById(R.id.button_numyoukuahu);
        Button numDEL = (Button) findViewById(R.id.button_numDEL);
        Button numdengyu = (Button) findViewById(R.id.button_numdengyu);
        Button numAC = (Button) findViewById(R.id.button_numAC);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final ca calculathor = new ca();
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("1");

            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("2");
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("3");
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("4");
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("5");
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("6")
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("7");
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("8");
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("9");
            }
        });
        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("0");
            }
        });
        numdian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(".");
            }
        });
        numcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("*");
            }
        });
        numchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("/");
            }
        });
        numjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("+");
            }
        });
        numjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("-");
            }
        });
        numAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = "";
                textView.setText(expression);
            }
        });
        numDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expression != "") {
                    expression = expression.substring(0, (expression.length() - 1));
                    textView.setText(expression);
            }
        };
        numzuokuahu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("(");
            }
        });
        numyoukuahu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(")");
            }
        });
        numdengyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!expression.isEmpty()) {
                        String temp_exp;
                        temp_exp = expression;
                        temp_exp += "=";
                        textView.setText(temp_exp);
                        result = calculathor.evalExp(expression);
                        expression = Double.toString(result);

                        textView.setText(expression);
                        ans = result;
                        expression = "";
                        temp_exp = "";
            }
        });

    }
}
