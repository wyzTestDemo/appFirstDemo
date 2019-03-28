package gx.com.musicplay;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import gx.com.common.MyToolBar.EnjoyshopToolBar;
import gx.com.common.Utils.CJS.CJSUtil;
import gx.com.common.Utils.QR.QrCodeUtil;

/**
 * Created by Asus on 2019/3/15.
 */

public class MusicPlayMainFragment extends Fragment implements View.OnClickListener {
    private EditText QRT;
    private ImageView QRI;
    private Button btn;
    private EnjoyshopToolBar mToolBar;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.musicplay_fragment_main, container, false);
        mToolBar = inflate.findViewById(R.id.toolbar);
        mToolBar.setTitle("音乐");
        QRI = (ImageView) inflate.findViewById(R.id.QRI);
        QRT = (EditText) inflate.findViewById(R.id.QRT);
        btn = (Button) inflate.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        CJSUtil.setStatusBar(getActivity());
        return inflate;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn) {
            String content = QRT.getText().toString().trim();
            if (content.equals("")) {
                Toast.makeText(getActivity(), "请先输入需要生成二维码的内容", Toast.LENGTH_SHORT).show();
                return;
            }
            Bitmap bitmap = QrCodeUtil.createQRImage(content, QRI.getWidth(), QRI.getHeight());
            QRI.setImageBitmap(bitmap);

        }
    }
}
