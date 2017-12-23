package com.yalin.dialogactivity.demo.dialog;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import java.lang.ref.WeakReference;

/**
 * YaLin
 * On 2017/12/23.
 */

final class CallbackPool {

  private static final int C = 10;
  static final int INVALID_CALLBACK_ID = -1;

  private static CallbackPool INSTANCE;

  private SparseArray<WeakReference<Object>> mCallbackMap = new SparseArray<>();

  static CallbackPool getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CallbackPool();
    }
    return INSTANCE;
  }

  private CallbackPool() {
  }

  int put(@NonNull Object callbackObj) {
    maybeRemoveInvalidCallback();
    int id = getCallbackId(callbackObj);
    mCallbackMap.append(id, new WeakReference<>(callbackObj));
    return id;
  }

  @Nullable
  Object obtain(int id) {
    WeakReference<Object> weakReference = mCallbackMap.get(id);
    mCallbackMap.remove(id);
    if (weakReference != null) {
      return weakReference.get();
    }
    return null;
  }

  private int getCallbackId(Object callbackObj) {
    // TODO: 2017/12/23  
    return callbackObj.hashCode();
  }

  private void maybeRemoveInvalidCallback() {
    int size = mCallbackMap.size();
    if (size >= C) {
      for (int i = 0; i < size; i++) {
        int key = mCallbackMap.keyAt(i);
        WeakReference<Object> ref = mCallbackMap.get(key);
        if (ref == null || ref.get() == null) {
          mCallbackMap.remove(key);
        }
      }
    }
  }
}
