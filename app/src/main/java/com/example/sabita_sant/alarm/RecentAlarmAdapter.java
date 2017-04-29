package com.example.sabita_sant.alarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabita_Sant on 16/04/17.
 */

public class RecentAlarmAdapter extends ArrayAdapter<RecentAlarmRes> {
    List<RecentAlarmRes> list= new ArrayList<>();
    public RecentAlarmAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(RecentAlarmRes object) {
        super.add(object);
        list.add(object);
        Log.e("size", String.valueOf(list.size()));
    }

    @Override
    public void clear() {
        super.clear();
        list.clear();
    }

    @Override
    public int getCount() {

        return list.size();

    }

    @Nullable
    @Override
    public RecentAlarmRes getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        Holder holder;
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.recent_row,parent,false);
            holder=new Holder();
            holder.time_tx= (TextView) row.findViewById(R.id.time_recent);
            holder.status_sw= (Switch) row.findViewById(R.id.switch_recent);
            row.setTag(holder);
        }
        else
        {
            holder= (Holder) row.getTag();
        }
        RecentAlarmRes recent=this.getItem(position);
        holder.time_tx.setText(recent.getTime());
        if(recent.getStatus().equals("true"))
            holder.status_sw.setChecked(true);
        return row;
    }

    class Holder{
        TextView time_tx;
        Switch status_sw;
    }
}
