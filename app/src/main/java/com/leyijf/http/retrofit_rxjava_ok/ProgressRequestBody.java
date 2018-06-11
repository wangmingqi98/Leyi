package com.leyijf.http.retrofit_rxjava_ok;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;


import com.leyijf.listener.ProgressListener;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class ProgressRequestBody extends RequestBody {
    public static final int UPDATE = 0x01;
    private RequestBody requestBody;
    private ProgressListener mListener;
    private BufferedSink bufferedSink;
    private static MyHandler myHandler;

    public ProgressRequestBody(FragmentActivity activity, RequestBody body, ProgressListener listener) {
        requestBody = body;
        mListener = listener;
        if (myHandler == null) {
            myHandler = new MyHandler(activity);
        }
    }

    class  MyHandler extends Handler {
        private WeakReference<FragmentActivity> mActivity;

        public MyHandler(FragmentActivity activity) {
            mActivity = new WeakReference<FragmentActivity>(activity);
        }

        public MyHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            FragmentActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case UPDATE:
                        ProgressModel progressModel = (ProgressModel) msg.obj;
                        if (mListener != null)
                            mListener.onProgress(progressModel.getCurrentBytes(), progressModel.getContentLength(), progressModel.isDone());
                        break;

                }
            }
        }


    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        //写入
        requestBody.writeTo(bufferedSink);
        //刷新
        bufferedSink.flush();
    }

    private Sink sink(BufferedSink sink) {

        return new ForwardingSink(sink) {
            long bytesWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;
                //回调
                Message msg = Message.obtain();
                msg.what = UPDATE;
                msg.obj = new ProgressModel(bytesWritten, contentLength, bytesWritten == contentLength);
                myHandler.sendMessage(msg);
            }
        };
    }


}
