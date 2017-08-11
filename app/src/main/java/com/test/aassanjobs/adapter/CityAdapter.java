package com.test.aassanjobs.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.aassanjobs.R;
import com.test.aassanjobs.model.City;
import com.test.aassanjobs.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<City> cityList = new ArrayList<>();

    public CityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_city_list, parent, false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        if(holder1 instanceof CityHolder) {
            CityHolder holder = (CityHolder) holder1;
            City city = cityList.get(position);
            holder.txtCity.setText(city.getName());
        }
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class CityHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtCity)
        TextView txtCity;

        @BindView(R.id.rootLayout)
        CardView rootLayout;

        public CityHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    City city = cityList.get(getAdapterPosition());
                    CommonUtils.showToast(context, city.getName());
                }
            });
        }
    }
}
