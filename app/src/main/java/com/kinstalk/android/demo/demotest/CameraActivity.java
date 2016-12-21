package com.kinstalk.android.demo.demotest;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;

import com.siqing.demotest.R;

import java.io.IOException;

/**
 * Created by siqing on 16/3/7.
 */
public class CameraActivity extends Activity {
    private TextureView cameraTextureView;
    private Camera mCamera;
    private View backgroundView;

    private Matrix currentMatrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraTextureView = (TextureView) findViewById(R.id.camera_texture);
        backgroundView = findViewById(R.id.background_view);
        cameraTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mCamera = Camera.open();
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.setPreviewSize(640, 480);
                mCamera.setParameters(parameters);
                mCamera.setDisplayOrientation(90);
                Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
                cameraTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                        480, 360, Gravity.CENTER));
                backgroundView.setLayoutParams(new FrameLayout.LayoutParams(
                        previewSize.width, previewSize.height, Gravity.CENTER));
                backgroundView.setRotation(90);
                try {
                    mCamera.setPreviewTexture(surface);
                } catch (IOException t) {
                }
                mCamera.startPreview();
                cameraTextureView.setAlpha(1.0f);
//                cameraTextureView.setRotation(90.0f);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                mCamera.stopPreview();
                mCamera.release();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }
        });

    }

    boolean isFirst = false;

    public void changeSize(View v) {
        if (!isFirst) {
            Matrix matrix = cameraTextureView.getMatrix();
            matrix.setScale(1, 16f / 9);
            matrix.postTranslate(0, -140);
            cameraTextureView.setTransform(matrix);
            isFirst = true;
        } else {
            Matrix matrix = cameraTextureView.getMatrix();
            matrix.setTranslate(0, -80);
            cameraTextureView.setTransform(matrix);
        }
    }
}
