package DAO;

import Entity.Teacher;

public interface TeacherDAO {
    void addTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    Teacher getTeacher(int id);
    Teacher getTeacher(String teacherId);
}
