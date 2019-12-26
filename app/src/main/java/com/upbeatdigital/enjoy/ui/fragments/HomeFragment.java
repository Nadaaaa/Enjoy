package com.upbeatdigital.enjoy.ui.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.upbeatdigital.enjoy.R;
import com.upbeatdigital.enjoy.adapters.SliderAdapter;
import com.upbeatdigital.enjoy.adapters.VideoAdapter;
import com.upbeatdigital.enjoy.api.apiutils.Retrofit;
import com.upbeatdigital.enjoy.api.responsemodels.ResponseGetHome;
import com.upbeatdigital.enjoy.databinding.FragmentHomeBinding;
import com.upbeatdigital.enjoy.models.Slider;
import com.upbeatdigital.enjoy.models.Video;
import com.upbeatdigital.enjoy.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements VideoAdapter.ListItemClickListener, SliderAdapter.ListItemClickListener {

    private Disposable disposable;
    private List<Video> videoList;
    private List<Slider> sliderList;
    private VideoAdapter videoAdapter;
    private SliderAdapter sliderAdapter;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        videoList = new ArrayList<>();
        videoAdapter = new VideoAdapter(getContext(), videoList, this);
        binding.rvVideos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvVideos.setAdapter(videoAdapter);

        sliderList = new ArrayList<>();
        sliderAdapter = new SliderAdapter(getContext(), sliderList, this);
        binding.rvSlider.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.rvSlider.setAdapter(sliderAdapter);

        callHomeApi();

        return binding.getRoot();
    }

    private void callHomeApi() {
        Retrofit.getService(Constants.BaseUrl)
                .getHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseGetHome>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseGetHome responseGetHome) {
                        if (responseGetHome.isSuccess()) {
                            videoList.addAll(responseGetHome.getData().getResponseVideo().getVideos());
                            videoAdapter.notifyDataSetChanged();

                            sliderList.addAll(responseGetHome.getData().getSliderList());
                            sliderAdapter.notifyDataSetChanged();
                        } else
                            Toast.makeText(getActivity(), responseGetHome.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "onError: getHome " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {

    }
}
