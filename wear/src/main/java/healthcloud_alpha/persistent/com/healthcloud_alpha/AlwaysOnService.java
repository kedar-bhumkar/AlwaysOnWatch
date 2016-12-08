package healthcloud_alpha.persistent.com.healthcloud_alpha;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class AlwaysOnService extends IntentService {

    public AlwaysOnService() {
        super("AlwaysOnService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Inside ", "AlwaysOnService");
        if (intent != null) {
                boolean isAlwaysOn = intent.getBooleanExtra("AlwaysOn", true);
                if(isAlwaysOn){
                    Handler handler = new Handler(Looper.getMainLooper());

                    final DisplayManager displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
                    displayManager.registerDisplayListener(new DisplayManager.DisplayListener() {
                        @Override
                        public void onDisplayAdded(int displayId) {}

                        @Override
                        public void onDisplayRemoved(int displayId) {}

                        @Override
                        public void onDisplayChanged(int displayId) {
                            try {
                                if (displayManager.getDisplay(displayId).getState() == Display.STATE_DOZE) {
                                    //updateFaceDisplay(true);
                                    Log.d("display ", "onDisplayChanged: dozing");
                                } else {
                                    //updateFaceDisplay(false);
                                    Log.d("display ", "onDisplayChanged: not dozing");

                                }
                            } catch (NullPointerException exception) {
                            }
                        }
                    }, handler);
                }
        }
    }
}
