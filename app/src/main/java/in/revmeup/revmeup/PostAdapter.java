package in.revmeup.revmeup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context mContext;
    List<Advertisement> mData ;
    Uri imguri;
    String url;

    public PostAdapter(Context mContext, List<Advertisement> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_post_item,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvProduct.setText(mData.get(position).getProductName());
        holder.tvDescription.setText(mData.get(position).getProductDescription());
        /*url=mData.get(position).getProductImageUrl();
        imguri = Uri.parse(url);
        holder.imgPost.setImageURI(imguri);*/
        //Picasso.with(mContext).load(mData.get(position).getProductImageUrl()).into(holder.imgPost);
        Glide.with(mContext).load(mData.get(position).getProductImageUrl()).placeholder(R.drawable.bags).into(holder.imgPost);
        Glide.with(mContext).load(mData.get(position).getProductImageUrl()).placeholder(R.drawable.me).into(holder.imgPostProfile);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription;
        TextView tvProduct;
        ImageView imgPost;
        ImageView imgPostProfile;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvDescription = itemView.findViewById(R.id.row_post_description);
            tvProduct = itemView.findViewById(R.id.row_post_product);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_post_profile_img);

            /*Intent postDetailActivity = new Intent(mContext,PostDetailActivity.class);
            int position = getAdapterPosition();
            postDetailActivity.putExtra("postImage",mData.get(position).getProductImageUrl());
            mContext.startActivity(postDetailActivity);
           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent postDetailActivity = new Intent(mContext,PostDetailActivity.class);
                    int position = getAdapterPosition();
                    postDetailActivity.putExtra("title",mData.get(position).getTitle());
                    postDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    postDetailActivity.putExtra("description",mData.get(position).getDescription());
                    postDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    postDetailActivity.putExtra("userPhoto",mData.get(position).getUserPhoto());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    postDetailActivity.putExtra("postDate",timestamp) ;
                    mContext.startActivity(postDetailActivity);
                }
            });*/

        }


    }
}