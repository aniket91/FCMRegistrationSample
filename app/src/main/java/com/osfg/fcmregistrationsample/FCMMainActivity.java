package com.osfg.fcmregistrationsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.osfg.utils.CommonUtils;

public class FCMMainActivity extends AppCompatActivity {

    private static final String loggerName = FCMMainActivity.class.getSimpleName();

    EditText fcmRegidView;
    Button refreshFcmRegIdButton;
    Button copyFcmRegIdButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmmain);

        fcmRegidView = (EditText) findViewById(R.id.fcmregIdText);
        refreshFcmRegIdButton = (Button) findViewById(R.id.fcmrefreshbutton);
        copyFcmRegIdButton = (Button) findViewById(R.id.fcmcopybutton);
        fcmRegidView.setKeyListener(null);

        refreshFcmRegIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fcmRegId = FirebaseInstanceId.getInstance().getToken();
                Log.d(loggerName, "FCM token refreshed: " + fcmRegId);
                if(fcmRegId != null) {
                    fcmRegidView.setText(fcmRegId);
                }else {
                    fcmRegidView.setText(getString(R.string.fcm_reg_in_progress));
                }

            }
        });

        copyFcmRegIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.setClipboard(FCMMainActivity.this, fcmRegidView.getText().toString());
            }
        });

        fcmRegidView.setText(getString(R.string.fcm_reg_in_progress));

    }
}
