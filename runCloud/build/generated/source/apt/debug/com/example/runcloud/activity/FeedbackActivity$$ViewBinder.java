// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FeedbackActivity$$ViewBinder<T extends com.example.runcloud.activity.FeedbackActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493035, "field 'et_feedback'");
    target.et_feedback = finder.castView(view, 2131493035, "field 'et_feedback'");
    view = finder.findRequiredView(source, 2131493036, "field 'btn_feedback'");
    target.btn_feedback = finder.castView(view, 2131493036, "field 'btn_feedback'");
  }

  @Override public void unbind(T target) {
    target.et_feedback = null;
    target.btn_feedback = null;
  }
}
