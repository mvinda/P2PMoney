package p2pmomeny.p2pmomeny.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.activity.ProductDtail;
import p2pmomeny.p2pmomeny.bean.Product;
import p2pmomeny.p2pmomeny.common.AppNetConfig;
import p2pmomeny.p2pmomeny.common.UIUtils;
import p2pmomeny.p2pmomeny.ui.RoundProgress;

/**
 * Created by Administrator on 2017-04-09.
 */
public class ProductListFragment extends Fragment {
    private static final String TAG = "ProductListFragment";
    AsyncHttpClient client=new AsyncHttpClient();
    private View fragement_product_list;
    ListView listView;
    private List<Product> products;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragement_product_list= UIUtils.getXmlView(R.layout.fragement_product_list);
        listView = (ListView) fragement_product_list.findViewById(R.id.lv);
        initData();
        initTitle();
        return fragement_product_list;
    }
    private void initTitle() {
    }
    private void initData() {
        client.post(AppNetConfig.PRODUCT,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                JSONObject jsonObject= JSON.parseObject(content);
                if (jsonObject.getBoolean("success")){
                    String data=jsonObject.getString("data");
                    products = JSON.parseArray(data, Product.class);
                    for (int i = 0; i < products.size(); i++) {
                        Product product = products.get(i);
                        Log.i(TAG, "onSuccess: minTouMoney"+product.minTouMoney+"money"+product.money+"name"+product.name+"suodingDays"+product.suodingDays+"yearLy"+product.yearLy+"progress"+product.progress);
                    }
                    MyAdapter myAdapter = new MyAdapter(products);
                    listView.setAdapter(myAdapter);
                }
            }
            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
        private  List<Product> products;
        public MyAdapter(List<Product> products) {
            this.products=products;
        }
        @Override
        public int getCount() {
            return products==null? 0:products.size();
        }
        @Override
        public Object getItem(int position) {
            return products.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            final Product product = products.get(position);
            if (convertView==null){
                convertView= View.inflate(getContext(),R.layout.item_product_list,null);
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.ll_on.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ProductDtail.class);
                    intent.putExtra("url",product.url);
                    startActivity(intent);
                }
            });
            viewHolder.p_minzouzi.setText(product.minTouMoney);
            viewHolder.p_money.setText(product.money);
            viewHolder.p_name.setText(product.name);
            viewHolder.p_suodingdays.setText(product.suodingDays);
            viewHolder.p_yearlv.setText(product.yearLy);
            viewHolder.p_progresss.setProgress(Integer.parseInt(product.progress));
            return convertView;
        }
    }

    class  ViewHolder{
        LinearLayout ll_on;
        TextView p_name;
        TextView p_money;
        TextView p_yearlv;
        TextView p_suodingdays;
        TextView p_minzouzi;
        RoundProgress p_progresss;
        ViewHolder(View convertView){
            p_name= (TextView) convertView.findViewById(R.id.p_name);
            p_money=(TextView) convertView.findViewById(R.id.p_money);
            p_yearlv= (TextView) convertView.findViewById(R.id.p_yearlv);
            p_suodingdays=(TextView) convertView.findViewById(R.id.p_suodingdays);
            p_minzouzi= (TextView) convertView.findViewById(R.id.p_minzouzi);
            p_progresss=(RoundProgress) convertView.findViewById(R.id.p_progresss);
            ll_on= (LinearLayout) convertView.findViewById(R.id.ll_on);
        }
    }



    private class MyAdapter2 extends BaseAdapter {
        private  List<Product> products;


        public MyAdapter2(List<Product> products) {
            this.products=products;
        }


        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position==0){

                return 1;
            }else{
                return 2;

            }
        }

        @Override
        public int getCount() {
            return products==null? 0:products.size();
        }
        @Override
        public Object getItem(int position) {
            return products.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position)==1){
                TextView textView=new TextView(getActivity());
                textView.setText("第一个");
                textView.setTextSize(50);
                textView.setTextColor(Color.RED);
                return  textView;
            }
            ViewHolder viewHolder = null;
            Product product = products.get(position);
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_product_list, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.p_minzouzi.setText(product.minTouMoney);
            viewHolder.p_money.setText(product.money);
            viewHolder.p_name.setText(product.name);
            viewHolder.p_suodingdays.setText(product.suodingDays);
            viewHolder.p_yearlv.setText(product.yearLy);
            viewHolder.p_progresss.setProgress(Integer.parseInt(product.progress));
            return convertView;

        }
    }

}
