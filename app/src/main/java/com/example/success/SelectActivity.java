package com.example.success;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SelectActivity extends AppCompatActivity {


    List<Model> mModelList;
    List<Model> newList;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    Button gotoresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        gotoresult = findViewById(R.id.gotoresult);
        mAdapter = new RecyclerViewAdapter(getListData());

        GridLayoutManager manager = new GridLayoutManager(SelectActivity.this,5);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        //resultActivity로 안가고 recyclerview두개로 해서 화면 전환 -> 테이블 포지션 사용가능할지도?
        gotoresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //결과화면으로 이동
                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                //intent.putextra로 선택된 table 넘김
                //intent.putExtra("tableset", (CharSequence) mModelList);

                startActivity(intent);
                finish();

            }
        });

    }

    private  List<Model> getListData(){
        //책상을 담을 arraylist생성
        mModelList = new ArrayList<>();
        //입력받은 개수만큼 책상추가
        for(int i=1;i<=30;i++){
            mModelList.add(new Model(i));
        }
        return mModelList;

    }

    public List<Model> getcheckData(){

        newList = new ArrayList<>();
        for(int i =1;i<=30;i++){
            if(new Model(i).isSelected){
                newList.add(new Model(i));
            }
        }
        return newList;
    }

}
