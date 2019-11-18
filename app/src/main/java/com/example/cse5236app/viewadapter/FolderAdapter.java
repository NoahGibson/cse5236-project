package com.example.cse5236app.viewadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cse5236app.AddFolderActivity;
import com.example.cse5236app.FolderActivity;
import com.example.cse5236app.R;
import com.example.cse5236app.model.Folder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderHolder> {

    private List<Folder> folders = new ArrayList<>();

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.folder_item, parent, false);
        return new FolderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {

        Folder currentFolder = folders.get(position);
        holder.bind(currentFolder);
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
        notifyDataSetChanged(); //this will be changed later
    }

    public Folder getFolderAt(int position) {
        return folders.get(position);
    }


    class FolderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Folder mFolder;

        private TextView textViewTitle;

        public FolderHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
        }

        public void bind(Folder folder) {
            mFolder = folder;
            textViewTitle.setText(folder.getTitle());
            textViewTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = AddFolderActivity.newUpdateFolderIntent(context, mFolder);
            ((Activity) context).startActivityForResult(intent, FolderActivity.UPDATE_FOLDER_REQUEST);
        }

    }

}
