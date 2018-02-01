package zzu.zhaoxuezhao.com.asynctaskdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView;
    DownTask downTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=findViewById(R.id.start_btn);
        mTextView=findViewById(R.id.response_text);

        downTask=new DownTask(this,mTextView);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    downTask.execute(new URL("http://www.freezxz.com/2018/01/09/android-handler/"));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
