package com.example.inclass09;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface CourseDAO {

    @Insert
    void insertAll(Course... courses);

    @Delete
    void delete(Course course);

    @Query("SELECT * from CourseTable WHERE id = :id")
    Course findByID(long id);

    @Query("SELECT * from CourseTable")
    List<Course> getAll();
}
