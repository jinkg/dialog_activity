package com.yalin.dialogactivity.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.yalin.dialogactivity.demo.ArithmeticFragment.ArithmeticResultCallback;
import com.yalin.dialogactivity.demo.dialog.DialogContentFragment;

/**
 * YaLin
 * On 2017/12/23.
 */

public class ArithmeticFragment extends DialogContentFragment<ArithmeticResultCallback> {

  private static final String ARGS_A = "a";
  private static final String ARGS_B = "b";

  private ArithmeticResultCallback callback;

  public static Bundle createArgsBundle(int a, int b) {
    Bundle args = new Bundle();
    args.putInt(ARGS_A, a);
    args.putInt(ARGS_B, b);
    return args;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_arithmetic, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      final int a = args.getInt(ARGS_A);
      final int b = args.getInt(ARGS_B);

      view.findViewById(R.id.plus).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          if (callback != null) {
            callback.onPlus(a + b);
          }
          dismiss();
        }
      });

      view.findViewById(R.id.sub).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          if (callback != null) {
            callback.onSub(a - b);
          }
          dismiss();
        }
      });
    }
  }

  @Override
  protected void setCallback(ArithmeticResultCallback callback) {
    this.callback = callback;
  }

  public interface ArithmeticResultCallback {

    void onPlus(int result);

    void onSub(int result);
  }
}
