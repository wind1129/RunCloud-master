// Generated code from Butter Knife. Do not modify!
package com.example.runcloud.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FragmentMainActivity$$ViewBinder<T extends com.example.runcloud.fragment.FragmentMainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493046, "field 'drawerLayout'");
    target.drawerLayout = finder.castView(view, 2131493046, "field 'drawerLayout'");
    view = finder.findRequiredView(source, 2131493048, "field 'navigationView'");
    target.navigationView = finder.castView(view, 2131493048, "field 'navigationView'");
    view = finder.findRequiredView(source, 2131492974, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131492974, "field 'mToolbar'");
    view = finder.findRequiredView(source, 2131493049, "field 'iv_feedback'");
    target.iv_feedback = finder.castView(view, 2131493049, "field 'iv_feedback'");
  }

  @Override public void unbind(T target) {
    target.drawerLayout = null;
    target.navigationView = null;
    target.mToolbar = null;
    target.iv_feedback = null;
  }
}
