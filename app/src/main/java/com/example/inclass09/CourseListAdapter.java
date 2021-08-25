package com.example.inclass09;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    ArrayList<Course> courses;
    AdapterInterface mListener;

    public CourseListAdapter(ArrayList<Course> courses, AdapterInterface adapterInterface) {
        this.courses = courses;
        mListener = adapterInterface;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_layout, parent, false);
        CourseViewHolder viewHolder = new CourseViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseNumberTextView.setText(course.getCourseNumber());
        holder.courseNameTextView.setText(course.getCourseName());
        holder.creditHoursTextView.setText(course.getCourseHours() + " Credit Hours");
        holder.gradeValueTextView.setText(course.getCourseGrade());

        holder.deleteCourseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mListener.deleteCourse(course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        TextView courseNumberTextView, courseNameTextView, creditHoursTextView, gradeValueTextView;
        ImageView deleteCourseImageView;
        AdapterInterface mListener;

        public CourseViewHolder(@NonNull View itemView, AdapterInterface adapterInterface) {
            super(itemView);
            mListener = adapterInterface;
            courseNumberTextView = itemView.findViewById(R.id.courseNumberTextView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            creditHoursTextView = itemView.findViewById(R.id.creditHoursTextView);
            gradeValueTextView = itemView.findViewById(R.id.gradeValueTextView);
            deleteCourseImageView = itemView.findViewById(R.id.deleteCourseImageView);
        }
    }

    interface AdapterInterface{
        void deleteCourse(Course course);
    }
}
