package com.example.notebooks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebooks.NoteTagActivity;
import com.example.notebooks.R;

import com.example.notebooks.model.Tag;

import java.util.List;

public class AdapterTag extends RecyclerView.Adapter<AdapterTag.ViewHolder> {

    private NoteTagActivity context;
    private List<Tag> listTag;

    public AdapterTag(NoteTagActivity context, List<Tag> listTag) {
        this.context = context;
        this.listTag = listTag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View objview = LayoutInflater.from(context).inflate(R.layout.tag_item, parent, false);
        return new ViewHolder(objview);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tag tags = listTag.get(position);
        Context context = holder.itemView.getContext();
        holder.tagname.setText(tags.gettagname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listTag.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tagname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagname = (TextView) itemView.findViewById(R.id.tagname);
        }
    }
}