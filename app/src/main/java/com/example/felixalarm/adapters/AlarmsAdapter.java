package com.example.felixalarm.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.felixalarm.R;
import com.example.felixalarm.listeners.AlarmsListener;

import java.util.List;

public class AlarmsAdapter extends RecyclerView.Adapter<AlarmsAdapter.AlarmsViewHolder> {
    private List<String> alarms;
    private List<String> times;
    private List<Switch> switches;

    public AlarmsAdapter(List<String> alarms,List<String> times,List<Switch> switches){
        this.alarms=alarms;
        this.times=times;
        this.switches=switches;
    }

    @NonNull
    @Override
    public AlarmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlarmsViewHolder(
       LayoutInflater.from(parent.getContext()).inflate(
                R.layout.uniwersal_alarm,parent,false
            )
        );

    }

    @Override
    public void onBindViewHolder(@NonNull AlarmsViewHolder holder,@SuppressLint("RecyclerView") final int position) {
        holder.alarmSettenName.setText(alarms.get(position));
        holder.alarmSettenTime.setText(times.get(position));
        holder.layoutAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmsListener.onAlarmClicked(alarms.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AlarmsViewHolder  extends RecyclerView.ViewHolder{
        TextView alarmSettenTime,alarmSettenName;

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchAlarm;
        ConstraintLayout layoutAlarm;

        public AlarmsViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmSettenTime=itemView.findViewById(R.id.alarmSettenTime);
            alarmSettenName=itemView.findViewById(R.id.alarmSettenName);
            switchAlarm=itemView.findViewById(R.id.switchAlarm);
        }
    }
}
