package com.example.jialong.indicatorrecyclerdecoration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jialong.indicatorrecyclerdecoration.ItemDecoration.TRecyclerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_one:
                startActivity(new Intent(this, TRecyclerActivity.class));
                break;
            case R.id.bt_two:
                break;
        }
    }
}
