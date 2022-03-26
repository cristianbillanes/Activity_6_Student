package com.example.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;


import java.util.List;

public class View_Student extends AppCompatActivity {
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        mRecycleView = (RecyclerView) findViewById(R.id.StudentRecord);
        new DAOStudent().readStudent(new DAOStudent.DataStatus() {
            @Override
            public void DataIsLoaded(List<Student> students, List<String> keys) {
                new RecyclearView_Config().setConfig(mRecycleView,View_Student.this,students, keys);
            }

            @Override
            public void DateIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
    public void Back(View v){
        finish();
    }
}