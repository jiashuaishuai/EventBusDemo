package com.example.jiashuaishuai.myapplicationeventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends Activity {
        private Button btn_try;
        private TextView tv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            EventBus.getDefault().register(this);
            btn_try = (Button) findViewById(R.id.btn_try);
            tv = (TextView) findViewById(R.id.tv);
            btn_try.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), Main2Activity.class);
                    startActivity(intent);
                }
            });
        }

    /**
     * 1、onEvent
     2、onEventMainThread
     3、onEventBackgroundThread
     4、onEventAsync

     这四种订阅函数都是使用onEvent开头的，它们的功能稍有不同,在介绍不同之前先介绍两个概念：
     告知观察者事件发生时通过EventBus.post函数实现，这个过程叫做事件的发布，观察者被告知事件发生叫做事件的接收，是通过下面的订阅函数实现的。

     onEvent:如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。
     onEventMainThread:如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，这个在Android中是非常有用的，因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
     onEventBackground:如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，那么onEventBackground函数直接在该子线程中执行。
     onEventAsync：使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.
     * @param event
     *
     *
     * 当发过来一个消息的时候，EventBus怎么知道要调哪个函数呢，就看哪个函数传进去的参数是这个类的实例，哪个是就调哪个。那如果有两个是呢，那两个都会被调用！！！！
     */
    @Subscribe
    public void onEventMainThread(AnyEventType event) {

        String msg = "onEventMainThread收到了消息：" + event.getmMsg();
        Log.d("harvic", msg);
        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

}
