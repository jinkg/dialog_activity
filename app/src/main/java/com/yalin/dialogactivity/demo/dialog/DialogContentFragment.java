package com.yalin.dialogactivity.demo.dialog;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * YaLin
 * On 2017/12/23.
 */

public abstract class DialogContentFragment<C> extends Fragment {

  protected abstract void setCallback(C callback);

  protected void dismiss() {
    Activity activity = getActivity();
    if (activity != null) {
      activity.finish();
    }
  }

}
