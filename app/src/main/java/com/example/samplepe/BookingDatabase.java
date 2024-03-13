package com.example.samplepe;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BookingEntity.class}, version = 1)
public abstract class BookingDatabase extends RoomDatabase {
    public abstract BookingDao bookingDao();

    private static BookingDatabase INSTANCE = null;

    public static BookingDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BookingDatabase.class) {
                INSTANCE = Room.databaseBuilder(context, BookingDatabase.class, "Booking_DB")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }

        return INSTANCE;
    }
}
