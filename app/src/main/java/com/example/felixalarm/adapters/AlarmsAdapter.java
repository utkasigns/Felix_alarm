package com.example.felixalarm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.felixalarm.R;

import java.util.List;

public class AlarmsAdapter extends RecyclerView.Adapter<AlarmsAdapter.AlarmsViewHolder> {
    private List<String> alarms;
    private List<Integer> times;
    private List<Switch> switches;

    public AlarmsAdapter(List<String> alarms,List<Integer> times,List<Switch> switches){
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
    public void onBindViewHolder(@NonNull AlarmsViewHolder holder, final int position) {
        //.....................................................................................
        // тут надо полученные переменные задать и еще я не уверенна надо ли кнопку свитч где
        // то тут упоминать
        holder.alarmSettenName.setText();
        holder.alarmSettenTime.setText();
        //.....................................................................................
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AlarmsViewHolder  extends RecyclerView.ViewHolder{
        TextView alarmSettenTime,alarmSettenName;
        Switch switchAlarm;

        public AlarmsViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmSettenTime=itemView.findViewById(R.id.alarmSettenTime);
            alarmSettenName=itemView.findViewById(R.id.alarmSettenName);
            switchAlarm=itemView.findViewById(R.id.switchAlarm);
        }
    }
}
