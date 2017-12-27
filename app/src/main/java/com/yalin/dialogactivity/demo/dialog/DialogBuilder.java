package com.yalin.dialogactivity.demo.dialog;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * YaLin
 * On 2017/12/23.
 */

public class DialogBuilder<T extends DialogContentFragment<C>, C> {

    private Context mContext;
    private Class<T> mFragmentClass;
    private C mCallback;
    private Bundle mArgs;

    public static <T extends DialogContentFragment<C>, C> DialogBuilder<T, C> from(
            Class<T> fragmentClass) {
        return new DialogBuilder<>(fragmentClass);
    }

    private DialogBuilder(Class<T> fragmentClass) {
        mFragmentClass = fragmentClass;
    }

    public DialogBuilder<T, C> on(Context context) {
        mContext = context;
        return this;
    }

    public DialogBuilder<T, C> callback(C callback) {
        mCallback = callback;
        return this;
    }

    public DialogBuilder<T, C> args(Bundle args) {
        mArgs = args;
        return this;
    }

    public void show() {
        Preconditions.checkNotNull(mContext);
        Preconditions.checkNotNull(mFragmentClass);

        int callBackId = CallbackPool.INVALID_CALLBACK_ID;
        if (mCallback != null) {
            callBackId = CallbackPool.getInstance().put(this, mCallback);
        }
        Intent intent = new Intent(mContext, DialogActivity.class);
        intent.putExtra(DialogActivity.CALLBACK_ID, callBackId);
        intent.putExtra(DialogActivity.FRAGMENT_CLASS, mFragmentClass);
        if (mArgs != null) {
            intent.putExtra(DialogActivity.ARGS, mArgs);
        }
        if (!(mContext instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mContext.startActivity(intent);
    }
}
