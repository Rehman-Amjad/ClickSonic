package com.rehman.clicksonic.Lists;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.rehman.clicksonic.Adapter.AdapterUsers;
import com.rehman.clicksonic.Adapter.FacebookAdapter;
import com.rehman.clicksonic.Adapter.YouTubeAdapter;
import com.rehman.clicksonic.Model.UserModel;
import com.rehman.clicksonic.Model.YouTubeModel;
import com.rehman.clicksonic.R;

import java.util.ArrayList;
import java.util.Objects;

public class YouTubeListActivity extends AppCompatActivity {
    CardView card_pending,card_approved,card_rejected;
    ImageView back_image;
    TextView totalCount,tv_status;
    RecyclerView recyclerView;
    YouTubeAdapter adapter;
    ArrayList<YouTubeModel> mDataList = new ArrayList<>();
    FirebaseAuth mAuth;
    String userUID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_list);
        mAuth = FirebaseAuth.getInstance();

        intiView();
        getYouTubeList();
        back_image.setOnClickListener(v -> { onBackPressed(); });

        card_pending.setOnClickListener(v -> {

            pendingOrders();
        });
        card_approved.setOnClickListener(v -> {
            approvedOrders();
        });
        card_rejected.setOnClickListener(v -> {
            rejectedOrders();
        });

    }

    private void rejectedOrders() {

        adapter = new YouTubeAdapter(this,mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("YouTube")
                .whereEqualTo("Status","rejected")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        mDataList.clear();
                        assert value != null;
                        for (DocumentChange documentChange : value.getDocumentChanges())
                        {
                            if (documentChange.getType() == DocumentChange.Type.ADDED){
                                mDataList.add(documentChange.getDocument().toObject(YouTubeModel.class));
                            }

                        }
                        adapter.notifyDataSetChanged();
                        totalCount.setText(String.valueOf(mDataList.size()));
                        tv_status.setText("Rejected Orders:");

                    }
                });

    }

    private void approvedOrders() {

        adapter = new YouTubeAdapter(this,mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("YouTube")
                .whereEqualTo("Status","approved")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        mDataList.clear();
                        assert value != null;
                        for (DocumentChange documentChange : value.getDocumentChanges())
                        {
                            if (documentChange.getType() == DocumentChange.Type.ADDED){
                                mDataList.add(documentChange.getDocument().toObject(YouTubeModel.class));
                            }

                        }
                        adapter.notifyDataSetChanged();
                        totalCount.setText(String.valueOf(mDataList.size()));
                        tv_status.setText("Approved Orders:");

                    }
                });

    }

    private void pendingOrders() {

        adapter = new YouTubeAdapter(this,mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("YouTube")
                .whereEqualTo("Status","pending")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        mDataList.clear();
                        assert value != null;
                        for (DocumentChange documentChange : value.getDocumentChanges())
                        {
                            if (documentChange.getType() == DocumentChange.Type.ADDED){
                                mDataList.add(documentChange.getDocument().toObject(YouTubeModel.class));
                            }

                        }
                        adapter.notifyDataSetChanged();
                        totalCount.setText(String.valueOf(mDataList.size()));
                        tv_status.setText("Pending Orders:");

                    }
                });

    }

    private void getYouTubeList() {

        adapter = new YouTubeAdapter(this,mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("YouTube")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        assert value != null;
                        for (DocumentChange documentChange : value.getDocumentChanges())
                        {
                            if (documentChange.getType() == DocumentChange.Type.ADDED){
                                mDataList.add(documentChange.getDocument().toObject(YouTubeModel.class));
                            }

                        }
                        adapter.notifyDataSetChanged();
                        totalCount.setText(String.valueOf(mDataList.size()));

                    }
                });

    }

    private void intiView() {
        back_image=findViewById(R.id.back_image);
//      TextView
        totalCount=findViewById(R.id.totalCount);
        tv_status=findViewById(R.id.tv_status);

        recyclerView=findViewById(R.id.youTube_recycler_view);
        //CardView
        card_pending=findViewById(R.id.card_pending);
        card_approved=findViewById(R.id.card_approved);
        card_rejected=findViewById(R.id.card_rejected);
    }
}