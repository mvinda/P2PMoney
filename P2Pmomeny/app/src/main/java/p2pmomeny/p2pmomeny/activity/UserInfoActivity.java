package p2pmomeny.p2pmomeny.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import p2pmomeny.p2pmomeny.MainActivity;
import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.common.AppManager;

/**
 * Created by Administrator on 2017-04-10.
 */
public class UserInfoActivity extends  BaseActivity {
    ImageView imageView1;
    ImageView iv_setting;
    ImageView iv_back;
    TextView tv_title;
    Button loginOut;
    private static final int CAMERA = 100;

    private static final int PICTURE = 200;
    @Override
    protected void initData() {




    }

    @Override
    protected void initTitle() {
        iv_setting= (ImageView) findViewById(R.id.iv_setting);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText("用户信息");
        iv_back.setVisibility(View.VISIBLE);
        loginOut= (Button) findViewById(R.id.loginOut);

        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginOut();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        iv_setting.setVisibility(View.INVISIBLE);
        imageView1= (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"这个还没实现喔",Toast.LENGTH_SHORT).show();

                /*


                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("选择来源");
                builder.setItems(new String[]{"拍照", "图库"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //打开系统拍照程序，选择拍照图片
                                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(camera, CAMERA);
                                break;
                            case 1:
                                //打开系统图库程序，选择图片
                                Intent picture = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(picture, PICTURE);
                                break;
                        }
                    }
                });
                builder.create().show();
                 */
            }

        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = getCacheDir() + "/tx.png";
        Toast.makeText(getApplicationContext(),"还没实现",Toast.LENGTH_SHORT).show();


    }


    public void loginOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("退出登录");
        builder.setMessage("你确定吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getSharedPreferences("user_info", MODE_PRIVATE).edit().clear().commit();
                AppManager.getInstance().removeAll();
                gotoActivity(MainActivity.class, null);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
