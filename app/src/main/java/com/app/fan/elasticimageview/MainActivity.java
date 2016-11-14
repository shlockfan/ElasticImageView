package com.app.fan.elasticimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.app.fan.elastic_imageview.ElasticImageView;

public class MainActivity extends AppCompatActivity {
    private ElasticImageView eimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eimage= (ElasticImageView) findViewById(R.id.e_img);
        eimage.setOnImageTabListener(new ElasticImageView.OnImageTabListener() {
            @Override
            public void OnImageTab() {
                Toast.makeText(MainActivity.this, "ImageTab", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
