package com.example.samplepe;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookingEntity entity);

    @Query("SELECT * FROM Booking")
    List<BookingEntity> selectAll();

    @Query("SELECT * FROM Booking p WHERE p.bookingId = :id")
    BookingEntity select(int id);
}
