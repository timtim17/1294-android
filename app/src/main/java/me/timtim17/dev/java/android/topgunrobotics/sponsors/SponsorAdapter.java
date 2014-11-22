package me.timtim17.dev.java.android.topgunrobotics.sponsors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import me.timtim17.dev.java.android.topgunrobotics.R;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {
    private List<Sponsor> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mCardView;
        public ViewHolder(View v){
            super(v);
            mCardView = v;
        }
    }

    public SponsorAdapter(List<Sponsor> dataset) {
        mDataset = dataset;
    }

    @Override
    public SponsorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_list, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sponsor currentSponsor = mDataset.get(position);
        ImageView imageView = (ImageView) holder.mCardView.findViewById(R.id.sponsorImage);
        imageView.setImageResource(currentSponsor.getLogo());
        imageView.setContentDescription(currentSponsor.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
