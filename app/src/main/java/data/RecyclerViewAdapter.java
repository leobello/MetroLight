package data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import my.app.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Ligne> lignes;

    public RecyclerViewAdapter(ArrayList<Ligne> lignes) {
        this.lignes = lignes;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row, viewGroup, false);
        ViewHolder mViewHolder = new ViewHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Ligne ligne1 = lignes.get(i);
        viewHolder.ligne.setText(ligne1.getLongName());
        viewHolder.nom.setText(ligne1.getShortName());
    }

    @Override
    public int getItemCount() {
        return lignes.size();
    }
}
