package za.ac.nplinnovations.newsreader.connection.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;
import za.ac.nplinnovations.newsreader.R;
import za.ac.nplinnovations.newsreader.connection.article.ViewArticleActivity;
import za.ac.nplinnovations.newsreader.connection.pojos.Article;
import za.ac.nplinnovations.newsreader.connection.pojos.MainResponse;

public class AdapterArticle extends RecyclerView.Adapter<AdapterArticle.ViewHolder> {
    public static final String SELECTED_ARTICLE = "selectedArticle";
    private MainResponse mData;
    private WeakReference<Context> mContextReference;

    public AdapterArticle(MainResponse mData, Context context) {
        this.mData = mData;
        this.mContextReference = new WeakReference<>(context);
    }

    @NonNull
    @Override
    public AdapterArticle.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContextReference.get());
        View view = inflater.inflate(R.layout.layout_article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArticle.ViewHolder holder, int position) {
        Article article = mData.getResults().get(position);

        holder.tvTitle.setText(article.getTitle());
        holder.tvAuthors.setText(article.getByline());
        holder.tvDate.setText(article.getPublished_date());

        if (article.getMedia()!=null){
            if (article.getMedia().size() > 0){
                Picasso.get()
                        .load(article.getMedia().get(0).getMedia_metadata().get(0).getUrl())
                        .placeholder(R.drawable.no_image_icon)
                        .error(R.drawable.no_image_found)
                        .into(holder.ivArticleImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView ivArticleImage;
        TextView tvTitle, tvAuthors, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivArticleImage = (CircleImageView) itemView.findViewById(R.id.ivArticleImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthors = (TextView) itemView.findViewById(R.id.tvAuthors);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Article article = mData.getResults().get(getAdapterPosition());
            Intent intent = new Intent(v.getContext(), ViewArticleActivity.class);
            intent.putExtra(SELECTED_ARTICLE, (Serializable) article);
            mContextReference.get().startActivity(intent);
        }
    }
}
