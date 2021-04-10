package com.codesample.recyclerview;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList; // 데이터 담긴거 리스트로 가져오려고 선언.

    // inflate = "부풀게 하다" xml에 표기된 내용들을 메모리에 객체화 시키는 행동.
    // 그래서 R.id.~~ / R.layout.~~ 해줘서 java클래스 파일에서 사용할 수 있게 함.
    // 그리고 공부하다보니 viewBinding을 고집할 필요 없음. 그닥 쓸모없는듯.

    // 뷰 홀더 = 각 뷰를 보관하는 holder 객체
    // 리사이클러뷰는 inflate를 최소화 하기 위해서 뷰를 재활용 하는데 뷰 업데이트 하기 위해서 findViewById를 매번 호출함.
    // 매번 호출하면 성능저하 일어남
    // 성능저하 줄이기 위해서 ViewHolder에 저장해서 액세스하며 사용함.
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected TextView english;
        protected TextView korean;

        // 오버라이드 해서 홀더(view)로 사용할수 있게 함.
        public CustomViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.id_listitem);
            this.english = (TextView) view.findViewById(R.id.english_listitem);
            this.korean = (TextView) view.findViewById(R.id.korean_listitem);
        }
    }

    // 메인에서 customAdapter(list)로 사용할 수 있게 오버라이드.
    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }

    // getItemCount 다음으로 실행되는 함수.
    // customViewHolder가 만들어졌을때 실행.
    // 10개의 객체를 사용하게 될것같으면 위아래 버퍼를 생각해 13~15개정도 뷰 객체를 생성해준다.
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // LayoutInflater.from 이란?
        // 이 방법은 가장 자주 사용하는 방법으로, LayoutInflater에 static으로 정의되어있는 LayoutInflater.from을 통해
        // LayoutInflater를 만드는 방법입니다. 내부적으로 context#getSystemService를 호출 하고 있으며,
        // 같은 context에서는 같은 객체를 리턴하기 때문에 굳이 멤버 변수로 선언해 놓지 않고 필요할 때마다 호출해서 사용해도 괜찮습니다.
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        // viewHolder에는 현재 item_list(view) 레이아웃의 각 객체들이 저장됨.
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    // 생성된 뷰홀더에 데이터를 바인딩(묶어줌)해줌.
    // ex. 데이터가 스크롤되서 맨 위에있던 뷰 홀더(레이아웃) 객체가 맨 아래로 이동되면, 레이아웃을 재사용하지만
    // 데이터는 바뀜. 인덱스 값이 (int) position이라는 이름으로 사용됨.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        // 글자 사이즈 지정
        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        // 글자 중앙 정렬
        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.english.setGravity(Gravity.CENTER);
        viewholder.korean.setGravity(Gravity.CENTER);

        // 저장된 값 꺼내서 setText해줌.
        viewholder.id.setText(mList.get(position).getId());
        viewholder.english.setText(mList.get(position).getEnglish());
        viewholder.korean.setText(mList.get(position).getKorean());
    }

    // getItemCount가 가장 먼저 실행되는 함수다.
    // 사용될 데이터의 길이를 책정하는 곳.
    @Override
    public int getItemCount() {

        // 삼항 연산자 A == B ? C : D
        // A가 B면 C / A가 B가 아니면 D
        // mList 가 널이 아니면 mList.size()를 리턴
        // mList 가 널이면 0을 리턴
        // mList가 추가될때 마다 사용되는 데이터 길이를 늘려주고 없을때는 길이가 0
        return (mList != null ? mList.size() : 0);
    }

}