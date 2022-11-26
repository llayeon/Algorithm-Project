package com.example.success;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText classname = findViewById(R.id.className);
        EditText num1 = findViewById(R.id.firstNum);
        EditText num2 = findViewById(R.id.secondNum);

        Button check_add = findViewById(R.id.check_add);

        check_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputClassName = classname.getText().toString();
                String sNum1 = num1.getText().toString();
                String sNum2 = num2.getText().toString();
                String[] numbers = new String[]{sNum1,sNum2};

                Intent intent = new Intent();

                intent.putExtra("className", inputClassName);
                intent.putExtra("numbers", numbers);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}