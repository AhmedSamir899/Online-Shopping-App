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

import com.example.onlineshopping.R;
import com.example.onlineshopping.UserFunctions.Showitem;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.viewHolderitem>{
    List<itemModel> itemlist;
    Context context2;

    public itemAdapter(Context context2, List<itemModel> itemlist){
        this.context2= context2;
        this.itemlist=itemlist;
    }

    @Override
    public viewHolderitem onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context2).inflate(R.layout.activity_items,parent,false);
        return new viewHolderitem(view);
    }


    @Override
    public void onBindViewHolder(viewHolderitem holder, int position) {

        String nameofitem = itemlist.get(position).getItemname();
        String descofitem = itemlist.get(position).getItemDesc();
        int priceofitem = itemlist.get(position).getItemprice();
        int photoofitem = itemlist.get(position).getItemphoto();
        int backgroundofitem = itemlist.get(position).getBackgrounditem();
        int countofitem = itemlist.get(position).getItemcount();


        holder.Itemname.setText(nameofitem);
        holder.ItemDesc.setText(descofitem);
        holder.Priceitem.setText(String.valueOf(priceofitem));
        holder.itemphoto.setImageResource(photoofitem);
        holder.itembackground.setImageResource(backgroundofitem);
        holder.countitem.setText(String.valueOf(countofitem));



        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context2, Showitem.class);
                intent.putExtra("itemname",nameofitem);
                context2.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }



    public class viewHolderitem extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Itemname;
        TextView ItemDesc;
        TextView Priceitem;
        TextView countitem;
        ImageView itemphoto;
        ImageView itembackground;
        LinearLayoutCompat item;


        public viewHolderitem(View itemView2) {
            super(itemView2);
            Itemname= itemView2.findViewById(R.id.itemname);
            ItemDesc= itemView2.findViewById(R.id.itemdescription);
            Priceitem= itemView2.findViewById(R.id.itemprice);
            countitem= itemView2.findViewById(R.id.itemcount);
            itemphoto= itemView2.findViewById(R.id.itempicture);
            itembackground= itemView2.findViewById(R.id.backgrounditem);
            item =itemView2.findViewById(R.id.items);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
        }
    }

}
