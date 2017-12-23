package com.yalin.dialogactivity.demo.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.yalin.dialogactivity.demo.R;

/**
 * YaLin
 * On 2017/12/23.
 */

public final class DialogActivity extends AppCompatActivity {

  public static final String CALLBACK_ID = "callback_id";
  public static final String FRAGMENT_CLASS = "fragment_class";
  public static final String ARGS = "args";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dialog);
    if (savedInstanceState == null) {
      attachFragment();
    } else {
      finish();
    }
  }

  @SuppressWarnings("unchecked")
  private void attachFragment() {
    Intent intent = getIntent();
    int callbackId = intent.getIntExtra(CALLBACK_ID, CallbackPool.INVALID_CALLBACK_ID);
    Object callback = CallbackPool.getInstance().obtain(callbackId);
    Class<DialogContentFragment> fragmentClass =
        (Class<DialogContentFragment>) intent.getSerializableExtra(FRAGMENT_CLASS);
    Bundle args = intent.getBundleExtra(ARGS);
    if (fragmentClass == null) {
      finish();
      return;
    }

    DialogContentFragment fragment;
    try {
      fragment = fragmentClass.newInstance();
      fragment.setCallback(callback);
      if (args != null) {
        fragment.setArguments(args);
      }
    } catch (Exception e) {
      finish();
      return;
    }
    getSupportFragmentManager().beginTransaction()
        .add(R.id.dialog_container, fragment)
        .commit();
  }
}
