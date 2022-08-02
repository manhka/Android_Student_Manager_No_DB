package manhnguyen.myproject.com;

public class Student {
    private int id;
    private String name;
    private String clazz;
    private int image;

    public Student() {
    }

    public Student(int id, String name, String clazz, int image) {
        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
