package com.example.student;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclearView_Config {
    private Context mContext;
    private StudentAdaptor mStudentAdaptor;

    public void setConfig(RecyclerView recyclerView, Context context, List<Student> students, List<String> keys){
        mContext = context;
        mStudentAdaptor = new StudentAdaptor(students, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mStudentAdaptor);
    }

    class StudentItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mAge;
        private TextView mCourse;
        private TextView mYear;
        private TextView mID;

        private  String key;

        public StudentItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
             inflate(R.layout.student_list_item, parent,false));
            mID = (TextView) itemView.findViewById(R.id.ID);
            mName = (TextView) itemView.findViewById(R.id.Name);
            mAge = (TextView) itemView.findViewById(R.id.Age);
            mCourse = (TextView) itemView.findViewById(R.id.Course);
            mYear = (TextView) itemView.findViewById(R.id.Year);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, Student_details.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Name",mName.getText().toString());
                    intent.putExtra("Age",mAge.getText().toString());
                    intent.putExtra("Course",mCourse.getText().toString());
                    intent.putExtra("Year",mYear.getText().toString());
                    mContext.startActivity(intent);
                }
            });

        }
        public void bind(Student student, String key){
            mID.setText(key);
            mName.setText(student.getName());
            mAge.setText(student.getAge());
            mCourse.setText(student.getCourse());
            mYear.setText(student.getYear());
            this.key = key;
        }
    }
    class StudentAdaptor extends RecyclerView.Adapter<StudentItemView>{
         private List<Student> mStudentList;
         private  List<String> mkeys;

        public StudentAdaptor(List<Student> mStudentList, List<String> mkeys) {
            this.mStudentList = mStudentList;
            this.mkeys = mkeys;
        }

        public StudentAdaptor() {
            super();
        }


        @NonNull
        @Override
        public StudentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StudentItemView(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull StudentItemView holder, int position) {
            holder.bind(mStudentList.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mStudentList.size();
        }
    }
}
