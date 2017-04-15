package com.example.sabita_sant.alarm;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ArithTest extends AppCompatActivity {

    TextView op1,op2, operand,alert;
    int val1,val2;
    EditText res;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arith_test);
        op1= (TextView) findViewById(R.id.operand1);
        op2= (TextView) findViewById(R.id.operand2);
        operand = (TextView) findViewById(R.id.operator);
        res= (EditText) findViewById(R.id.res);
        alert= (TextView) findViewById(R.id.alert_text);
        set();
    }

    private void set() {
        char op[]={'+','-','*','/'};
        int r= (int) (Math.random()*10%4);
        operand.setText(String.valueOf(op[r]));
        val1= (int) ((Math.random()*100)%23);
        op1.setText(String.valueOf(val1));
        val2=(int) ((Math.random()*100)%16+1);
        op2.setText(String.valueOf(val2));
        switch (r)
        {
            case 0:
                result= String.valueOf((val1+val2));
                break;
            case 1:
                result= String.valueOf((val1-val2));
                break;
            case 2:
                result= String.valueOf((val1*val2));
                break;
            case 3:
                result= String.valueOf((val1/val2));
                break;

        }
        Toast.makeText(this,"res "+result,Toast.LENGTH_SHORT).show();

    }

    public void check(View view) {
        if(res.getText().toString().equals(result)){
            AlarmReceiver.ins.mediaPlayer.stop();
        }
        else
        {
            alert.setText("Sorry!! Make another try to STOP ");
            set();
        }

    }
}
