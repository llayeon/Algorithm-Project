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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//전략1 알고리즘
//배열로 알고리즘 돌려서 반환값으로(0 or 1)
//조건문써서 warning.Activity로갈지
//NewActivity로 갈지 정함

public class SelectActivity extends AppCompatActivity {


    List<Model> mModelList;
    List<Model> newList;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    Button gotoresult;
    RecyclerViewAdapter.ItemClickListener itemClickListener;
    int pos;
    String str_pos;
    int ans;

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

        newList = new ArrayList<>();

        //선택된 책상 position을 받음
        itemClickListener = new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pos = (int) mRecyclerView.getTag();
                Log.e("테이블의 pos값 ",String.valueOf(pos));
                str_pos = String.valueOf(pos);

            }
        };

        //ans => check(배열)
        if(ans == 0){
            //거리두기가 지켜지고 있지 않음
            gotoresult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectActivity.this,WarningActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else if(ans == 1){
            //거리두기가 지켜지고 있음
            gotoresult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectActivity.this,NewActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }


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



    final static int row = 6;
    final static int col = 5;

    int check(String[] getSeat){
        // TODO Auto-generated method stub
        Scanner kb = new Scanner(System.in);


        String[] arr = new String[row];


        for (int i = 0; i < row; i++) {
            arr[i] = kb.nextLine();
        }

        System.out.println();


        return ans = solution(arr);


    }



    public static int solution(String[] places) {
        int answer;

        String[] p = places;

        boolean isOk = true;
        for (int r = 0; r < row && isOk; r++) {
            for (int c = 0; c < col && isOk; c++) {
                if (p[r].charAt(c) == 'P') {
                    if (!bfs(r, c, p))
                        isOk = false;
                }
            }
        }
        answer = isOk ? 1 : 0;


        return answer;
    }

    private static boolean bfs(int r, int c, String[] p) {
        int dr[] = { -1, 1, 0, 0 };
        int dc[] = { 0, 0, -1, 1 };

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] { r, c });

        while (!queue.isEmpty()) {
            int[] position = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = position[0] + dr[i];
                int nc = position[1] + dc[i];

                if (nr < 0 || nc < 0 || nr >= row || nc >= col || (nr == r && nc == c))
                    continue;

                int d = Math.abs(nr - r) + Math.abs(nc - c);

                if (p[nr].charAt(nc) == 'P' && d <= 2)
                    return false;
                else if (p[nr].charAt(nc) == 'O' && d < 2)
                    queue.offer(new int[] { nr, nc });
            }
        }

        return true;
    }


}
