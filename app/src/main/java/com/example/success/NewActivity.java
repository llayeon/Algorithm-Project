package com.example.success;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//전략2 알고리즘
//배열받아서 체크는 사람, 가림막 들어가야할곳 배치해줌

public class NewActivity extends AppCompatActivity {

    Button gotoMain;
    //배열선언
    String[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent intent = getIntent();
        //선언된 배열에 intent로 받아온 배열 넣어줌

        //종료버튼 누르면 main으로 돌아감
        gotoMain = findViewById(R.id.gotomain);
        gotoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    final static int row = 6;
    final static int col = 5;

    //ptarr에 intent로 입력받은 배열 넣어줌
    public static int[][] ptarr = new int[row][col];


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner kb = new Scanner(System.in);


        //ptarr에 배열넣어줌


        int ans;

        ans = solution(arr);

        // System.out.println(ans + " ");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(ptarr[i][j] + " ");
            }
            System.out.println();
        }

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

            // i = 0 인 경우 위로 1칸
            // i = 1 인 경우 아래로 1칸
            // i = 2 인 경우 왼쪽 1칸
            // i = 3 인 경우 오른쪽 1칸
            for (int i = 0; i < 4; i++) {
                int nr = position[0] + dr[i];
                int nc = position[1] + dc[i];

                if (nr < 0 || nc < 0 || nr >= row || nc >= col || (nr == r && nc == c))
                    continue;

                int d = Math.abs(nr - r) + Math.abs(nc - c);

                if (p[nr].charAt(nc) == 'P' && d <= 2) {
                    if (i == 0) {
                        ptarr[nr + 1][nc] = 1;
                    }
                    else if (i == 1) {
                        ptarr[nr - 1][nc] = 1;
                    }
                    else if (i == 2) {
                        ptarr[nr][nc + 1] = 1;
                    }
                    else if (i == 3) {
                        ptarr[nr][nc - 1] = 1;
                    }
                    // return false;
                }
                else if (p[nr].charAt(nc) == 'O' && d < 2)
                    queue.offer(new int[] { nr, nc });
            }
        }

        return true;
    }

}