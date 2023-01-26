package com.status.alok.Fragment;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.status.alok.R;

public class Setting extends android.support.v4.app.Fragment {

    Button rateus ;
    Button how ;
   // Button privacy ;
    Dialog HelpPopUp, rateUsPopUp;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.setting, container, false);
       /* recyclerView =  v.findViewById(R.id.picturesRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(30);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        gLay = new GridLayoutManager(getContext(),3);
        tv= v.findViewById(R.id.statTxt2);
        cryingEmoji = v.findViewById(R.id.cryingEmoji);
        recyclerView.setLayoutManager(gLay);
        muList= new ArrayList<String>();
        fetchImages();

        mReAdapter = new myAdapter((ArrayList<String>) muList,getContext());
        recyclerView.setAdapter(mReAdapter);*/
rateus = v.findViewById(R.id.rateUs);
how = v.findViewById(R.id.how);
//privacy = v.findViewById(R.id.privacypolicy);
final String storeLink = "https://play.google.com/store/apps/details?id=";

rateus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse(storeLink + v.getContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(storeLink + v.getContext().getPackageName())));
        }
    }
});
how.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        HelpPopUp = new Dialog(getContext());
        HelpPopUp.setContentView(R.layout.help_pop_up);
        ImageView ClosePopup = HelpPopUp.findViewById(R.id.ClosePopUp);

        ClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpPopUp.dismiss();
            }
        });
        HelpPopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        HelpPopUp.show();
    }
});
        return v;
    }

/*
privacy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});
*/

    @Override
    public void onResume() {
        super.onResume();

    }

















    @Override
    public void onStart() {
        super.onStart();
    }









}
