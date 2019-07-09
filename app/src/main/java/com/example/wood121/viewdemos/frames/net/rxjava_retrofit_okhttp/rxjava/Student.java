package com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.rxjava;

/**
 * @date: 2019/7/8
 * @version:
 * @author: liuzhengling
 * @des:
 */
class Student {
    private int id;
    private String Name;
    private Course course;

    static class Course {
        private String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student(int id, String name, Course course) {
        this.id = id;
        Name = name;
        this.course = course;
    }
}


