package volley.android.sen.volleydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import volley.android.sen.volleydemo.Fragment.MeFragment;
import volley.android.sen.volleydemo.Fragment.MessageFragment;

/**
 * Created by shouwang on 16/4/12.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private final int  TAB_HOME=0;
    private final int  TAB_DISCOVER=1;
    private final int  TAB_MESSAGE=2;
    private final int  TAB_ME=3;
    MeFragment      mMeFragment;
    MessageFragment mMessageFragment;
    public TabAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new Fragment();
        switch (position){
            case TAB_HOME:
                break;
            case TAB_ME:
                if(mMeFragment==null) {
                    mMeFragment = new MeFragment();
                }
                fragment=mMeFragment;
                break;
            case TAB_MESSAGE:
                if(mMessageFragment==null){
                    mMessageFragment=new MessageFragment();
                }
                fragment=mMessageFragment;
                break;
            case TAB_DISCOVER:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
