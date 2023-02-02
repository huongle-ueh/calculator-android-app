package com.example.mycalculator;

import android.view.Window;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    TextView input,  output;

    private ArrayList<String> arrOperation;
    private ArrayList<Double> arrNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        input = (TextView) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
        Button AC = (Button) findViewById(R.id.AC);
        Button cham = (Button)findViewById(R.id.thapphan);
        Button xoa = (Button) findViewById(R.id.xoa);
        Button cong = (Button) findViewById(R.id.cong);
        Button tru = (Button) findViewById(R.id.tru);
        Button nhan = (Button) findViewById(R.id.nhan);
        Button chia = (Button) findViewById(R.id.chia);
        Button result = (Button) findViewById(R.id.bang);
        Button num0 = (Button) findViewById(R.id.num0);
        Button num1 = (Button) findViewById(R.id.num1);
        Button num2 = (Button) findViewById(R.id.num2);
        Button num3 = (Button) findViewById(R.id.num3);
        Button num4 = (Button) findViewById(R.id.num4);
        Button num5 = (Button) findViewById(R.id.num5);
        Button num6 = (Button) findViewById(R.id.num6);
        Button num7 = (Button) findViewById(R.id.num7);
        Button num8 = (Button) findViewById(R.id.num8);
        Button num9 = (Button) findViewById(R.id.num9);

        AC.setOnClickListener(v -> {
            input.setText("");
            output.setText("0");
        });
        xoa.setOnClickListener(v -> {
            StringBuffer str = new StringBuffer(input.getText());
            if(str.length() > 0){
                str.deleteCharAt(str.length()-1);input.setText(str);
            }
        });
        num0.setOnClickListener(v -> input.setText(String.format("%s0", input.getText())));
        num1.setOnClickListener(v -> input.setText(String.format("%s1", input.getText())));
        num2.setOnClickListener(v -> input.setText(String.format("%s2", input.getText())));
        num3.setOnClickListener(v -> input.setText(String.format("%s3", input.getText())));
        num4.setOnClickListener(v -> input.setText(String.format("%s4", input.getText())));
        num5.setOnClickListener(v -> input.setText(String.format("%s5", input.getText())));
        num6.setOnClickListener(v -> input.setText(String.format("%s6", input.getText())));
        num7.setOnClickListener(v -> input.setText(String.format("%s7", input.getText())));
        num8.setOnClickListener(v -> input.setText(String.format("%s8", input.getText())));
        num9.setOnClickListener(v -> input.setText(String.format("%s9", input.getText())));
        cham.setOnClickListener(v -> {
            if(input.getText().length() >0)
                if(Character.isDigit(input.getText().charAt(input.getText().length()-1)) )
                    input.setText(String.format("%s.", input.getText()));
        });

        tru.setOnClickListener(v -> {
            if(output.getText()!="0" && input.getText()=="")
                input.setText(String.format("%s-", output.getText()));

            if(input.getText().length()==0)
                input.setText(String.format("%s-", input.getText()));

            else if(Character.isDigit(input.getText().charAt(input.getText().length()-1))
                    || input.getText().charAt(input.getText().length()-1)=='/'
                    || input.getText().charAt(input.getText().length()-1)=='x' ) {
                input.setText(String.format("%s-", input.getText()));
            }
        });

        cong.setOnClickListener(v -> {
            if(input.getText().length() >0)
                if(Character.isDigit(input.getText().charAt(input.getText().length()-1)) )
                    input.setText(String.format("%s+", input.getText()));
            if(output.getText()!="0" && input.getText()=="")
                input.setText(String.format("%s+", output.getText()));
        });

        nhan.setOnClickListener(v -> {
            if(input.getText().length() >0)
                if(Character.isDigit(input.getText().charAt(input.getText().length()-1)))
                    input.setText(String.format("%sx", input.getText()));
            if(output.getText()!="0" && input.getText()=="")
                input.setText(String.format("%sx", output.getText()));
        });

        chia.setOnClickListener(v -> {
            if(input.getText().length() >0)
                if(Character.isDigit(input.getText().charAt(input.getText().length()-1)))
                    input.setText(String.format("%s/", input.getText()));

            if(output.getText()!="0" && input.getText()=="")
                input.setText(String.format("%s/", output.getText()));
        });

        result.setOnClickListener(v -> {
            if(input.getText().length() > 1 )
                Result();
            input.setText("");
        });
    }

    public int addOperation(String input) {
        arrOperation = new ArrayList<>();
        addNumber(this.input.getText().toString());

        char[] cArray = input.toCharArray();
        for (int i = 1; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                case '-':
                case 'x':
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
    public void Result()
    {
        double result;
        DecimalFormat numberFormat = new DecimalFormat("###.##");
        addOperation(input.getText().toString());

        for (int i = 0; i < (arrNumber.size() - 1); i++) {
            switch (arrOperation.get(i)) {
                case "x":
                    arrNumber.set(i, arrNumber.get(i) * arrNumber.get(i + 1));
                    arrNumber.remove(i+1);
                    arrOperation.remove(i);
                    i--;
                    break;
                case "/":
                    arrNumber.set(i, arrNumber.get(i) / arrNumber.get(i + 1));
                    arrNumber.remove(i+1);
                    arrOperation.remove(i);
                    i--;
                default:
                    break;
            }
        }
        result = arrNumber.get(0);
        for (int i = 0; i < (arrNumber.size() - 1); i++) {
            switch (arrOperation.get(i)) {
                case "+":
                    result = result + arrNumber.get(i + 1);
                    break;
                case "-":
                    result = result - arrNumber.get(i + 1);
                    break;
                default:
                    break;
            }
        }
        output.setText(String.format("%s", numberFormat.format(result)));
    }
}