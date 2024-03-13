package com.example.samplepe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookingDetailActivity extends AppCompatActivity {

    BookingDao bookingDao;

    TextView customerNameTv,serviceNameTv, priceTv, quantityTv,totalValueDetailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Create Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        customerNameTv = findViewById(R.id.customerNameTv);
        serviceNameTv = findViewById(R.id.serviceNameTv);
        priceTv = findViewById(R.id.priceTv);
        quantityTv = findViewById(R.id.quanityTv);
        totalValueDetailTv = findViewById(R.id.totalValueDetailTv);


        bookingDao = BookingDatabase.getInstance(this).bookingDao();
        int id =  getIntent().getIntExtra("id", 0);
        BookingEntity entity = bookingDao.select(id);
        customerNameTv.setText(entity.getCustomerName());
        priceTv.setText("" + entity.getPrice());
        quantityTv.setText("" + entity.getPrice());
        totalValueDetailTv.setText("" + entity.getTotalValue());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}