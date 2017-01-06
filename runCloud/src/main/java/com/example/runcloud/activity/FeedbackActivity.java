package com.example.runcloud.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.HttpMessage;
import com.example.runcloud.extra.CustomDialog;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpClientTool;
import com.example.runcloud.util.HttpUtils;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wind1129 on 16/11/18.
 */
public class FeedbackActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.et_feedback)
    EditText et_feedback;
    @Bind(R.id.btn_feedback)
    Button btn_feedback;

    /**
     * 参数
     */
    private String userId;
    private String orgId;
    private String feedbackText;
    private HttpResponse httpResponse;

    private String uri;
    private String urlPart = "/ares/restful/feedback/feedback";
    private String finalURL;

    /** 本地存储 */
    private SharedPreferences sp;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);
        ButterKnife.bind(this);
        userId = ((MyApplication) getApplication()).getInformation().getUserId();
        orgId = ((MyApplication) getApplication()).getInformation().getOrgId();
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        uri = sp.getString("URI", "");
        finalURL = uri + urlPart;
        btn_feedback.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_feedback:
                if (!TextUtils.isEmpty(et_feedback.getText())) {
                    feedbackText = et_feedback.getText().toString();
                    new PostFeedbackTask().execute();
                }
                break;
        }
    }

    private class PostFeedbackTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... params) {
            JSONObject param = new JSONObject();
            try {
                param.put("userId", userId);
                param.put("orgId", orgId);
                param.put("content", feedbackText);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int code;
            HttpPost httpPost = new HttpPost(finalURL);
            StringEntity se;
            try {
                se = new StringEntity(param.toString(), "utf-8");
                httpPost.setEntity(se);
                // 发送请求
                httpResponse = HttpClientTool.getHttpClient().execute(httpPost);
                code = httpResponse.getStatusLine().getStatusCode();
                Log.d("+++code",code+"");
                return code;
            } catch (Exception e) {
                return 0;
            }
        }

        @Override
        protected void onPostExecute(Integer code) {
            if (code == 200) {
                String rev;
                try {
                    rev = EntityUtils.toString(httpResponse.getEntity());
                    Log.i("+++rev", rev);
                    HttpMessage httpMessage = HttpUtils.HttpMessageFromJson(rev);
                    Log.d("+++httpMessage", httpMessage.getResultCode());
                    if(httpMessage.getResultCode().equals("000000")){
                        Intent intent = new Intent(FeedbackActivity.this, FragmentMainActivity.class);
                        startActivity(intent);
                    }else{
                        FunctionUtils.showCustomDialog(httpMessage.getMessage(),
                                FeedbackActivity.this);
                        return;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                FunctionUtils.showCustomDialog("与服务器失去连接，\n请稍后再试",
                        FeedbackActivity.this);
            }
        }
    }
}
