package com.example.student;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText ID,Name,Age,Year;
    private Spinner Course;
    String key = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ID = findViewById(R.id.ID);
        Name = findViewById(R.id.Name);
        Age = findViewById(R.id.Age);
        Course = findViewById(R.id.Course);
        Year = findViewById(R.id.Year);
    }
    public void Add (View v) {
        Student student = new Student();
        student.setName(Name.getText().toString());
        student.setAge(Age.getText().toString());
        student.setCourse(Course.getSelectedItem().toString());
        student.setYear(Year.getText().toString());
        new DAOStudent().addstudent(ID.getText().toString(),student, new DAOStudent.DataStatus() {
            @Override
            public void DataIsLoaded(List<Student> students, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
                Toast.makeText(MainActivity.this, "The Student record has been inserted successfuly", Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
    public void Show(View v){
        Intent intent = new Intent(this, View_Student.class);
        startActivity(intent);
    }
}