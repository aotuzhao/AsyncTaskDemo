package zzu.zhaoxuezhao.com.asynctaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aotu on 2018/2/1.
 */

public class DownTask extends AsyncTask<URL, Integer, String> {
    ProgressDialog mProgressDialog;
    int hasRead = 0;
    Context mContext;
    TextView mTextView;

    public DownTask(Context context, TextView textView) {
        mContext = context;
        mTextView = textView;

    }

    @Override
    protected String doInBackground(URL... urls) {

        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            //发起网络请求
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream inputStream = connection.getInputStream();
            //对获取到的输入流进行读取
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
                hasRead++;
                publishProgress(hasRead);

            }
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTextView.setText(s);
        mProgressDialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //初始化提示框
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle("download");
        mProgressDialog.setMessage("Downloading, please wait a moment");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //更新进度
        mProgressDialog.setProgress(values[0]);
    }
}
