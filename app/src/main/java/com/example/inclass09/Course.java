package com.example.inclass09;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CourseTable")
public class Course {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String courseNumber;

    @ColumnInfo
    public String courseName;

    @ColumnInfo
    public String courseHours;

    @ColumnInfo
    public String courseGrade;

    public Course(long id, String courseNumber, String courseName, String courseHours, String courseGrade) {
        this.id = id;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseHours = courseHours;
        this.courseGrade = courseGrade;
    }

    public Course(String courseNumber, String courseName, String courseHours, String courseGrade) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseHours = courseHours;
        this.courseGrade = courseGrade;
    }

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(String courseHours) {
        this.courseHours = courseHours;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseNumber='" + courseNumber + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseHours='" + courseHours + '\'' +
                ", courseGrade='" + courseGrade + '\'' +
                '}';
    }
}
