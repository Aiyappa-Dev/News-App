package com.aimright.admin.demoapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.aimright.admin.demoapp.Activities.NewsDetailsActivity;
import com.aimright.admin.demoapp.Models.fetchUrl.FetchUrl;
import com.aimright.admin.demoapp.Models.fetchUrl.ResultsFetchUrl;
import com.aimright.admin.demoapp.Utils.DataUtils;
import com.aimright.admin.demoapp.R;
/*import com.thedascapital.www.newsapp.R;*/

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private final String type1;
    private List<ResultsFetchUrl> articles;
    private List<ResultsFetchUrl> articles11;
    private Context context;
    private OnItemClickListerner onItemClickListerner;
    private static int positionClick;

    public Adapter(/*List<Article> articles,*/FetchUrl articles1 , Context context, String type/*, FetchUrl articles11*/) {

            this.articles = articles1.getData().getResults();
            this.context = context;
          //  articles11=  articles11.getData().getResults();

        this.type1=type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view, onItemClickListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {




        final MyViewHolder holder = viewHolder;
      //  Article model = articles.get(i);
      // List<ResultsFetchUrl> model = (List<ResultsFetchUrl>) articles.get(i);
        /*if (!type1.isEmpty())
        {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(DataUtils.getRandomDrawbleColor());
            requestOptions.error(DataUtils.getRandomDrawbleColor());
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            requestOptions.centerCrop();
            List<String> img= Arrays.asList(new String[1]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ii = new Intent(context, NewsDetailsActivity.class);
                    ii.putExtra("Id",articles.get(i).getId());
                    ii.putExtra("item_size",articles.size());
                    ii.putExtra("articles", (Serializable) articles);
                    ii.putExtra("title","");
                    //Article article = articles.get(position);
                    ii.putExtra("position", i);
                    context.startActivity(ii);
                }
            });

            try {
              //  img=Arrays.asList();
                Glide.with(context)
                        .load(articles11.get(i).getUrlToImage())
                        .apply(requestOptions)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                Log.e("image",String.valueOf(e.toString()));
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder.imageView);
            } catch (Exception e) {
                holder.progressBar.setVisibility(View.GONE);
                //e.printStackTrace();
            }

         holder.title.setText(articles11.get(i).getTitle());
        holder.desc.setText(articles11.get(i).getDescription());
        holder.source.setText(articles11.get(i).getSource().getName());
        holder.time.setText("\u2022"+ DataUtils.DateToTimeFormat(articles11.get(i).getPublishedAt()));
        holder.publishedAt.setText(DataUtils.DateFormat(articles11.get(i).getPublishedAt()));
        holder.author.setText(articles11.get(i).getAuthor());

        }
        else
        {*/
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(DataUtils.getRandomDrawbleColor());
            requestOptions.error(DataUtils.getRandomDrawbleColor());
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            requestOptions.centerCrop();
            List<String> img= Arrays.asList(new String[1]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*testing*/
                    positionClick=i;
                    Intent ii = new Intent(context, NewsDetailsActivity.class);
                    ii.putExtra("Id",articles.get(i).getId());
                    ii.putExtra("item_size",articles.size());
                    ii.putExtra("articles", (Serializable) articles);
                    ii.putExtra("title",type1);
                    //Article article = articles.get(position);
                    ii.putExtra("position", i);
                    context.startActivity(ii);
                }
            });

            try {
                img=Arrays.asList(articles.get(i).getThumbnails());
                Glide.with(context)
                        .load(img.get(0))
                        .apply(requestOptions)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                Log.e("image",String.valueOf(e.toString()));
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder.imageView);
            } catch (Exception e) {
                holder.progressBar.setVisibility(View.GONE);

                //e.printStackTrace();
            }

            holder.title.setText(articles.get(i).getNews_title());
            //holder.desc.setText(model.getDescription());
            holder.source.setText(articles.get(i).getAuthor());
            holder.time.setText("\u2022"+ DataUtils.DateToTimeFormat(articles.get(i).getTime_stamp()));
            holder.publishedAt.setText(DataUtils.DateFormat(articles.get(i).getTime_stamp()));
            holder.author.setText(articles.get(i).getAuthor());

      //  }


    }

    @Override
    public int getItemCount() {
       /* if (!type1.isEmpty())
        {
            return articles11.size();
        }
        else
        {*/
            return articles.size();
       // }
    }

    public void setOnItemClickListerner(OnItemClickListerner onItemClickListerner) {
        this.onItemClickListerner = onItemClickListerner;
    }

    public interface OnItemClickListerner{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, desc, author, publishedAt, source, time;
        ImageView imageView;
        ProgressBar progressBar;
        OnItemClickListerner onItemClickListerner;

        public MyViewHolder(@NonNull View itemView, OnItemClickListerner onItemClickListerner) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            publishedAt = itemView.findViewById(R.id.publishAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.progress_load_photo);

            this.onItemClickListerner = onItemClickListerner;

        }

        @Override
        public void onClick(View v) {
            onItemClickListerner.onItemClick(v, getAdapterPosition());
        }
    }
}
