package com.list.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

   List<NotesModel> notesModel;
   Context context;

    public NotesAdapter(List<NotesModel> notesModel, Context context) {
        this.notesModel = notesModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_row,parent,false);
         MyViewHolder hholder = new MyViewHolder(view);

         return hholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NotesModel model = notesModel.get(position);
        holder.mtitle.setText(model.getTitle());
        holder.mdescrip.setText(model.getDescription());

    }

    @Override
    public int getItemCount() {

        return notesModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mtitle, mdescrip;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mtitle = itemView.findViewById(R.id.andtitle);
            mdescrip = itemView.findViewById(R.id.adddesc);
        }
    }
}
