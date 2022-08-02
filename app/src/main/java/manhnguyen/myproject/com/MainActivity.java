package manhnguyen.myproject.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DialerFilter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewStudent;
    StudentAdapter adapter;
    ArrayList<Student> studentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        adapter = new StudentAdapter(this, R.layout.student_row, studentArrayList);
        listViewStudent.setAdapter(adapter);

    }

    // MAKE MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // TOUCH MENU TO CREATE NEW STUDENT
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemAddNewStudent) {
            DiaLogAddStudent();
        }
        return super.onOptionsItemSelected(item);
    }

    // MAKE DIALOG TO CREATE
    private void DiaLogAddStudent() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_add_new_student);
        EditText addName = (EditText) dialog.findViewById(R.id.editTextTextNameAdd);
        EditText addClass = (EditText) dialog.findViewById(R.id.editTextTextClazzAdd);
        Button btnAdd = (Button) dialog.findViewById(R.id.buttonAdd);
        Button btnCancel = (Button) dialog.findViewById(R.id.buttonCancel);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = studentArrayList.size();
                String studentName = addName.getText().toString().trim();
                String studentClass = addClass.getText().toString().trim();

                if (studentName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please , enter student name", Toast.LENGTH_SHORT).show();
                } else if (studentClass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please, enter student class", Toast.LENGTH_SHORT).show();
                } else {
                    studentArrayList.add(new Student(id++, studentName, studentClass, R.drawable.student1));
                    Toast.makeText(MainActivity.this, "Adding student successful", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //MAKE DIALOG EDIT STUDENT
    public void DialogEditStudent(String name, String clazz, int id) {
        Dialog dialogEdit = new Dialog(this);
        dialogEdit.setContentView(R.layout.dialog_update_student);
        dialogEdit.setCanceledOnTouchOutside(false);
        EditText editName = (EditText) dialogEdit.findViewById(R.id.editTextTextNameEdit);
        EditText editClass = (EditText) dialogEdit.findViewById(R.id.editTextTextClassEdit);
        Button editStudent = (Button) dialogEdit.findViewById(R.id.buttonEdit);
        Button cancelEditStudent = (Button) dialogEdit.findViewById(R.id.buttonCancelEdit);
        editName.setText(name);
        editClass.setText(clazz);
        cancelEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEdit.dismiss();
            }
        });

        editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameEdit = editName.getText().toString().trim();
                String classEdit = editClass.getText().toString().trim();
                if (nameEdit.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please, Enter student name to edit", Toast.LENGTH_SHORT).show();
                } else if (classEdit.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please , Enter student class to edit", Toast.LENGTH_SHORT).show();
                } else {
                    studentArrayList.get(id - 1).setName(nameEdit);
                    studentArrayList.get(id - 1).setClazz(classEdit);
                    studentArrayList.get(id - 1).setId(id);
                    studentArrayList.get(id - 1).setImage(R.drawable.student1);
                    adapter.notifyDataSetChanged();
                    dialogEdit.dismiss();
                    Toast.makeText(MainActivity.this, "Edit student successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogEdit.show();

    }

    //     Dialog Delete Student
    public void DialogDeleteStudent(int id, String name, String clazz) {
        Dialog dialogDelete = new Dialog(this);
        dialogDelete.setContentView(R.layout.dialog_delete_student);
        dialogDelete.setCanceledOnTouchOutside(false);
        Button btnDeleteStudent = (Button) dialogDelete.findViewById(R.id.buttonDeleteStudent);
        Button btnCancelDeleteStudent = (Button) dialogDelete.findViewById(R.id.buttonCancelDeleteStudent);
        TextView deleteName = (TextView) dialogDelete.findViewById(R.id.textViewStudentName);
        TextView deleteClass = (TextView) dialogDelete.findViewById(R.id.textViewStudentClass);
        deleteName.setText(name);
        deleteClass.setText(clazz);
        btnCancelDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDelete.dismiss();
            }
        });
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete student: '" + name + "'?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogDelete.dismiss();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        studentArrayList.remove(id-1);
                        adapter.notifyDataSetChanged();
                        dialogDelete.dismiss();
                        Toast.makeText(MainActivity.this, "Delete Student Successful!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

        dialogDelete.show();
    }


    private void AnhXa() {

        listViewStudent = (ListView) findViewById(R.id.listviewStudent);
        studentArrayList = new ArrayList<>();
        //add student
        studentArrayList.add(new Student(1, "A", "12", R.drawable.student1));
        studentArrayList.add(new Student(2, "B", "12", R.drawable.student1));
        studentArrayList.add(new Student(3, "C", "12", R.drawable.student1));
        studentArrayList.add(new Student(4, "D", "12", R.drawable.student1));
        studentArrayList.add(new Student(5, "E", "12", R.drawable.student1));
        studentArrayList.add(new Student(6, "F", "12", R.drawable.student1));
        studentArrayList.add(new Student(7, "G", "12", R.drawable.student1));
    }

}