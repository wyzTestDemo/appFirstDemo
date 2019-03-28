package gx.com.home;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import gx.com.common.MyQR.CaptureActivity;
import gx.com.common.MyToolBar.EnjoyshopToolBar;
import gx.com.common.OpenActivity.EventUtil;
import gx.com.common.Utils.CJS.CJSUtil;
import gx.com.common.Utils.MyHttp.HttpContants;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMainFragment extends Fragment{
    private EnjoyshopToolBar mToolBar;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private TextView textView;
    public HomeMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_main, container, false);
        mToolBar = (EnjoyshopToolBar) view.findViewById(R.id.toolbar);
        mToolBar.setWeakButtonIcon(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    /**
                     * 跳转到扫码界面扫码
                     */
                    Intent intent  = new Intent(getActivity(),CaptureActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_SCAN);
                }
            }
        });
        textView = (TextView) view.findViewById(R.id.QRNR);
        CJSUtil.setStatusBar(getActivity());
        xlmb();
       /* requestData();*/
    /* 图片切换效果
      Animation animation=new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(3000);
        myButton.startAnimation(animation);
  animation01.setDuration(1200);                   //持续时间
        animation01.setStartOffset(3600);                //多久后开始
        animation01.setRepeatMode(Animation.REVERSE);    //重复模式
animation.setFillAfter(<span style="color:#000080;"><strong>true</strong></span>); //动画结束保持状态
 animation01.setAnimationListener(new Animation.AnimationListener() {
 开始
            @Override
            public void onAnimationStart(Animation animation) {
                jiantou01.setAlpha(0f);
            }
            结束
            @Override
            public void onAnimationEnd(Animation animation) {
                jiantou01.setAlpha(1f);
            }
重复
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        */
        return view;
    }
    /*下拉面板通知*/
    public void  xlmb(){
        NotificationManager notifyManager = (NotificationManager)(getActivity().getSystemService(Context.NOTIFICATION_SERVICE));
        Notification.Builder builder=new Notification.Builder(getActivity());
        /*new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jianshu.com/p/82e249713f1b"));*/
        Intent intent=new Intent(getActivity(),MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(getActivity(),0,intent,0);


        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
        notifyManager.notify(1, builder.build());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                textView.setText(content);

            }
        }
    }
    private void  requestData(){
               /* .addParams("type", "1")*/
        OkHttpUtils.get().url(HttpContants.BASE_URL+HttpContants.TestAppUrl)
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("wyz",e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                textView.setText(response);
            }
        });
    }
}
