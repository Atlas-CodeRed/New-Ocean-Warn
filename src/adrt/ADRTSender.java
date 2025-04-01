package adrt;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

public class ADRTSender {
    private static Context context;
    private static String debuggerPackageName;

    public static void onContext(Context context, String str) {
        debuggerPackageName = str;
    }

    private static Intent createIntent(String str) {
        Intent intent = new Intent();
        intent.setPackage(debuggerPackageName);
        intent.setAction(str);
        return intent;
    }

    public static void sendConnect(String str) {
        Intent createIntent = createIntent("com.adrt.CONNECT");
        createIntent.putExtra("package", str);
        context.sendBroadcast(createIntent);
    }

    public static void sendStop(String str) {
        Intent createIntent = createIntent("com.adrt.STOP");
        createIntent.putExtra("package", str);
        context.sendBroadcast(createIntent);
    }

    public static void sendBreakpointHit(String str, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3, ArrayList<String> arrayList4, ArrayList<String> arrayList5, ArrayList<String> arrayList6) {
        Intent createIntent = createIntent("com.adrt.BREAKPOINT_HIT");
        createIntent.putExtra("package", str);
        createIntent.putExtra("variables", arrayList);
        createIntent.putExtra("variableValues", arrayList2);
        createIntent.putExtra("variableKinds", arrayList3);
        createIntent.putExtra("stackMethods", arrayList4);
        createIntent.putExtra("stackLocations", arrayList5);
        createIntent.putExtra("stackLocationKinds", arrayList6);
        context.sendBroadcast(createIntent);
    }

    public static void sendFields(String str, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        Intent createIntent = createIntent("com.adrt.FIELDS");
        createIntent.putExtra("package", str);
        createIntent.putExtra("path", str2);
        createIntent.putExtra("fields", arrayList);
        createIntent.putExtra("fieldValues", arrayList2);
        createIntent.putExtra("fieldKinds", arrayList3);
        context.sendBroadcast(createIntent);
    }

    public static void sendLogcatLines(String[] strArr) {
        Intent createIntent = createIntent("com.adrt.LOGCAT_ENTRIES");
        createIntent.putExtra("lines", strArr);
        context.sendBroadcast(createIntent);
    }
}
