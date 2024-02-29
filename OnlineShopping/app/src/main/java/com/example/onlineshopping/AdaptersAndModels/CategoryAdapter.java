package com.example.onlineshopping.AdaptersAndModels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.UserFunctions.ItemsOfCategories;
import com.example.onlineshopping.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    List<CategoryModel>categorylist;
    Context context;

    public CategoryAdapter(Context context, List<CategoryModel> categorylist){
        this.context=context;
        this.categorylist=categorylist;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_categories,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        String nameofCategory = categorylist.get(position).getCategoryname();
        int iconofcategory = categorylist.get(position).getCategoryPhoto();
        int backgroundofcategory = categorylist.get(position).getCategorybackground();

        holder.Categoryname.setText(nameofCategory);
        holder.categoryicon.setImageResource(iconofcategory);
        holder.categorybackground.setImageResource(backgroundofcategory);


        holder.Category.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemsOfCategories.class);
            intent.putExtra("name",nameofCategory);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return categorylist.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Categoryname;
        ImageView categoryicon;
        ImageView categorybackground;
        LinearLayoutCompat Category;


        public viewHolder(View itemView) {
            super(itemView);
            Categoryname = itemView.findViewById(R.id.Categoryname);
            categoryicon = itemView.findViewById(R.id.Categoryicon);
            categorybackground = itemView.findViewById(R.id.Categorybackground);
            Category=itemView.findViewById(R.id.Category);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
        }
    }


}
