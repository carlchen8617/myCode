package au.edu.swin.sdmd.suncalculatorjava;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<sunRiseAndSet> sunList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView day, rise, set;

        public ViewHolder(View view) {
            super(view);
            day = (TextView) view.findViewById(R.id.day);
            rise = (TextView) view.findViewById(R.id.srise);
            set =  view.findViewById(R.id.sset);
        }
    }

    public myAdapter(List<sunRiseAndSet> sunList) {
        this.sunList = sunList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapt_report, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        sunRiseAndSet srs = sunList.get(position);


        holder.day.setText(srs.getDay());
        holder.rise.setText(srs.getRise());
        holder.set.setText(srs.getSet());
    }

    public int getItemCount() {
        return sunList.size();
    }



}
