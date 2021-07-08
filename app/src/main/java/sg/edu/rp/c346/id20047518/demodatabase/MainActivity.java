package sg.edu.rp.c346.id20047518.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ArrayList<Task> taskList;
    ArrayAdapter<Task> aa;
    ListView lv;
    EditText etName;
    EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        lv = findViewById(R.id.lv);
        taskList = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, taskList);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etName.getText().toString(), etDate.getText().toString());

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<Task> data =db.getTasks();
                taskList.clear();
                taskList.addAll(data);
                aa.notifyDataSetChanged();
            }
        });

//        btnGetTasks.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // Create the DBHelper object, passing in the
//                // activity's Context
//                DBHelper db = new DBHelper(MainActivity.this);
//
//                // Insert a task
//                ArrayList<String> data = db.getTaskContent();
//                db.close();
//
//                String txt = "";
//                for (int i = 0; i < data.size(); i++) {
//                    Log.d("Database Content", i +". "+data.get(i));
//                    txt += i + ". " + data.get(i) + "\n";
//                }
//                tvResults.setText(txt);
//                taskList.addAll(data);
//                aa.notifyDataSetChanged();
//            }
//        });
    }
}