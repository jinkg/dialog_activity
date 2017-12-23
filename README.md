# Useage

* Create your custom fragment class entends from `DialogContentFragment` and define your custom callback
```
public class ArithmeticFragment extends DialogContentFragment<ArithmeticResultCallback>
```
```
public interface ArithmeticResultCallback {
    void onPlus(int result);

    void onSub(int result);
  }
```
* Use `DialogBuilder` show your fragment on a context or activity. You need implement your custom callback.
```
public void doArithmetic(View view) {
    Bundle args = ArithmeticFragment.createArgsBundle(100, 88);
    DialogBuilder.from(ArithmeticFragment.class)
        .on(this.getApplicationContext())
        .callback(this)
        .args(args)
        .show();
  }
```
