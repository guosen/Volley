package volley.android.sen.volleydemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shouwang on 16/4/12.
 */
public class WebViewActivity extends Activity {
    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);
        ButterKnife.bind(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://nacola.m.tmall.com/shop/promotion.htm?promotion_id=5862719");
       //  webView.loadUrl("http://www.baidu.com");
    }
}
