package com.example.ccl.myapplication_1;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;
    private List<Recipe> mRecipeList = new ArrayList<>();
    boolean hasDescShown = false;

    public RecipeAdapter(Context c){
        mContext = c;
    }

    // Provide a reference to the views for each data item
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        int mId;
        CardView mCardView;
        TextView mName;
        TextView mDescription;
        TextView mCategory;
        ImageView mDishPhoto;

        RecipeViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.recipe_card_view);
            mName = itemView.findViewById(R.id.name_text_view);
            mDescription = itemView.findViewById(R.id.description_text_view);
            mCategory = itemView.findViewById(R.id.dish_category_text_view);
            mDishPhoto = itemView.findViewById(R.id.thumb_nail);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecipeAdapter(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RecipeViewHolder rvh = new RecipeViewHolder(v);
        return rvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {
        // get element from recipeList at this position and replace the contents of the view with that element
        holder.mName.setText(mRecipeList.get(position).getName());
        holder.mDescription.setText(mRecipeList.get(position).getDescription());
        holder.mCategory.setText(mRecipeList.get(position).getCategory());
        holder.mDishPhoto.setImageResource(mRecipeList.get(position).getImageResourceId());
//        Log.v("RecipeAdapter", mRecipeList.get(position).getName());
//        Log.v("RecipeAdapter", mRecipeList.get(position).getDescription());
//        Log.v("RecipeAdapter", String.valueOf(mRecipeList.get(position).getImageResourceId()));

        holder.mDishPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("RecipeAdapter", "item: " + position + "\nName: " +
                        mRecipeList.get(position).getName() + "\nRecipe id: " +
                        mRecipeList.get(position).getRecipe_id()
                );
            }
        });

        holder.mDishPhoto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!hasDescShown) {
                holder.mDescription.setVisibility(View.VISIBLE);
                hasDescShown = true;
                }
                else {
                    holder.mDescription.setVisibility(View.GONE);
                    hasDescShown = false;
                }
                return true;
            }
        });
    }

    // Return the size of the recipeList
    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
