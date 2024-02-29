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
import com.example.onlineshopping.UserFunctions.ShowItemFromCart;

import java.util.List;

public class itemcartAdapter extends RecyclerView.Adapter<itemcartAdapter.viewHolderitemcart>{
    List<itemModel> itemlist;
    Context context3;

    public itemcartAdapter(Context context3, List<itemModel> itemlist){
        this.context3=context3;
        this.itemlist=itemlist;
    }

    @Override
    public viewHolderitemcart onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context3).inflate(R.layout.item_in_cart,parent,false);
        return new viewHolderitemcart(view);
    }


    @Override
    public void onBindViewHolder(viewHolderitemcart holder, int position) {

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



        holder.item_incart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context3, ShowItemFromCart.class);
                intent.putExtra("itemname",nameofitem);
                context3.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }



    public class viewHolderitemcart extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Itemname;
        TextView ItemDesc;
        TextView Priceitem;
        TextView countitem;
        ImageView itemphoto;
        ImageView itembackground;
        LinearLayoutCompat item_incart;


        public viewHolderitemcart(View itemView3) {
            super(itemView3);
            Itemname= itemView3.findViewById(R.id.itemnameincart);
            ItemDesc= itemView3.findViewById(R.id.itemdescriptionincart);
            Priceitem= itemView3.findViewById(R.id.itempriceincart);
            countitem= itemView3.findViewById(R.id.itemcountincart);
            itemphoto= itemView3.findViewById(R.id.itempictureincart);
            itembackground= itemView3.findViewById(R.id.backgrounditemincart);

            item_incart =itemView3.findViewById(R.id.item_incart);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
        }
    }

}
