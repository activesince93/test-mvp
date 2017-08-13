package com.test.aassanjobs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.test.aassanjobs.adapter.CityAdapter;
import com.test.aassanjobs.model.City;
import com.test.aassanjobs.presenter.ICityFilterPresenter;
import com.test.aassanjobs.presenter.ICityPresenter;
import com.test.aassanjobs.presenter.MainPresenter;
import com.test.aassanjobs.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class MainActivity extends AppCompatActivity implements ICityPresenter, ICityFilterPresenter {

    @BindView(R.id.edtTxtSearch)
    EditText edtTxtSearch;

    @BindView(R.id.recyclerViewCityList)
    RecyclerView recyclerViewCityList;

    @BindView(R.id.txtEmptyView)
    TextView txtEmptyView;

    private MainPresenter mainPresenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        mainPresenter = new MainPresenter();

        initViews();

        mainPresenter.provideCityList(this, context);
    }

    private void initViews() {
        recyclerViewCityList.setLayoutManager(new LinearLayoutManager(this));

        edtTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mainPresenter.filterCities(charSequence.toString(), (ICityFilterPresenter) context);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setRecyclerViewAdapter(List<City> cityList) {
        if(cityList.size() > 0) {
            CityAdapter adapter = new CityAdapter(context, cityList);
            recyclerViewCityList.setAdapter(adapter);

            recyclerViewCityList.setVisibility(View.VISIBLE);
            txtEmptyView.setVisibility(View.GONE);
        } else {
            recyclerViewCityList.setVisibility(View.GONE);
            txtEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCityResponse(List<City> cityList) {
        CommonUtils.showLog(getClass().getSimpleName(), "onCityResponse()\n" + cityList.toString());
        setRecyclerViewAdapter(cityList);
    }

    @Override
    public void onCityError(String message) {
        CommonUtils.showLog(getClass().getSimpleName(), "onCityError()");
        CommonUtils.showToast(context, getString(R.string.some_error_occurred));
    }

    @Override
    public void onCityFiltered(List<City> cityList) {
        CommonUtils.showLog(getClass().getSimpleName(), "onCityFiltered()\n" + cityList.toString());
        onCityResponse(cityList);
    }
}
