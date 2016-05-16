package com.sjproject.webchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView m_ListView;
    CustomAdapter m_Adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 커스텀 어댑터 생성
        m_Adapter = new CustomAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listView1);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        m_Adapter.add("안드로이드",1);
        m_Adapter.add("채팅",1);
        m_Adapter.add("테스트",0);
        m_Adapter.add("딩동댕",1);
        m_Adapter.add("2016-05-16",2);
        m_Adapter.add("리사이클뷰",0);;


        findViewById(R.id.button1)
                .setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = (EditText) findViewById(R.id.editText1) ;
                        String inputValue = editText.getText().toString() ;
                        editText.setText("");
                        refresh(inputValue,0);
                    }
                }
        );


        findViewById(R.id.button2)
                .setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = (EditText) findViewById(R.id.editText1) ;
                        String inputValue = editText.getText().toString() ;
                        editText.setText("");
                        refresh(inputValue,1);
                    }
                }
        );

    }

    private void refresh (String inputValue, int _str) {
        m_Adapter.add(inputValue,_str) ;
        m_Adapter.notifyDataSetChanged();
    }
}
