package com.example.inclass09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCourseFragment.AddCourseInterface, GradesFragment.GradesInterface {

    CourseDatabase db;
    ArrayList<Course> courses;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, CourseDatabase.class, "Grades.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        courses = (ArrayList<Course>) db.courseDAO().getAll();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, GradesFragment.newInstance(courses))
                .commit();
    }


    @Override
    public void addNewCourse(String number, String name, String hours, String grade) {
        db.courseDAO().insertAll(new Course(number, name, hours, grade));
        courses = (ArrayList<Course>) db.courseDAO().getAll();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, GradesFragment.newInstance(courses))
                .commit();
    }


    @Override
    public void cancelNewCourse() {
        courses = (ArrayList<Course>) db.courseDAO().getAll();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, GradesFragment.newInstance(courses))
                .commit();
    }


    @Override
    public void delete(Course course) {
        db.courseDAO().delete(course);
        courses = (ArrayList<Course>) db.courseDAO().getAll();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, GradesFragment.newInstance(courses))
                .commit();
    }


    public void goToAddCourse() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new AddCourseFragment())
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addCourse:
                goToAddCourse();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}