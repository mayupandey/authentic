package predic.com.authentic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
View mView;
    public ViewHolder(View itemView) {
        super(itemView);
        mView=itemView;

    }
    public void setDetails(Context ctx,String title,String desciption,String image){
        TextView mTitleTV = mView.findViewById(R.id.rTitleTv);
        TextView mDetailTV = mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageIv =  mView.findViewById(R.id.rImageView);
        mTitleTV.setText(title);
        mDetailTV.setText(desciption);
        Picasso.get().load(image).into(mImageIv);

    }
}
