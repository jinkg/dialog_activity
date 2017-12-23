package com.yalin.dialogactivity.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.yalin.dialogactivity.demo.TestFragment1.Callback1;
import com.yalin.dialogactivity.demo.dialog.DialogContentFragment;

/**
 * YaLin
 * On 2017/12/23.
 */

public class TestFragment1 extends DialogContentFragment<Callback1> {

  private Callback1 callback1;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_dialog1, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.button_action).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (callback1 != null) {
          callback1.onCallback1();
        }
        dismiss();
      }
    });
  }

  @Override
  protected void setCallback(Callback1 callback) {
    callback1 = callback;
  }

  public interface Callback1 {

    void onCallback1();
  }
}
