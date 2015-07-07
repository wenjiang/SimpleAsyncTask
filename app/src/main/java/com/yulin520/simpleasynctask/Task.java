package com.yulin520.simpleasynctask;

import java.lang.ref.WeakReference;

/**
 * 任务类
 * Created by zwb on 2015/7/2.
 */
class Task<K, T> extends AbstractTask<K, T> {
    private WeakReference<K> weakAttachment;
    private DoneCallback<K, T> doneCallback;
    private FailCallback<K> failCallback;

    Task(K attachment, BackgroundCallback<T> backgroundWork, DoneCallback<K, T> doneCallback, FailCallback<K> failCallback) {
        super(backgroundWork);
        super.attachment = attachment;
        this.doneCallback = doneCallback;
        this.failCallback = failCallback;
    }

    @Override
    protected void onSuccess(K attachment, T result) {
        weakAttachment = new WeakReference<>(attachment);
        if (doneCallback != null && attachment != null) {
            doneCallback.onSuccess(weakAttachment.get(), result);
        }
    }

    @Override
    protected void onError(K attachment, Exception e) {
        weakAttachment = new WeakReference<>(attachment);
        if (failCallback != null && attachment != null) {
            failCallback.onError(weakAttachment.get(), e);
        }
    }
}
