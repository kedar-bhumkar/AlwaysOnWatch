package healthcloud_alpha.persistent.com.healthcloud_alpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private CheckBox box;
    private Activity theActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OnCreate...", "...");
        super.onCreate(savedInstanceState);
        theActivity = this;
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        Log.d("WatchViewStub...", stub.toString());
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                Log.d("CheckBox....", "....");
                //mTextView = (TextView) stub.findViewById(R.id.text);
                //Log.d("mTextView ", mTextView.toString());
                box = (CheckBox) stub.findViewById(R.id.checkBox);
                addBoxListener(box);
                Log.d("CheckBox ", box.toString());
            }
        });



    }

    private void addBoxListener(CheckBox box){
        box.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("On btn clicked", "");
                if(((CheckBox)v).isChecked()){
                    Log.d("Checkbox ", "checked");
                    Intent intent = new Intent(theActivity, AlwaysOnService.class);
                    intent.putExtra("AlwaysOn", true);
                    startService(intent);
                }else{
                    Log.d("Checkbox ", "unchecked");
                }
            }
        });
    }
}
