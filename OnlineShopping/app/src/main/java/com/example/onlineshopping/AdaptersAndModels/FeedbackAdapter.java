package com.example.onlineshopping.AdaptersAndModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.viewHolderitem>{

    List<FeedbackModel> feedlist;
    Context context2;

    public FeedbackAdapter(Context context2, List<FeedbackModel> feedlist){
        this.context2= context2;
        this.feedlist=feedlist;
    }

    @Override
    public FeedbackAdapter.viewHolderitem onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context2).inflate(R.layout.feedback_view,parent,false);
        return new FeedbackAdapter.viewHolderitem(view);
    }


    @Override
    public void onBindViewHolder(FeedbackAdapter.viewHolderitem holder, int position) {

        String username = feedlist.get(position).getUsername();
        String feedback= feedlist.get(position).getFeedback();


        holder.UserName.setText(username);
        holder.Feedback.setText(feedback);



    }

    @Override
    public int getItemCount() {
        return feedlist.size();
    }



    public class viewHolderitem extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView UserName;
        TextView Feedback;


        public viewHolderitem(View itemView2) {
            super(itemView2);
            UserName= itemView2.findViewById(R.id.USER_NAME);
            Feedback= itemView2.findViewById(R.id.FEEDBACKKK);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
        }
    }

}
