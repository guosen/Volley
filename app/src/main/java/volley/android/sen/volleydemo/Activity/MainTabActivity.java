package volley.android.sen.volleydemo.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import util.ExampleUtil;
import volley.android.sen.volleydemo.R;
import volley.android.sen.volleydemo.adapter.TabAdapter;
import volley.android.sen.volleydemo.wdget.CustomViewPage;

/**
 * Created by shouwang on 16/4/12.
 */
public class MainTabActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    @Bind(R.id.vPage)
    CustomViewPage vPage;
    @Bind(R.id.bottom)
    RadioGroup     bottom;
    private TabAdapter mTabAdapter;
    public static boolean isForeground = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab);
        ButterKnife.bind(this);
        registerMessageReceiver();
        mTabAdapter=new TabAdapter(getSupportFragmentManager());
        vPage.setAdapter(mTabAdapter);
        bottom.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.rbHome:
                vPage.setCurrentItem(0);
            break;
            case R.id.rbMe:
                vPage.setCurrentItem(3);
                break;
            case R.id.rbMessge:
                vPage.setCurrentItem(2);
                break;
            case R.id.rbDisc:
                vPage.setCurrentItem(1);
                break;
        }
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }

    private void setCostomMsg(String msg){
//        if (null != msgText) {
//            msgText.setText(msg);
//            msgText.setVisibility(android.view.View.VISIBLE);
//        }

        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
    }
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        isForeground=true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground=false;
        super.onPause();
    }
}
