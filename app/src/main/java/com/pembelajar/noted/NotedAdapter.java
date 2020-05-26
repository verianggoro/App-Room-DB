package com.pembelajar.noted;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pembelajar.noted.model.Noted;

import java.util.List;

public class NotedAdapter extends RecyclerView.Adapter<NotedAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private List<Noted> allNoted;
    private onItemCallback itemCallback;

    public interface onItemCallback{
        void itemClicked(Noted noted);
    }

    public void setOnItemClickListener(onItemCallback onItemCallback){
        this.itemCallback = onItemCallback;
    }

    public NotedAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NotedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_noted, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotedAdapter.ViewHolder holder, final int position) {
        if (allNoted != null){
            final Noted noted = allNoted.get(position);
            holder.time.setText(noted.getTimeNoted());
            holder.title.setText(noted.getTitleNote());
            holder.itemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemCallback.itemClicked(allNoted.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (allNoted != null){
            return allNoted.size();
        }else{
            return 0;
        }
    }

    public void setNoted(List<Noted> noted){
        allNoted = noted;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, time;
        private CardView itemCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.txt_time);
            title = itemView.findViewById(R.id.txt_title_noted);
            itemCard = itemView.findViewById(R.id.item_noted_card);
        }
    }

}
