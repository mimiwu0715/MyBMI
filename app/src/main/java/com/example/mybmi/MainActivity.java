package com.example.mybmi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        createConfigurationContext(conf);

        setContentView(R.layout.activity_main);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(calcBMI);
    }
    private View.OnClickListener calcBMI=new View.OnClickListener()
    {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v){
            DecimalFormat nf=new DecimalFormat("0.00");
            EditText fieldheight=(EditText)findViewById(R.id.hight);
            EditText fieldweight=(EditText)findViewById(R.id.weight);
            //身高
            double height=Double.parseDouble(fieldheight.getText().toString())/100;
            //體重
            double weight=Double.parseDouble(fieldweight.getText().toString());
            //計算出BMI值
            double BMI=weight/(height*height);
            //結果
            TextView result=(TextView)findViewById(R.id.result);
            result.setText(getText(R.string.bmi_result)+nf.format(BMI));

            //建議
             TextView fieldsuggest=(TextView)findViewById(R.id.suggest);
             if(BMI>25)//太重了
                 fieldsuggest.setText(R.string.advice_heavy);
             else if (BMI<20)//太輕了
                 fieldsuggest.setText(R.string.advice_light);
             else //剛剛好
                 fieldsuggest.setText(R.string.advice_average);
        }
    };


}