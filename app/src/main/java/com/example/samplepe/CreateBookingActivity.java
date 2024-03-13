package com.example.samplepe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class CreateBookingActivity extends AppCompatActivity {
    TextWatcher updateTotal= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            updateTotal();
        }
    };

    SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");

    BookingDao bookingDao;

    EditText customerNameTe,serviceNameTe, priceTe, quantityTe;

    TextView totalValueTv;

    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_booking);
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

        bookingDao = BookingDatabase.getInstance(this).bookingDao();

        customerNameTe = findViewById(R.id.customerNameTe);
        serviceNameTe = findViewById(R.id.serviceNameTe);
        priceTe = findViewById(R.id.priceTe);
        quantityTe = findViewById(R.id.quantityTe);
        totalValueTv = findViewById(R.id.totalValueTv);
        saveBtn = findViewById(R.id.saveBtn);
        priceTe.addTextChangedListener(updateTotal);
        quantityTe.addTextChangedListener(updateTotal);
        saveBtn.setOnClickListener(v -> save());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    void save() {
        String cName = customerNameTe.getText().toString();
        String sName = serviceNameTe.getText().toString();
        String priceStr = priceTe.getText().toString();
        String quantityStr = quantityTe.getText().toString();
        if(priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int quantity = Integer.parseInt(quantityStr);
        if(cName.isEmpty() || sName.isEmpty() || price <= 0 || quantity < 0) {
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String now = dt1.format(Date.from(Instant.now()));
            BookingEntity booking= new BookingEntity(0, cName, now, price, quantity, sName);
            bookingDao.insert(booking);
            Toast.makeText(this, "Save successful!", Toast.LENGTH_SHORT).show();

            customerNameTe.setText("");
            serviceNameTe.setText("");
            priceTe.setText("");
            quantityTe.setText("");
            totalValueTv.setText("");
        } else {
            Toast.makeText(this, "android 9 pls", Toast.LENGTH_SHORT).show();
        }
    }

    void updateTotal() {
        try {
            double price = Double.parseDouble(priceTe.getText().toString());
            int quantity = Integer.parseInt(quantityTe.getText().toString());

            double totalValue = price * quantity;
            totalValueTv.setText("" + totalValue);
        } catch (Throwable e) {
            totalValueTv.setText("");
        }
 
    }
}