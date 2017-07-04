package p2pmomeny.p2pmomeny.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.activity.LoginActivity;
import p2pmomeny.p2pmomeny.bean.Image;
import p2pmomeny.p2pmomeny.bean.Index;
import p2pmomeny.p2pmomeny.bean.Product;
import p2pmomeny.p2pmomeny.common.AppNetConfig;
import p2pmomeny.p2pmomeny.ui.RoundProgress;


/**
 * Created by Administrator on 2017-04-06.
 */
public class HomeFragment  extends Fragment{

    private static final String TAG = "HomeFragment";
    AsyncHttpClient client=new AsyncHttpClient();

    TextView tv_title;
    ImageView iv_back;
    ImageView iv_setting;
    private  int totalProgress=90;
    ViewPager  vp_barner;
    CirclePageIndicator circle_barner;
    TextView textView1;
    TextView p_yearlv;
    Button button1;
    private Index index;
    private  BitmapUtils bitmapUtils;
    RoundProgress p_progresss;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);

        initUI(view);
        initData();


        return view;

    }
    private void initData() {
        client.post(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.i(TAG, "onSuccess: " + content);
                JSONObject jsonObject = JSON.parseObject(content);
                String proInfo = jsonObject.getString("proInfo");
                Product product = JSON.parseObject(proInfo, Product.class);
                String imageArr = jsonObject.getString("imageArr");
                List<Image> imageList = JSON.parseArray(imageArr, Image.class);
                index = new Index();
                index.product=product;
                index.imageList=imageList;
                vp_barner.setAdapter(new MyAdapter());
                //把指示器關聯起來
                totalProgress = Integer.parseInt(index.product.progress);
                circle_barner.setViewPager(vp_barner);
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        int temProgress=0;
                        while (temProgress<totalProgress){

                            temProgress++;
                        p_progresss.setProgress(temProgress);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }

                    }
                    }
                }.start();



            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                Toast.makeText(getContext(),"服务器请求异常",Toast.LENGTH_SHORT).show();

            }
        });






    }

    private void initUI(View view) {
        p_progresss= (RoundProgress) view.findViewById(R.id.p_progresss);
        tv_title= (TextView) view.findViewById(R.id.tv_title);
        iv_back= (ImageView) view.findViewById(R.id.iv_back);
        iv_setting= (ImageView) view.findViewById(R.id.iv_setting);
        vp_barner= (ViewPager) view.findViewById(R.id.vp_barner);
        iv_setting.setVisibility(View.INVISIBLE);
        iv_back.setVisibility(View.INVISIBLE);
        circle_barner= (CirclePageIndicator) view.findViewById(R.id.circle_barner);
      textView1= (TextView) view.findViewById(R.id.textView1);
     p_yearlv= (TextView) view.findViewById(R.id.p_yearlv);
        button1= (Button) view.findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getActivity().getSharedPreferences("user_info", getActivity().MODE_PRIVATE);
                String name = sp.getString("UF_ACC", "");
                if (!TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(),"您已经登录过了",Toast.LENGTH_SHORT).show();



                }else{
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }

            }
        });

    }

    private class MyAdapter extends PagerAdapter {
        MyAdapter(){
            bitmapUtils = new BitmapUtils(getActivity());


        }
        @Override
        public int getCount() {

            if (index.imageList!=null){

                return index.imageList.size();
            }else {

                return 0;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            Log.i(TAG, "instantiateItem: "+index.imageList.get(position).IMAURL);

            bitmapUtils.display(imageView,index.imageList.get(position).IMAURL);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);


        }
    }
}
