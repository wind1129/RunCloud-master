// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SettingActivity$$ViewBinder<T extends com.example.runcloud.activity.SettingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492974, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131492974, "field 'mToolbar'");
  }

  @Override public void unbind(T target) {
    target.mToolbar = null;
  }
}
