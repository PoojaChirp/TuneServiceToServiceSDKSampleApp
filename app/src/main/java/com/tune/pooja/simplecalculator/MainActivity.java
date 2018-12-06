package com.tune.pooja.simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tune.ITune;
import com.tune.Tune;
import com.tune.TuneEvent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd, btnSubtract, btnMultiple, btnDivide, btnResult, btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    private TextView txtRes;
    private EditText editValues;
    private String prevNum = Integer.toString(0), sign = null ;
    private double  prev = 0.0;

//    private View.OnClickListener myClickListener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }

    private void init() {

        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiple = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnResult = findViewById(R.id.btnResult);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        editValues = findViewById(R.id.editValues);
        txtRes = findViewById(R.id.txtRes);

        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiple.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        // get the current number in the variable number and previous number in prev
        double cur;
        switch(view.getId()){


            // make enums for the operators
            case R.id.btnAdd:
                sign = "add";
                prevNum = txtRes.getText().toString();
                prev = Double.valueOf(prevNum);
                editValues.setText(null);
                txtRes.setText(null);
                break;
            case R.id.btnSubtract:
                sign = "sub";
                prevNum = txtRes.getText().toString();
                prev = Double.valueOf(prevNum);
                editValues.setText(null);
                txtRes.setText(null);
                break;
            case R.id.btnMultiply:
                sign = "mul";
                prevNum = txtRes.getText().toString();
                prev = Double.valueOf(prevNum);
                editValues.setText(null);
                txtRes.setText(null);
                break;
            case R.id.btnDivide:
                sign= "div";
                prevNum = txtRes.getText().toString();
                prev = Double.valueOf(prevNum);
                editValues.setText(null);
                txtRes.setText(null);
                break;

            case R.id.btnResult:
                cur =  Double.valueOf(editValues.getText().toString());
                calculate(sign,cur,prev);
                editValues.setText(null);
                    break;


            case R.id.btn0:
                initNumber(editValues, btn0.getText().toString());
                break;
            case R.id.btn1:
                initNumber(editValues, btn1.getText().toString());
                break;
            case R.id.btn2:
                initNumber(editValues, btn2.getText().toString());
                break;
            case R.id.btn3:
                initNumber(editValues, btn3.getText().toString());
                break;
            case R.id.btn4:
                initNumber(editValues, btn4.getText().toString());
                break;
            case R.id.btn5:
                initNumber(editValues, btn5.getText().toString());
                break;
            case R.id.btn6:
                initNumber(editValues, btn6.getText().toString());
                break;
            case R.id.btn7:
                initNumber(editValues, btn7.getText().toString());
                break;
            case R.id.btn8:
                initNumber(editValues, btn8.getText().toString());
                break;
            case R.id.btn9:
                initNumber(editValues, btn9.getText().toString());
                break;

        }

    }

    private void initNumber(EditText editText, String newlySelected) {


            final String existingString = editText.getText().toString();

            //Concatenate the existing string with the newly selected number (0 to 9).
            final String finalValue = existingString + newlySelected;
            //Set the EditText value with the updated String

            editText.setText(finalValue);


        txtRes.setText(editText.getText());
    }

    private void calculate(String operation, double number, double prevNum) {
       // Variable to store the resultant value of the calc
        double value =0;

        switch(operation) {
            case "add":
                value = number + prevNum;
                break;
            case "sub":
                value = prevNum - number;
                break;
            case "mul":
                value = number * prevNum;
                break;
            case "div":
                if (number != 0.0)
                value = prevNum / number;
                else
                    value = 0.0;
                break;
        }
            txtRes.setText(Double.toString(value));
        if (value > 10) {
            ITune tune = Tune.getInstance();
            tune.setUserId("userId");
            tune.measureEvent(new TuneEvent(TuneEvent.LEVEL_ACHIEVED).withLevel(5));
        }
    }


}



