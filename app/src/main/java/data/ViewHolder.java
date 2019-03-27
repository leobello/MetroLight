package data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import my.app.R;

public class ViewHolder extends RecyclerView.ViewHolder {


    public TextView nom, ligne;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nom = (TextView) itemView.findViewById(R.id.nomTram);
        ligne = (TextView) itemView.findViewById(R.id.nomLigne);
    }
}
