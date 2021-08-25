package com.example.inclass09;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class GradesFragment extends Fragment implements CourseListAdapter.AdapterInterface {

    TextView gpaValueTextView, hoursValueTextView;
    RecyclerView courseListRecyclerView;
    LinearLayoutManager layoutManager;
    GradesInterface mListener;
    CourseListAdapter adapter;
    ArrayList<Course> listOfCourses;
    private static final String ARG_COURSES = "ARG_COURSES";


    public GradesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static GradesFragment newInstance(ArrayList<Course> courses) {
        GradesFragment fragment = new GradesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_COURSES, courses);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listOfCourses = (ArrayList<Course>) getArguments().getSerializable(ARG_COURSES);
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof GradesInterface){
            mListener = (GradesInterface) context;
        }
        else {
            throw new RuntimeException(getContext().toString() + " must implement GradesInterface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grades, container, false);

        getActivity().setTitle(getResources().getString(R.string.gradesTitle));

        float hours = 0;
        float totalHours = 0;
        float creditPoints = 0;
        String grade;

        gpaValueTextView = view.findViewById(R.id.gpaValueTextView);
        hoursValueTextView = view.findViewById(R.id.hoursValueTextView);

        courseListRecyclerView= view.findViewById(R.id.courseListRecyclerView);
        courseListRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        courseListRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < listOfCourses.size(); i++){
            Course course = listOfCourses.get(i);
            hours = Float.parseFloat((course.getCourseHours()));
            totalHours += hours;
            grade = course.getCourseGrade();
            switch (grade){
                case "A": creditPoints += 4 * hours; break;
                case "B": creditPoints += 3 * hours; break;
                case "C": creditPoints += 2 * hours; break;
                case "D": creditPoints += hours; break;
                case "F": creditPoints += 0; break;
            }
        }

        if (totalHours == 0){
            creditPoints = 0;
        }
        else {
            creditPoints = creditPoints / totalHours;
        }

        if (creditPoints == 0 && totalHours == 0){
            gpaValueTextView.setText("");
            hoursValueTextView.setText("");
        }
        else {
            gpaValueTextView.setText(String.format("%.2f", creditPoints));
            hoursValueTextView.setText(String.valueOf(totalHours));
        }

        if (listOfCourses.size() != 0){
            adapter = new CourseListAdapter(listOfCourses, GradesFragment.this);
            courseListRecyclerView.setAdapter(adapter);
        }

        return view;
    }


//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.actionbar_menu, menu);
//        //getActivity().getMenuInflater().inflate(R.menu.actionbar_menu, menu);
//    }


    @Override
    public void deleteCourse(Course course) {
        mListener.delete(course);
    }

    interface GradesInterface{
        void delete(Course course);
    }
}