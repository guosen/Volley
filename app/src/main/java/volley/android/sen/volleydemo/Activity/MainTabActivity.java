package volley.android.sen.volleydemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import volley.android.sen.volleydemo.R;
import volley.android.sen.volleydemo.adapter.TabAdapter;

/**
 * Created by shouwang on 16/4/12.
 */
public class MainTabActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    @Bind(R.id.vPage)
    ViewPager vPage;
    @Bind(R.id.bottom)
    RadioGroup bottom;
    private TabAdapter mTabAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab);
        ButterKnife.bind(this);
        mTabAdapter=new TabAdapter(getSupportFragmentManager());
        vPage.setAdapter(mTabAdapter);
        bottom.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.rbHome:
            break;
            case R.id.rbMe:
                vPage.setCurrentItem(3);
                break;
        }
    }
}
