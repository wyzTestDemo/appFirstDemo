package gx.com.yyb;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import gx.com.home.HomeMainFragment;
import gx.com.musicplay.MusicPlayMainFragment;
import gx.com.userinfo.UserInfoMainFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private TextView tx_top;
    private RadioButton tx_channel;
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private Fragment[] fragments = new Fragment[4];
    private int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.tab_bar);
        radioGroup.setOnCheckedChangeListener(this);
        tx_channel = (RadioButton) findViewById(R.id.tab_bar_channel);
        tx_channel.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (i) {
            case R.id.tab_bar_channel:
                if (fragments[0] == null) {
                    fragments[0] = new HomeMainFragment();
                    fragmentTransaction.add(R.id.content, fragments[0]);
                }
                hideAndShow(0,fragmentTransaction);

                break;
            case R.id.tab_bar_profile:
                if (fragments[1] == null) {
                    fragments[1] = new MusicPlayMainFragment();
                    fragmentTransaction.add(R.id.content, fragments[1]);
                }
                hideAndShow(1,fragmentTransaction);
                break;
            case R.id.tab_bar_my:
                if (fragments[2] == null) {
                    fragments[2] = new UserInfoMainFragment();
                    fragmentTransaction.add(R.id.content, fragments[2]);
                }
                hideAndShow(2,fragmentTransaction);
                break;
        }

    }
    private void hideAndShow(int expectIndex,FragmentTransaction transition) {
        for (int i = 0; i < fragments.length; i++) {

            if (i != expectIndex && fragments[i] != null) {
                transition.hide(fragments[i]);
            }
        }
        transition.show(fragments[expectIndex]);
        transition.commit();
        currentIndex = expectIndex;
    }

}
