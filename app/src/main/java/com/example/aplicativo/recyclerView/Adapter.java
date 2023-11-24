package com.example.aplicativo.recyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicativo.R;
import com.example.aplicativo.home.MainActivity;
import com.example.aplicativo.localização.Loc;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Loc> data;

    public Adapter(MainActivity context, ArrayList<Loc> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       Loc loc = data.get(position);
        holder.teste1.setText("Latitude " + loc.getLatitude());
        holder.teste2.setText("Longitude " + loc.getLongitude());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, teste1, teste2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            teste1 = itemView.findViewById(R.id.latitude);
            teste2 = itemView.findViewById(R.id.longitude);
        }
    }


}
