package edu.huflit.learningenglishapp.main_layout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import edu.huflit.learningenglishapp.R;

public class Blog {
    private String title;
    private String content;

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public static class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
        private List<Blog> blogs;

        public BlogAdapter(List<Blog> blogs) {
            this.blogs = blogs;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_blog, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Blog blog = blogs.get(position);
            holder.titleTextView.setText(blog.getTitle());
            holder.contentTextView.setText(blog.getContent());
        }
        @Override
        public int getItemCount() {
            return blogs.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView titleTextView;
            public TextView contentTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.TwTitle);
                contentTextView = itemView.findViewById(R.id.tvContent);
            }
        }
    }

}


