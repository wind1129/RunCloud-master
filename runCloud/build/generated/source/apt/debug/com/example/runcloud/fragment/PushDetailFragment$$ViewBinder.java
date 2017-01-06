// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PushDetailFragment$$ViewBinder<T extends com.example.runcloud.fragment.PushDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493053, "field 'progressBar'");
    target.progressBar = finder.castView(view, 2131493053, "field 'progressBar'");
    view = finder.findRequiredView(source, 2131493050, "field 'rlStoryHeader'");
    target.rlStoryHeader = finder.castView(view, 2131493050, "field 'rlStoryHeader'");
    view = finder.findRequiredView(source, 2131492942, "field 'scrollView'");
    target.scrollView = finder.castView(view, 2131492942, "field 'scrollView'");
    view = finder.findRequiredView(source, 2131493051, "field 'llWebViewContainer'");
    target.llWebViewContainer = finder.castView(view, 2131493051, "field 'llWebViewContainer'");
    view = finder.findRequiredView(source, 2131493052, "field 'spaceView'");
    target.spaceView = view;
    view = finder.findRequiredView(source, 2131493016, "field 'content_linkto'");
    target.content_linkto = view;
  }

  @Override public void unbind(T target) {
    target.progressBar = null;
    target.rlStoryHeader = null;
    target.scrollView = null;
    target.llWebViewContainer = null;
    target.spaceView = null;
    target.content_linkto = null;
  }
}
