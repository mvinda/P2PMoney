package p2pmomeny.p2pmomeny.fragment;


import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.common.BaseFragment;

/**
 * Created by Administrator on 2017-04-06.
 */
public class MoreFragment extends BaseFragment{
    WebView wv;
    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.more_fragemnt;
    }

    @Override
    public void initData(String data) {
        wv.loadUrl("https://bbs.ppmoney.com/portal.php");
        WebSettings settings = wv.getSettings();
        settings .setJavaScriptEnabled(true);//支持js
        settings.setUseWideViewPort(true);//支持双击缩放

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            //网页加载开始的方法
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            //网页加载结束的时候调用
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }
    @Override
    public void initTitle(View successView) {
        ImageView iv_back = (ImageView) successView.findViewById(R.id.iv_back);
        ImageView iv_setting = (ImageView) successView.findViewById(R.id.iv_setting);
        TextView tv_title = (TextView) successView.findViewById(R.id.tv_title);
        tv_title.setText("更多");
        iv_back.setVisibility(View.INVISIBLE);
        iv_setting.setVisibility(View.INVISIBLE);
        wv= (WebView) successView.findViewById(R.id.wv);
    }
}


