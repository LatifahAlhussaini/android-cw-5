package com.example.cartoon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CartoonAdapter extends RecyclerView.Adapter {

    final LinearLayout LL ;
    ArrayList<Cartoon> C_Array;
    Context context;

    public CartoonAdapter(LinearLayout ll, ArrayList<Cartoon> c_Array, Context context) {
        LL = ll;
        C_Array = c_Array;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartoon_list_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder)holder).img.setImageResource(C_Array.get(position).getImage());
        ((ViewHolder)holder).name.setText(C_Array.get(position).getName());
        ((ViewHolder)holder).rating.setText(C_Array.get(position).getRating()+"");
        ((ViewHolder)holder).year.setText(C_Array.get(position).getYear()+"");

        ((ViewHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,Ditails.class);
                i.putExtra("Cartoon",C_Array.get(position));
                context.startActivity(i);

            }
        });


        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        ((ViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete?");

                builder.setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Cartoon delete = C_Array.get(position);
                      C_Array.remove(position);
                      notifyDataSetChanged();

                        Snackbar.make(LL,"Undo",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                C_Array.add(position,delete);
                                notifyDataSetChanged();
                                
                            }
                        }).show();
                    }
                });



                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return C_Array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView name;
        public TextView rating;
        public TextView year;
        public Button delete;
        public View view;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            img = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView);
            rating = itemView.findViewById(R.id.textView3);
            year = itemView.findViewById(R.id.textView5);
            delete = itemView.findViewById(R.id.delete);


        }
    }

}
