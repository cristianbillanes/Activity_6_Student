package com.example.student;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOStudent {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceStudent;
    private List<Student> students = new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<Student> students, List<String> keys);
        void DateIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();
    }
    public DAOStudent()
    {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceStudent = mDataBase.getReference("Student1");
    }
    public void readStudent(final DataStatus dataStatus){
        mReferenceStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 students.clear();
                 List<String> keys = new ArrayList<>();
                 for(DataSnapshot keyNods: snapshot.getChildren()){
                    keys.add(keyNods.getKey());
                    Student student = keyNods.getValue(Student.class);
                    students.add(student);
                 }
                 dataStatus.DataIsLoaded(students,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addstudent(String ID,Student student, final DataStatus dataStatus){
        mReferenceStudent.child(ID).setValue(student)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DateIsInserted();
                    }
                });
    }
    public void updateStudent(String key, Student student, final DataStatus dataStatus){
        mReferenceStudent.child(key).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deleteStudent(String key,final DataStatus dataStatus){
        mReferenceStudent.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }

}
