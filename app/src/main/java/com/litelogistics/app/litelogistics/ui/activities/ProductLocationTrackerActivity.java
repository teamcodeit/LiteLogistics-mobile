package com.litelogistics.app.litelogistics.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.litelogistics.app.litelogistics.R;
import com.litelogistics.app.litelogistics.models.BaseModel;
import com.litelogistics.app.litelogistics.models.LocationDetail;
import com.litelogistics.app.litelogistics.network.LitePayService;
import com.litelogistics.app.litelogistics.network.RestClient;
import com.litelogistics.app.litelogistics.ui.base.BaseActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lekan Adigun on 7/21/2018.
 */

public class ProductLocationTrackerActivity extends BaseActivity implements OnMapReadyCallback {

    @BindView(R.id.edt_pfi)
    MaterialEditText mPfiEditText;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingUpPanelLayout;

    private GoogleMap mGoogleMap;
    private ProgressDialog progressDialog;
    private int retryCount = 0; //Optimization for a low network bandwidth. Retry request up to 5 times if retrofit called onFailure()

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_input_pfi);

        FragmentManager manager = getSupportFragmentManager();
        if (manager != null) {
            SupportMapFragment supportMapFragment = (SupportMapFragment)
                    manager.findFragmentById(R.id.support_map_fragment);
            if (supportMapFragment != null)
                supportMapFragment.getMapAsync(this);
        }

        slidingUpPanelLayout.setAnchorPoint(1.7f);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
    }

    @OnClick(R.id.btn_go) public void onGoClick() {

        String text = mPfiEditText.getText().toString().trim();
        hideKeyboard();
        if (text.isEmpty()) {
            toast("Enter PFI");
            return;
        }

        fetchLocationDetails(text);
    }

    @OnClick(R.id.panel_header) public void onPanelHeadClick() {
        if (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED) {
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
            slidingUpPanelLayout.setAnchorPoint(0.7f);
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        try {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }catch (Exception e) {
            //ignored
        }
    }

    private void fetchLocationDetails(final String pfi) {

        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }

        LitePayService service = RestClient.createService();
        service.get(pfi).enqueue(new Callback<BaseModel<LocationDetail>>() {
            @Override
            public void onResponse(Call<BaseModel<LocationDetail>> call, Response<BaseModel<LocationDetail>> response) {

                if (progressDialog != null)
                    progressDialog.cancel();

                if (response.isSuccessful()) {

                    BaseModel<LocationDetail> model = response.body();
                    if (model != null && model.status) {
                        showLocationOnMap(model.data);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseModel<LocationDetail>> call, Throwable t) {

                if (retryCount < 5) {
                    retryCount++;
                    fetchLocationDetails(pfi);
                    return;
                }

                if (progressDialog != null) {
                    progressDialog.cancel();
                    toast("Failed to fetch details. Are you sure you have a valid internet connection?");
                }
            }
        });
    }

    private void showLocationOnMap(LocationDetail data) {

        LatLng latLng = new LatLng(data.location_lat, data.location_lon);
        if (mGoogleMap != null) {
            mGoogleMap.addMarker(new MarkerOptions().position(latLng));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }
}
