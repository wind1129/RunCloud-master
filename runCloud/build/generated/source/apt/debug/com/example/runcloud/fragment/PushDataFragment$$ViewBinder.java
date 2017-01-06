// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PushDataFragment$$ViewBinder<T extends com.example.runcloud.fragment.PushDataFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493088, "field 'swipeRefreshLayout'");
    target.swipeRefreshLayout = finder.castView(view, 2131493088, "field 'swipeRefreshLayout'");
    view = finder.findRequiredView(source, 2131493155, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131493155, "field 'recyclerView'");
  }

  @Override public void unbind(T target) {
    target.swipeRefreshLayout = null;
    target.recyclerView = null;
  }
}
