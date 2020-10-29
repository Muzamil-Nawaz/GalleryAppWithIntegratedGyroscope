package com.example.lab4task3;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String [] memeArray = {"meme1","meme2","meme3","meme4","meme5"};
    int currentIndex = 1;
    SensorManager sensorManager;
    Sensor gyroscope;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        setImage(currentIndex);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyroscopeListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[2] > 0.2f) { // anticlockwise
                    if(currentIndex>0) {
                        currentIndex--;
                        setImage(currentIndex);
                    }
                } else if(sensorEvent.values[2] < -0.2f) { // clockwise
                    if (currentIndex < memeArray.length-1) {
                        currentIndex++;
                        setImage(currentIndex);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(gyroscopeListener,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public  void setImage(int imageIndex){
        int id = getResources().getIdentifier(memeArray[imageIndex], "drawable", getPackageName() );
        imageView.setImageResource(id);
        Toast.makeText(this,"New image Loaded",Toast.LENGTH_SHORT).show();
    }
}
