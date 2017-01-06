// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ContentActivity$$ViewBinder<T extends com.example.runcloud.activity.ContentActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493108, "field 'detailImg'");
    target.detailImg = finder.castView(view, 2131493108, "field 'detailImg'");
    view = finder.findRequiredView(source, 2131493107, "field 'toolbarLayout'");
    target.toolbarLayout = finder.castView(view, 2131493107, "field 'toolbarLayout'");
    view = finder.findRequiredView(source, 2131493111, "field 'fab'");
    target.fab = finder.castView(view, 2131493111, "field 'fab'");
    view = finder.findRequiredView(source, 2131493112, "field 'progress'");
    target.progress = finder.castView(view, 2131493112, "field 'progress'");
    view = finder.findRequiredView(source, 2131493018, "field 'webContainer'");
    target.webContainer = finder.castView(view, 2131493018, "field 'webContainer'");
    view = finder.findRequiredView(source, 2131493016, "field 'content_linkto'");
    target.content_linkto = view;
  }

  @Override public void unbind(T target) {
    target.detailImg = null;
    target.toolbarLayout = null;
    target.fab = null;
    target.progress = null;
    target.webContainer = null;
    target.content_linkto = null;
  }
}
