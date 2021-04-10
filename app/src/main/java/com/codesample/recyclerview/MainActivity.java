package com.codesample.recyclerview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;


// 리사이클러 뷰 와 리스트 뷰
// 리스트 뷰는 모든 데이터를 다 꺼내서 일부분을 보여주는 방식이고
// 리사이클러 뷰는 필요한 데이터를 꺼내서 보여줌.
// 실제 채팅방에서 보여지고있는 데이터는 10개정도인데 리스트뷰로 사용하면 100개의 데이터가 들어가 새로 생성되고, 삭제되고를 반복함.
// 리사이클러 뷰는 원하는 위치에 안보이는 곳의 뷰를 재사용해서 보여줌.
// 결론 100개의 채팅데이터에서 리스트뷰 = 100개중 10개만 보이게 새로 생성 및 삭제를 진행
// 리사이클러뷰 = 단 10개의 객체만 사용해서 안보이는쪽 객체를 위로 올려 재사용함.
// 이중 10개를 기억하고 있을 객체가 필요함. 그것이 viewHolder
public class MainActivity extends AppCompatActivity {

    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main의 recyclerview_main_list 객체를 선언
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        // 리니어레이아웃 매니저 선언
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        // 리사이클러뷰 객체를 리니어 레이아웃으로 만들어줌.
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // arraylist 재선언 위에 ArrayList<Dictionary>로 선언해서 타입스킵함.
        mArrayList = new ArrayList<>();

        // CustomAdapter의 arrayList와 일치시켜줌.
        mAdapter = new CustomAdapter(mArrayList);
        // 메인의 리사이클러뷰에 CustomAdapter 연결
        mRecyclerView.setAdapter(mAdapter);

        // 꾸며주는 애, 구분선 지어주려고 만들어놓은듯
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        // 리사이클러뷰에 dividerItemDecoration 설정한거 넣어줌.
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        // 버튼 선언
        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                Dictionary data = new Dictionary(count+"","Apple" + count, "사과" + count);

                //mArrayList.add(0, dict);
                // RecyclerView의 첫 줄에 삽입
                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

                // 새로고침 기능 (아마 customAdapter가 재실행되는 애)
                mAdapter.notifyDataSetChanged();
            }
        });

    }

}