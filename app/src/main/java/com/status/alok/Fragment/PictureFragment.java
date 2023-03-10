package com.status.alok.Fragment;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.status.alok.Activity.PicDetail;
import com.status.alok.R;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class PictureFragment extends android.support.v4.app.Fragment
{


    public PictureFragment() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    RecyclerView.Adapter mReAdapter;
    GridLayoutManager gLay;
    TextView tv;
    ImageView cryingEmoji;
    List<String> muList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_picture, container, false);
        recyclerView =  v.findViewById(R.id.picturesRecView);
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
        recyclerView.setAdapter(mReAdapter);


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        muList.clear();
    fetchImages();
    mReAdapter.notifyDataSetChanged();
    }

    private void fetchImages() {

        try {
            String path = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/.Statuses";
            Log.d("test", "onStart: " + path);
            File dir = new File(path);
            File[] files = dir.listFiles();

            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            if(files.length>0) {
                recyclerView.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                cryingEmoji.setVisibility(View.GONE);
            }
            for (int i = 0; i < files.length; i++) {

                if(files[i].getName().endsWith(".jpg")||files[i].getName().endsWith(".png")){

                    muList.add(files[i].getAbsolutePath());

                }

            }



        }catch (Exception ex){
            Toast.makeText(getContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }


    }












    @Override
    public void onStart() {
        super.onStart();
           }

    public class myAdapter extends RecyclerView.Adapter<myAdapter.MyHolder>{

        List<String> muList = new ArrayList<String>();
        @Override
        public myAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pictures_template,parent,false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final myAdapter.MyHolder holder, int position) {
            final Uri iri = Uri.parse(muList.get(position));
            File f = new File(muList.get(position));

            Picasso.with(getContext()).load(f).centerCrop().networkPolicy(NetworkPolicy.OFFLINE).fit().into(holder.imgV, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Log.d("test", "onError: ");
                }
            });

            holder.imgB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    File f1,f2;
                    f1 = new File(iri.toString());
                    String fname = f1.getName();
                    f2 = new File(Environment.getExternalStorageDirectory()+"/WhatsAppStatus/Images/");
                    f2.mkdirs();

                    try {
                        FileUtils.copyFileToDirectory(f1,f2);
                        ContentValues values =new ContentValues();
                        values.put(MediaStore.Images.Media.DATE_TAKEN,System.currentTimeMillis());
                        values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
                        values.put(MediaStore.MediaColumns.DATA,f2.toString()+"/"+fname);
                        getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }finally {
                        Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();
                    }


                }
            });
        }

        @Override
        public int getItemCount() {
            return muList.size();
        }

        public myAdapter(ArrayList<String> mylist, Context context) {
            this.muList = mylist;
        }

        class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
            ImageView imgV;
            ImageButton imgB;
            FloatingActionButton fab1;
            public MyHolder(View itemView) {
                super(itemView);

                imgV = itemView.findViewById(R.id.imageView);
                imgB = itemView.findViewById(R.id.imgBdon);
               // fab1 = itemView.findViewById(R.id.bmb);
                imgV.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(),PicDetail.class);
                intent.putExtra("pos", "" + getAdapterPosition());
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Objects.requireNonNull(getActivity()), imgV, "imageViewTrans1");
                Objects.requireNonNull(getContext()).startActivity(intent, activityOptionsCompat.toBundle());
            }



        }

    }




}

