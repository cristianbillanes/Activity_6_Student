package com.example.student;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class Student_details extends AppCompatActivity {
    private EditText Name,Age,Year;
    private Spinner Course;
    private String mname,mage,myear,mcourse,key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        key = getIntent().getStringExtra("key");
        mname = getIntent().getStringExtra("Name");
        mage = getIntent().getStringExtra("Age");
        myear = getIntent().getStringExtra("Year");
        mcourse = getIntent().getStringExtra("Course");
        Name = findViewById(R.id.Name);
        Age = findViewById(R.id.Age);
        Course = findViewById(R.id.Course);
        Year = findViewById(R.id.Year);
        Name.setText(mname);
        Age.setText(mage);
        Year.setText(myear);
        Course.setSelection(getIndex_SpinnerItem(Course,mcourse));
    }
    public void Back(View v){
        finish();
    }
    public void Update(View v){
        Student student = new Student();
        student.setName(Name.getText().toString());
        student.setAge(Age.getText().toString());
        student.setCourse(Course.getSelectedItem().toString());
        student.setYear(Year.getText().toString());
        new DAOStudent().updateStudent(key,student, new DAOStudent.DataStatus() {
            @Override
            public void DataIsLoaded(List<Student> students, List<String> keys) {}
            @Override
            public void DateIsInserted() { }
            @Override
            public void DataIsUpdated() {
                    Toast.makeText(Student_details.this,
                            "Student record has ben updated successfuly",
                            Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsDeleted() { }
        });
    }
    public void Delete(View v){
        new DAOStudent().deleteStudent(key, new DAOStudent.DataStatus() {
            @Override
            public void DataIsLoaded(List<Student> students, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {

            }

            @Override
            public void DataIsDeleted() {
                Toast.makeText(Student_details.this,
                        "Student record has ben deleted successfuly",
                        Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void DataIsUpdated() {

            }
        });
    }
    private int getIndex_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for(int i=0;i<spinner.getCount();i++){
            if(spinner.getItemAtPosition(i).equals(item)){
                index = 1;
                break;
            }

        }
        return index;
    }
}