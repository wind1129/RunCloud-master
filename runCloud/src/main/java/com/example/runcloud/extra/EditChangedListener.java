package com.example.runcloud.extra;

import com.example.runcloud.activity.AddAttention;
import com.example.runcloud.activity.PutAttention;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.util.FunctionUtils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**监控EditText*/
public class EditChangedListener implements TextWatcher {  
       private CharSequence temp;//监听前的文本  
       private int editStart;//光标开始位置  
       private int editEnd;//光标结束位置  
       private final int charMaxNum = 10;
       private EditText text;
       private String str;
       private String urlRegx;
       private String tagShow;
       
       
  
       public EditChangedListener(EditText text, String urlRegx, String tagShow) {
		super();
		this.text = text;
		this.urlRegx = urlRegx;
		this.tagShow = tagShow;
	}

	@Override  
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
           temp = s;  
       }  
  
       @Override  
       public void onTextChanged(CharSequence s, int start, int before, int count) {  
          str = text.getText().toString();
          if (TextUtils.isEmpty(text.getText())) {
        	  return;
          }
          if(!FunctionUtils.matchs(str, urlRegx)){
        	  if(tagShow.equals("AddAttention")){
        	  FunctionUtils.showCustomDialog("不能输入非法字符，\n请重新输入",
						AddAttention.addAttention);
        	  }else if(tagShow.equals("PutAttention")){
        		  FunctionUtils.showCustomDialog("不能输入非法字符，\n请重新输入",
  						PutAttention.putAttention);
        		  
        	  }
          };
  
       }  
  
       @Override  
       public void afterTextChanged(Editable s) {  
          
  
       }  
   };  
