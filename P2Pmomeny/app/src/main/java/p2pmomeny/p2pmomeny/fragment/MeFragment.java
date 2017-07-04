package p2pmomeny.p2pmomeny.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.http.RequestParams;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.activity.ChongZhiActivity;
import p2pmomeny.p2pmomeny.activity.LoginActivity;
import p2pmomeny.p2pmomeny.activity.TiXianActivity;
import p2pmomeny.p2pmomeny.activity.TouZhiManager;
import p2pmomeny.p2pmomeny.activity.UserInfoActivity;
import p2pmomeny.p2pmomeny.activity.ZiChangActivity;
import p2pmomeny.p2pmomeny.bean.Login;
import p2pmomeny.p2pmomeny.common.BaseFragment;
/**
 * Created by Administrator on 2017-04-06.
 */
public class MeFragment extends BaseFragment{
    private static final String TAG = "MeFragment";
    private BitmapUtils bitmapUtils;;
    private ImageView imageView1;
    private TextView textView11;
    private ImageView chongzhi;
    private ImageView tixian;
    private  TextView ll_touzi,ll_zichang,ll_anquan;
    ToggleButton tb;
    private TextView tv_money;

    //
    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }
    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    //数据网络成功的时候调用
    @Override
    public void initData(String data) {
      islogin();
    }

    private void islogin() {
        SharedPreferences sp=getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String uf_acc=sp.getString("UF_ACC","");
        if (TextUtils.isEmpty(uf_acc)){
            //未登录
            showLoginDialog();

        }else{
            //已经登录
            //初始化个人信息
            doUser();
        }
    }
    private void doUser() {
        bitmapUtils=new BitmapUtils(getActivity());
        Login login=new Login();
        SharedPreferences sp=getActivity().getSharedPreferences("user_info", getActivity().MODE_PRIVATE);
        login.UF_ACC=sp.getString("UF_ACC","");
        login.UF_AVATAR_URL=sp.getString("UF_AVATAR_URL","");
        login.UF_IS_CERT=sp.getString("UF_IS_CERT","");
        login.UF_PHONE=sp.getString("UF_PHONE","");
        textView11.setText(login.UF_ACC);
        Log.i(TAG, "doUser: "+login.UF_AVATAR_URL);
        bitmapUtils.display(imageView1,login.UF_AVATAR_URL);
    }

    private void showLoginDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录");
        builder.setMessage("必须先登录----go");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    //网络访问成功的时候初始化标题的时候调用
    @Override
    public void initTitle(View successView) {
        tv_money = (TextView) successView.findViewById(R.id.tv_money);
        ImageView iv_back = (ImageView) successView.findViewById(R.id.iv_back);
        ImageView iv_setting = (ImageView) successView.findViewById(R.id.iv_setting);
        TextView tv_title = (TextView) successView.findViewById(R.id.tv_title);
        tv_title.setText("我的资产");

        iv_back.setVisibility(View.INVISIBLE);
        iv_setting.setVisibility(View.VISIBLE);
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                startActivity(intent);
            }
        });
        imageView1 = (ImageView) successView.findViewById(R.id.imageView1);
        textView11 = (TextView) successView.findViewById(R.id.textView11);
        chongzhi = (ImageView) successView.findViewById(R.id.chongzhi);
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChongZhiActivity.class);
                startActivityForResult(intent, 1);

            }
        });
        tixian = (ImageView) successView.findViewById(R.id.tixian);
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TiXianActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        ll_touzi= (TextView) successView.findViewById(R.id.ll_touzi);
        ll_touzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), TouZhiManager.class);
                startActivity(intent);

            }
        });

        ll_zichang= (TextView) successView.findViewById(R.id.ll_zichang);
        ll_zichang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ZiChangActivity.class);
                startActivity(intent);
            }
        });

        ll_anquan= (TextView) successView.findViewById(R.id.ll_anquan);
        tb= (ToggleButton) successView.findViewById(R.id.tb);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getContext(),""+isChecked,Toast.LENGTH_SHORT).show();
                SharedPreferences sp=getContext().getSharedPreferences("is_check", getContext().MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("IS_CHECK", isChecked);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==3){
            String s = tv_money.getText().toString();
            Log.i(TAG, "onActivityResult: "+s);
            int older_money= Integer.parseInt(s);
            String money=data.getStringExtra("money");
            Log.i(TAG, "onActivityResult: "+money);

            int money_new = Integer.parseInt(money);
            int tv=older_money+money_new;
            tv_money.setText(tv+"");
        }else if (requestCode==2&&resultCode==4){
            String s = tv_money.getText().toString();
            int older_money= Integer.parseInt(s);
            String money=data.getStringExtra("money");
            int money_new = Integer.parseInt(money);
            int tv=older_money-money_new;
            Log.i(TAG, "onActivityResult: "+older_money+"---"+s+"---"+money+"--"+money_new);
            tv_money.setText(tv+"");
        }



    }
}
