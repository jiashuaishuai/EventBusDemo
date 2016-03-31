package com.example.jiashuaishuai.myapplicationeventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class Main2Activity extends Activity {
    private Button btn_first_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final AnyEventType anyEventType = new AnyEventType();
        anyEventType.setmMsg("换种方式行不行");
        btn_first_event = (Button) findViewById(R.id.btn_first_event);
        btn_first_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(anyEventType);
            }
        });
    }

}
