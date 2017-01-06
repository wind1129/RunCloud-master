package com.example.runcloud.util;

import android.app.Activity;
import android.content.Intent;

import com.example.runcloud.activity.PushDetailActivity;
import com.example.runcloud.activity.StoryActivity;
import com.example.runcloud.entity.PushData;
import com.example.runcloud.entity.PushDetail;
import com.example.runcloud.mvp.utils.Constants;

/**
 * Created by hefuyi on 16/7/29.
 */
public class IntentUtils {
    public static final String EXTRA_STORY_ID = "extra_story_id";



    public static final void intentToStoryActivity(Activity activity, PushData story) {
        Intent intent = new Intent(activity, PushDetailActivity.class);
        intent.putExtra(Constants.ID, String.valueOf(story.getPushId()));
        activity.startActivity(intent);
    }


}
