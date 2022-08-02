package manhnguyen.myproject.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    MainActivity context;
    int layout;
    List<Student> studentList;

    public StudentAdapter(MainActivity context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView image;
        TextView name, clazz;
        ImageButton btnEdit, btnDelete;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            holder.name = (TextView) view.findViewById(R.id.textViewName);
            holder.clazz = (TextView) view.findViewById(R.id.textViewClass);
            holder.image = (ImageView) view.findViewById(R.id.imageViewStudent);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Student student = studentList.get(i);
        // gắn giá trị
        holder.name.setText(student.getName());
        holder.clazz.setText(student.getClazz());
        holder.image.setImageResource(student.getImage());
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_scale);
        view.findViewById(R.id.imageViewStudent).startAnimation(animation);
        holder.btnEdit = (ImageButton) view.findViewById(R.id.imageButtonEdit);
        holder.btnDelete = (ImageButton) view.findViewById(R.id.imageButtonDelete);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogEditStudent(student.getName(), student.getClazz(), student.getId());
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           context.DialogDeleteStudent(student.getId(),student.getName(),student.getClazz());
            }
        });
        return view;
    }
}
