package com.example.inclass09;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;


public class AddCourseFragment extends Fragment {

    String courseNumber, courseName, courseHours, courseGrade;
    EditText courseNumberEditText, courseNameEditText, courseHoursEditText;
    RadioGroup radioGroup;
    AddCourseInterface mListener;

    public AddCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCourseInterface){
            mListener = (AddCourseInterface) context;
        }
        else {
            throw new RuntimeException(getContext().toString() + " must implement AddCourseInterface");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);

        getActivity().setTitle(getResources().getString(R.string.addCourseTitle));

        courseNumberEditText = view.findViewById(R.id.courseNumberEditText);
        courseNameEditText = view.findViewById(R.id.courseNameEditText);
        courseHoursEditText = view.findViewById(R.id.courseHoursEditText);
        radioGroup = view.findViewById(R.id.radioGroup);

        view.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseNumber = courseNumberEditText.getText().toString();
                courseName = courseNameEditText.getText().toString();
                courseHours = courseHoursEditText.getText().toString();

                int checkedRadio = radioGroup.getCheckedRadioButtonId();
                switch (checkedRadio){
                    case R.id.gradeA: courseGrade = "A"; break;
                    case R.id.gradeB: courseGrade = "B"; break;
                    case R.id.gradeC: courseGrade = "C"; break;
                    case R.id.gradeD: courseGrade = "D"; break;
                    case R.id.gradeF: courseGrade = "F"; break;
                    default: courseGrade = ""; break;
                }

                if (courseNumber.isEmpty() || courseName.isEmpty() || courseHours.isEmpty() || courseGrade.isEmpty()){
                    showAlertDialog(getResources().getString(R.string.mandatoryFields));
                }
                else if (courseHours.length() > 2 || Integer.parseInt(courseHours) > 6){
                    showAlertDialog(getResources().getString(R.string.extremeHours));
                }
                else if (Integer.parseInt(courseHours) <= 0){
                    showAlertDialog(getResources().getString(R.string.invalidHours));
                }
                else {
                    mListener.addNewCourse(courseNumber, courseName, courseHours, courseGrade);
                }
            }
        });


        view.findViewById(R.id.cancelTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelNewCourse();
            }
        });

        return view;
    }


    void showAlertDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    interface AddCourseInterface{
        void addNewCourse(String number, String name, String hours, String grade);
        void cancelNewCourse();
    }

}