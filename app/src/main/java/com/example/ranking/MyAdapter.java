package com.example.ranking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
private Context context;
private ArrayList<Item> itemList1 ;



    public MyAdapter(Context context) {
        this.context=context;
        this.itemList1= new ArrayList();



    }

    public ArrayList<Item> getItemList1() {
        return itemList1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.student_layout,viewGroup,false);


        return new MyViewHolder(view) ;
    }


    @Override


    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Item item= (Item) itemList1.get(i);
        myViewHolder.textView1.setText(item.getName());
    myViewHolder.textView2.setText(Integer.toString(item.getMarks()));
    myViewHolder.textView3.setText(Integer.toString(item.getRank()));



    }


    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public void updateList() {
       notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1,textView2,textView3,textView4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1=(TextView)itemView.findViewById(R.id.textView1);
            textView2=(TextView)itemView.findViewById(R.id.textView2);
            textView3=(TextView)itemView.findViewById(R.id.textView3);

        }
    }
}
