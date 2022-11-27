package com.example.success;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newClass;
    String[] numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newClass = findViewById(R.id.newClass6);
        numbers = new String[]{};


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                getAddActivityResult.launch(intent);
            }
        });



    }

    private final ActivityResultLauncher<Intent> getAddActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){

                    newClass.setText(result.getData().getStringExtra("className"));
                    newClass.setVisibility(newClass.VISIBLE);
                    numbers = result.getData().getStringArrayExtra("numbers");

                }
            }
    );

    public void gotoselect(View view){
        Intent intent2 = new Intent(MainActivity.this,SelectActivity.class);
        //intent2.putExtra("numbers",numbers);
        startActivity(intent2);
        finish();
    }


}