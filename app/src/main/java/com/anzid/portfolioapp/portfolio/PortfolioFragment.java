package com.anzid.portfolioapp.portfolio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amyu.stack_card_layout_manager.StackCardLayoutManager;
import com.anzid.portfolioapp.R;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.StaggeredGridLayoutManager.HORIZONTAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class PortfolioFragment extends Fragment implements PortfolioCallback {

    List<PortfolioItem> mdata;
    public RecyclerView rv_portfolio;
    public PortfolioAdapter portfolioAdapter ;



    public PortfolioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_portfolio = view.findViewById(R.id.rv_portfolio);
        // create a list of portfolio items
        mdata = new ArrayList<>();





        mdata.add(new PortfolioItem(R.drawable.project7));
        mdata.add(new PortfolioItem(R.drawable.project3));
        mdata.add(new PortfolioItem(R.drawable.project5));
        mdata.add(new PortfolioItem(R.drawable.project5));
        mdata.add(new PortfolioItem(R.drawable.project0));
        mdata.add(new PortfolioItem(R.drawable.project2));
        mdata.add(new PortfolioItem(R.drawable.project3));
        mdata.add(new PortfolioItem(R.drawable.project7));
        mdata.add(new PortfolioItem(R.drawable.project1));


        portfolioAdapter = new PortfolioAdapter(mdata,this);

        // setup grid recyclerview
        rv_portfolio.setLayoutManager(new OverLayCardLayoutManager());
        CardConfig.initConfig(requireContext());
        ItemTouchHelper.Callback callback = new RenRenCallback(rv_portfolio, portfolioAdapter, mdata);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv_portfolio);
//        PortfolioFragmentExtKt.init(this);
        rv_portfolio.setAdapter(portfolioAdapter);

        new Handler().postDelayed(() -> {
            portfolioAdapter.setMdata(mdata);
        }, 5000);

    }

    @Override
    public void onPortfolioItemClick(int pos) {

        //handle click listener event when portfolio item clicked

        // first we need to create a fragment bottom sheet instance

        PortfolioFragmentDetails portfolioFragmentDetails =
                new PortfolioFragmentDetails();

        // now we need to send portfolio item to portfolio dialog fragment
        // we will use bundle for that
        // also we need our portfolio item data class to implement serializable interface so we can send it

        Bundle bundle = new Bundle();
        bundle.putSerializable("object",mdata.get(pos));
        portfolioFragmentDetails.setArguments(bundle);

        // now let's open the portfolio bottom sheet fregment

        portfolioFragmentDetails.show(getActivity().getSupportFragmentManager(),"tagname");

        //now we done opening the bottom sheet let's test it out
        // ok everthing goes well
        // let's set the data in details fragment






    }
}
