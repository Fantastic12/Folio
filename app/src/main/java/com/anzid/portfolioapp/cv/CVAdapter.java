package com.anzid.portfolioapp.cv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anzid.dynamic_theme.DynamicThemeAdapter;
import com.anzid.portfolioapp.R;
import com.anzid.portfolioapp.themes.ExtKt;

import java.util.List;

public class CVAdapter extends DynamicThemeAdapter<CVAdapter.CVViewHolder> {


    List<CVItem> mdata;

    public CVAdapter(List<CVItem> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public CVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cv,parent,false);

        return new CVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CVViewHolder holder, int position) {

        holder.tvTitle.setText(mdata.get(position).getTitle());
        holder.tvDescription.setText(mdata.get(position).getDescription());
        holder.circle.getDrawable().setTint(ExtKt.getSelectedTheme().getCvLine());
        holder.line.setBackgroundColor(ExtKt.getSelectedTheme().getCvLine());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class CVViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvDescription;
        ImageView circle;
        View line;

        public CVViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_cv_title);
            tvDescription = itemView.findViewById(R.id.item_cv_desc);
            circle = itemView.findViewById(R.id.imageView);
            line = itemView.findViewById(R.id.view);
        }
    }
}
