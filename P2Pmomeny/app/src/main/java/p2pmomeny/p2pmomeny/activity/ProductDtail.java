package p2pmomeny.p2pmomeny.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import p2pmomeny.p2pmomeny.R;

/**
 * Created by Administrator on 2017-04-10.
 */
public class ProductDtail extends  BaseActivity {
   WebView  wv;
    private String url;

    @Override
    protected void initData() {
        wv.loadUrl(url);
        WebSettings settings = wv.getSettings();
        settings .setJavaScriptEnabled(true);//支持js
        settings.setUseWideViewPort(true);//支持双击缩放
// 设置可以支持缩放
        wv.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        wv.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        wv.getSettings().setUseWideViewPort(true);
//自适应屏幕
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv.getSettings().setLoadWithOverviewMode(true);
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
    protected void initTitle() {

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        wv= (WebView) findViewById(R.id.wv);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_productdtail;
    }
}
