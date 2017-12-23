package com.yalin.dialogactivity.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.yalin.dialogactivity.demo.TestFragment2.Callback2;
import com.yalin.dialogactivity.demo.dialog.DialogContentFragment;

/**
 * YaLin
 * On 2017/12/23.
 */

public class TestFragment2 extends DialogContentFragment<Callback2> {

  private Callback2 callback2;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_dialog2, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.button_action).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (callback2 != null) {
          callback2.onCallback2("Action 2");
        }
        dismiss();
      }
    });
  }

  @Override
  protected void setCallback(Callback2 callback) {
    callback2 = callback;
  }


  public interface Callback2 {

    void onCallback2(String content);
  }
}
