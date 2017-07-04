package p2pmomeny.p2pmomeny.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import p2pmomeny.p2pmomeny.bean.Login;
import p2pmomeny.p2pmomeny.common.AppManager;

/**
 * Created by Administrator on 2017-04-10.
 */
public abstract class BaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();
    }





    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();


    public void SaveLogin(Login login){
        SharedPreferences sp=getSharedPreferences("user_info",MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("UF_ACC",login.UF_ACC);
        edit.putString("UF_AVATAR_URL",login.UF_AVATAR_URL);

        edit.putString("UF_IS_CERT",login.UF_IS_CERT);
        edit.putString("UF_PHONE",login.UF_PHONE);
        edit.commit();
    }



    public Login getLogin(){
        Login login=new Login();
        SharedPreferences sp=getSharedPreferences("user_info",MODE_PRIVATE);
        login.UF_ACC=sp.getString("UF_ACC","");
        login.UF_AVATAR_URL=sp.getString("UF_AVATAR_URL","");
        login.UF_IS_CERT=sp.getString("UF_IS_CERT","");
        login.UF_PHONE=sp.getString("UF_PHONE","");
        return login;
    }
    public void gotoActivity(Class clazz,Bundle bundle){
        Intent intent = new Intent(this,clazz);
        if (bundle!=null){
            intent.putExtra("param",bundle);
        }
             startActivity(intent);

    }



}
