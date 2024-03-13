package com.example.samplepe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BookingListActivity extends AppCompatActivity {
    RecyclerView productListRv;
    Button createBtn;
    BookingDao bookingDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bookingDao = BookingDatabase.getInstance(this).bookingDao();

        productListRv = findViewById(R.id.productListRv);
        createBtn = findViewById(R.id.createBtn);
        createBtn.setOnClickListener(v -> openCreateActivity());

        updateList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateList();
    }

    void openCreateActivity() {
        Intent intent = new Intent(this, CreateBookingActivity.class);
        startActivity(intent);
    }

    void updateList() {
        BookingListAdapter adapter = new BookingListAdapter(this, bookingDao.selectAll());
        productListRv.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
        productListRv.setAdapter(adapter);
    }
}