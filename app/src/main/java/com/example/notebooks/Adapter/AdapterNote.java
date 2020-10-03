package com.example.notebooks.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebooks.R;
import com.example.notebooks.activities.MainActivity;
import com.example.notebooks.model.Note;

import java.util.List;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.ViewHolder> {

    private MainActivity context;
    private List<Note> listnote;
    private TextView textCreateTime;

    public AdapterNote(MainActivity context, List<Note> listnote) {
        this.context = context;
        this.listnote = listnote;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View objview = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(objview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = listnote.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
        holder.createTime.setText(note.getTimeCreate());
    }

    @Override
    public int getItemCount() {
        return listnote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, createTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            content = (TextView) itemView.findViewById(R.id.txt_content);
            createTime = (TextView) itemView.findViewById(R.id.text_create_time);
        }
    }
}
