// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StoryHeaderView$$ViewBinder<T extends com.example.runcloud.view.StoryHeaderView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493203, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131493203, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131493204, "field 'tvAuthor'");
    target.tvAuthor = finder.castView(view, 2131493204, "field 'tvAuthor'");
    view = finder.findRequiredView(source, 2131493202, "field 'IvImage'");
    target.IvImage = finder.castView(view, 2131493202, "field 'IvImage'");
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
    target.tvAuthor = null;
    target.IvImage = null;
  }
}
