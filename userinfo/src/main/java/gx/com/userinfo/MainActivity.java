package gx.com.userinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_activity_main);

    }

    @Override
    public void onClick(View view) {
        /*startActivity(new Intent(getContext(), SearchActivity.class));*/
        Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_LONG).show();
    }
}
