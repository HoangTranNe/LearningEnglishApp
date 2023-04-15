package edu.huflit.learning_english.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.learning_english.R;

public class item_blog_class {
    private String title;
    private String content;

    public item_blog_class(String title, String content) {
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
        private List<item_blog_class> blogs;

        public BlogAdapter(List<item_blog_class> blogs) {
            this.blogs = blogs;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_item_blog, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            item_blog_class blog = blogs.get(position);
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
