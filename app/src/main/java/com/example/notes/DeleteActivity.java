package com.example.notes;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;
import android.widget.TextView;

import androidx.wear.widget.CircularProgressLayout;

public class DeleteActivity extends WearableActivity implements CircularProgressLayout.OnTimerFinishedListener, CircularProgressLayout.OnClickListener{

    CircularProgressLayout circularProgressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        circularProgressLayout = (CircularProgressLayout) findViewById(R.id.delayed_confirm);

        //could be a bug in this line because did not follow the tutorial exactly
        //circularProgressLayout.setOnTimerFinishedListener(circularProgressLayout.getOnTimerFinishedListener());
        circularProgressLayout.setOnTimerFinishedListener(this);

        circularProgressLayout.setTotalTime(3000);

        circularProgressLayout.startTimer();
    }

    @Override
    public void onTimerFinished(CircularProgressLayout layout) {
        Helper.displayConfirmation("Deleted", this);

        String id = getIntent().getStringExtra("id");

        Helper.removeNote(id, this);

        finish();
    }

    @Override
    public void onClick(View v) {
        Helper.displayConfirmation("Cancelled", this);
        //could be a bug
        circularProgressLayout.stopTimer();
        finish();
    }
}
