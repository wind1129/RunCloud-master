// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class QuicklySearchShow$$ViewBinder<T extends com.example.runcloud.activity.QuicklySearchShow> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493089, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131493089, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131493088, "field 'swipeRefresh'");
    target.swipeRefresh = finder.castView(view, 2131493088, "field 'swipeRefresh'");
  }

  @Override public void unbind(T target) {
    target.recyclerView = null;
    target.swipeRefresh = null;
  }
}
