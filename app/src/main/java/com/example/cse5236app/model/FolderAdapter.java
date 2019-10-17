package com.example.cse5236app.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse5236app.R;

import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderHolder> {
    private List<Folder> folders = new ArrayList<>();

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.folder_item, parent, false);
        return new FolderHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {
        Folder currentFolder = folders.get(position);
        holder.textViewTitle.setText(currentFolder.getTitle());
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public void setFolders(List<Folder> folders){
        this.folders = folders;
        notifyDataSetChanged(); //this will be changed later
    }

    class FolderHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;

        public FolderHolder(View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
        }
    }
}
