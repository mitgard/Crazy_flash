package com.example.crazy_flash.app;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.ImageView;


public class MainActivity extends Activity {
    boolean flashOn = false;
    private FlashlightController flashlightController;
    private Soundmeter noise;
    Drawable anim_image;
    ImageView flameView;
    Animatable animatableListner;
    final Handler mHandler = new Handler();
    final Handler animHandler = new Handler();
    private AnimationDrawable animationDrawable;
    private ScaleGestureDetector scaleGestureDetector;

    final Runnable listner = new Runnable(){
            @Override
            public void run() {
                double check = noise.getAmplitude();
                if(check>5)
                {
                    if(!flashOn)
                    {

                        if(animatableListner.isRunning()) {
                            animatableListner.stop();
                            flameView.setVisibility(View.INVISIBLE);
                        }
                        flashlightController.killFlashlight();

                    }
                }
                mHandler.postDelayed(listner, 100);

            }
    };

    final Runnable animRun =new Runnable() {
        @Override
        public void run() {
  /*          if(!flashOn)
            {
                animationDrawable.start();
            }
            else
            {
                animationDrawable.stop();
            }

            animHandler.postDelayed(animRun, 100);
    */    }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashlightController = new FlashlightController(this);
//        Button flashlight = (Button) findViewById(R.id.button);
//        ImageView anim_image = (ImageView) findViewById(R.id.anim_image);
 /*       anim_image.setBackgroundResource(R.drawable.animat);
        animationDrawable = (AnimationDrawable) anim_image.getBackground();

        animHandler.postDelayed(animRun, 100);
*/      mHandler.postDelayed(listner, 100);
        noise = new Soundmeter();
        noise.start();
        flameView =(ImageView) findViewById(R.id.flame);
        ImageView vectorAnimation = (ImageView) findViewById(R.id.flame);
        anim_image = vectorAnimation.getDrawable();
        if(anim_image instanceof Animatable)
        {
          animatableListner =  ((Animatable)anim_image);
        }
        scaleGestureDetector = new ScaleGestureDetector(this, new onScaleGestureListener());
//        flashlight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!flashOn) {
//                    flashlightController.setFlashlight(true);
//
//                }
//
//                else {
//                    flashlightController.killFlashlight();
//                    flashOn = !flashOn;
//                }
//            }
//        });
    }

        private class onScaleGestureListener extends SimpleOnScaleGestureListener{
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                flashlightController.setFlashlight(true);
                flameView.setVisibility(View.VISIBLE);
                animatableListner.start();
                return true;
            }

}

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // on pause turn off the flash
//        flashturn.turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // on resume turn on the flash
//        if(flashturn.hasFlash)
//            flashturn.turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();


        // on starting the app get the camera params
//        flashturn.getCamera();

    }

    @Override
    protected void onStop() {
        super.onStop();


        // on stop release the camera
//        if (flashturn.camera != null) {
//            flashturn.camera.release();
//            flashturn.camera = null;
//        }
    }

}
