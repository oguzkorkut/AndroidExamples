package com.okorkut.onlinequizapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.okorkut.onlinequizapp.interfaces.ItemClickListener;
import com.okorkut.onlinequizapp.model.Category;
import com.okorkut.onlinequizapp.viewHolder.CategoryViewHolder;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    View myFragment;
    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference categories;

    public static CategoryFragment newInstance(){
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("APP_LOG", "CategoryFragment -> onCreate begins");
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Category");

        Log.i("APP_LOG", "CategoryFragment -> onCreate complated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("APP_LOG", "CategoryFragment -> onCreateView begins");

        myFragment = inflater.inflate(R.layout.fragment_category, container, false);

        listCategory = myFragment.findViewById(R.id.listCategory);
        listCategory.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

        loadCategories();

        Log.i("APP_LOG", "CategoryFragment -> onCreateView complated");

        return myFragment;
    }

    private void loadCategories() {

        Log.i("APP_LOG", "CategoryFragment -> loadCategories begins");

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories) {

            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, final Category model, int position) {
                Log.i("APP_LOG", "CategoryFragment -> loadCategories-FirebaseRecyclerAdapter begins");

                viewHolder.category_name.setText(model.getName());
                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.category_image);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(),String.format("%s|%s", adapter.getRef(position).getKey(),model.getName()),Toast.LENGTH_SHORT).show();
                    }
                });

                Log.i("APP_LOG", "CategoryFragment -> loadCategories-FirebaseRecyclerAdapter complated");
            }
        };

        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);

        Log.i("APP_LOG", "CategoryFragment -> loadCategories complated");
    }
}
