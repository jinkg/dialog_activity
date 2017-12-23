package com.yalin.dialogactivity.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.yalin.dialogactivity.demo.ArithmeticFragment.ArithmeticResultCallback;
import com.yalin.dialogactivity.demo.TestFragment1.Callback1;
import com.yalin.dialogactivity.demo.TestFragment2.Callback2;
import com.yalin.dialogactivity.demo.dialog.DialogBuilder;

public class MainActivity extends AppCompatActivity implements Callback1, Callback2,
    ArithmeticResultCallback {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void showDialog1(View view) {
    DialogBuilder.from(TestFragment1.class)
        .on(this)
        .callback(this)
        .show();
  }

  public void showDialog2(View view) {
    DialogBuilder.from(TestFragment2.class)
        .on(this)
        .callback(this)
        .show();
  }

  public void doArithmetic(View view) {
    Bundle args = ArithmeticFragment.createArgsBundle(100, 88);
    DialogBuilder.from(ArithmeticFragment.class)
        .on(this.getApplicationContext())
        .callback(this)
        .args(args)
        .show();
  }

  @Override
  public void onCallback1() {
    Toast.makeText(this, "Callback1", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onCallback2(String content) {
    Toast.makeText(this, "Callback2 " + content, Toast.LENGTH_SHORT).show();
  }


  @Override
  public void onPlus(int result) {
    Toast.makeText(this, "onPlus result " + result, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onSub(int result) {
    Toast.makeText(this, "onSub result " + result, Toast.LENGTH_SHORT).show();
  }
}
