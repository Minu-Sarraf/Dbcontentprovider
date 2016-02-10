package com.examples.user.dbcontentprovider;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by minu on 1/17/2016.
 */

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Datalist> list;
    int a = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Datalist> list) {
        this.list = list;

    }
public void refresh(ArrayList<Datalist>list){
    this.list=list;
    notifyDataSetChanged();

}
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final TextView title = (TextView) holder.view.findViewById(R.id.textView2);
       // final TextView date = (TextView) holder.view.findViewById(R.id.textView3);
        //ImageView image2=(ImageView)holder.view.findViewById(R.id.imageView);
        title.setText((list).get(position).getTitle());

      // date.setText((list).get(position).getDate());

        //image2.setImageResource((pojos).get(position).getimage());


        // Return the size of your dataset (invoked by the layout manager)



    }

    @Override
    public int getItemCount() {
        if(list==null) {
            return 0;
        }else{
            return list.size();
        }
        //return list.size();
    }

}