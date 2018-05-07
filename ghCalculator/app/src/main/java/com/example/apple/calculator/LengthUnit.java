package com.example.apple.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by apple on 2018/4/16.
 */

public class LengthUnit extends AppCompatActivity {
    private Spinner sp1, sp2;
    private EditText text;
    private TextView tv1, tv2, result;
    private String unit1, unit2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        sp1 = (Spinner) findViewById(R.id.unit_1);
        tv1 = (TextView) findViewById(R.id.text_1);
        unit1 = (String) sp1.getSelectedItem();
        sp2 = (Spinner) findViewById(R.id.unit_2);
        tv2 = (TextView) findViewById(R.id.text_2);
        unit2 = (String) sp2.getSelectedItem();

        text = (EditText) findViewById(R.id.num_1);
        result = (TextView) findViewById(R.id.num_2);

        Button button = (Button) findViewById(R.id.convertion2);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int pos, long id) {
                unit1 = (String) sp1.getSelectedItem();
                tv1.setText(unit1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int pos, long id) {
                unit2 = (String) sp2.getSelectedItem();
                tv2.setText(unit2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = text.getText().toString();
                double i = Double.parseDouble(str);
                String s;

                if (unit1.equals("米")) {
                    switch (unit2) {
                        case "米":
                            result.setText(str);
                            break;
                        case "英寸":
                            i = i * 39.3700787;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "厘米":
                            i = i / 100;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "寸":
                            i = i * 30;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                    }
                } else if (unit1.equals("英寸")) {
                    switch (unit2) {
                        case "米":
                            i = i * 0.0254;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "英寸":
                            result.setText(str);
                            break;
                        case "厘米":
                            i = i * 50000;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "寸":
                            i = i * 0.762;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                    }
                }
                else if (unit1.equals("厘米")) {
                    switch (unit2) {
                        case "米":
                            i = i / 100;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "英寸":
                            i = i * 0.3937008;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "厘米":
                            result.setText(str);
                            break;
                        case "寸":
                            i = i * 0.3;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                    }
                }
                else {
                    switch (unit2) {
                        case "米":
                            i = i * 0.0333333;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "英寸":
                            i = i * 1.312336;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "厘米":
                            i = i * 3.333333;
                            s = Double.toString(i);
                            result.setText(s);
                            break;
                        case "寸":
                            result.setText(str);
                            break;
                    }
                }
            }
        });
    }
}

