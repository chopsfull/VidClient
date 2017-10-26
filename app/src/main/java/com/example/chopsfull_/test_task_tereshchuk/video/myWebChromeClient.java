package com.example.chopsfull_.test_task_tereshchuk.video;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.example.chopsfull_.test_task_tereshchuk.R;
import com.example.chopsfull_.test_task_tereshchuk.VideoActivity;

public class myWebChromeClient extends WebChromeClient {
    private Bitmap mDefaultVideoPoster;
    private View mVideoProgressView;


    private WebView webView;
    private FrameLayout customViewContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private View mCustomView;
    private VideoActivity videoActivity;

    public myWebChromeClient(WebView webView, FrameLayout customViewContainer, CustomViewCallback customViewCallback, VideoActivity videoActivity) {
        this.webView = webView;
        this.customViewContainer = customViewContainer;
        this.customViewCallback = customViewCallback;
        this.videoActivity = videoActivity;
    }

    @Override
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        onShowCustomView(view, callback);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {

        // if a view already exists then immediately terminate the new one
        if (mCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }
        mCustomView = view;
        webView.setVisibility(View.GONE);
        customViewContainer.setVisibility(View.VISIBLE);
        customViewContainer.addView(view);
        customViewCallback = callback;
    }

    @Override
    public View getVideoLoadingProgressView() {

        if (mVideoProgressView == null) {
            LayoutInflater inflater = LayoutInflater.from(videoActivity);
            mVideoProgressView = inflater.inflate(R.layout.video_progress, null);
        }
        return mVideoProgressView;
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();    //To change body of overridden methods use File | Settings | File Templates.
        if (mCustomView == null)
            return;

        webView.setVisibility(View.VISIBLE);
        customViewContainer.setVisibility(View.GONE);

        // Hide the custom view.
        mCustomView.setVisibility(View.GONE);

        // Remove the custom view from its container.
        customViewContainer.removeView(mCustomView);
        customViewCallback.onCustomViewHidden();

        mCustomView = null;
    }
}
