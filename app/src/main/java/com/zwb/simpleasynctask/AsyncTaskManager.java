package com.zwb.simpleasynctask;

/**
 * 异步任务类
 * Created by zwb on 2015/6/29.
 */
public class AsyncTaskManager<K> {
    private BackgroundCallback backgroundCallback;
    private DoneCallback doneCallback;
    private FailCallback failCallback;
    private K attachment;

    /**
     * 初始化
     *
     * @return
     */
    public AsyncTaskManager(K attachment) {
        this.attachment = attachment;
    }

    /**
     * 执行后台的任务
     *
     * @param callback
     * @param <T>
     * @return
     */
    public <T> AsyncTaskManager background(BackgroundCallback<T> callback) {
        this.backgroundCallback = callback;
        return this;
    }

    /**
     * 失败之后的回调
     *
     * @param failCallback
     * @param <k>
     * @return
     */
    public <k> AsyncTaskManager fail(FailCallback<K> failCallback) {
        this.failCallback = failCallback;
        return this;
    }

    /**
     * 成功之后的执行动作
     *
     * @param done
     * @param <T>
     * @return
     */
    public <K, T> AsyncTaskManager done(DoneCallback<K, T> done) {
        this.doneCallback = done;
        return this;
    }

    /**
     * 运行异步任务
     *
     * @return
     */
    public String run() {
        return Tasks.executeInBackground(attachment, backgroundCallback, doneCallback, failCallback);
    }

    /**
     * 取消异步任务
     *
     * @param tag
     */
    public void cancel(String tag) {
        Tasks.cancel(tag);
    }

    /**
     * 取消所有异步任务
     */
    public void cancelAll() {
        Tasks.cancelAll(attachment);
    }
}
