package edu.huflit.learning_english;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.learning_english.placeholder.PlaceholderContent;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter {
    public MyItemRecyclerViewAdapter(List<PlaceholderContent.PlaceholderItem> items) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
