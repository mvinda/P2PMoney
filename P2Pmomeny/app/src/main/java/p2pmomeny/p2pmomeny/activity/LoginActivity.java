package p2pmomeny.p2pmomeny.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import p2pmomeny.p2pmomeny.MainActivity;
import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.bean.Login;
import p2pmomeny.p2pmomeny.common.AppNetConfig;
import p2pmomeny.p2pmomeny.common.MD5Utils;

/**
 * Created by Administrator on 2017-04-10.
 */
public class LoginActivity extends BaseActivity {

    Button log_log_btn;
    ImageView iv_setting;
    ImageView iv_back;
    TextView tv_title;
    AsyncHttpClient client=new AsyncHttpClient();
    EditText  log_ed_mob,log_ed_pad;



    @Override
    protected void initData() {
        log_log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });




    }

    private void loginCheck() {
        String username=log_ed_mob.getText().toString();
        String password=log_ed_pad.getText().toString();
        if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
            RequestParams requestParams = new RequestParams();
            requestParams.put("username",username);
            requestParams.put("password", MD5Utils.MD5(password));
            client.post(AppNetConfig.LOGIN,requestParams,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    JSONObject jsonObject= JSON.parseObject(content);
                    if (jsonObject.getBoolean("success")){
                        String data=jsonObject.getString("data");
                        Login login=JSON.parseObject(data,Login.class);
                        SaveLogin(login);
                       gotoActivity(MainActivity.class, null);
                        finish();
                        //保存到Sp 中
                        //关闭当前

                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);

                }
            });
        }
    }

    @Override
    protected void initTitle() {


        iv_setting= (ImageView) findViewById(R.id.iv_setting);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_title= (TextView) findViewById(R.id.tv_title);
        iv_back.setVisibility(View.INVISIBLE);
        tv_title.setText("登录");
        iv_setting.setVisibility(View.INVISIBLE);
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        log_ed_mob= (EditText) findViewById(R.id.log_ed_mob);
        log_ed_pad= (EditText) findViewById(R.id.log_ed_pad);
        log_log_btn= (Button) findViewById(R.id.log_log_btn);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
