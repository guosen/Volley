package volley.android.sen.volleydemo.wdget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import volley.android.sen.volleydemo.R;

/**
 * Created by shouwang on 16/4/14.
 * 可添加头部和
 */
public class HeadFootRecycleView extends ObservableRecyclerView{
    private final String Tag="HeadFootRecycleView";
    private View mHead;
    private View mFoot;
    public HeadFootRecycleView(Context context) {
        super(context);
    }

    public HeadFootRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadFootRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
