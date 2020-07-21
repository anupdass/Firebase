package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private Context context;
    private List<Employee>employees;

    public Adapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
            holder.t1.setText(employees.get(position).getName());
            holder.t2.setText(String.valueOf(employees.get(position).getSalary()));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.name_Tv);
            t2 = itemView.findViewById(R.id.salary_Tv);
        }
    }
}
