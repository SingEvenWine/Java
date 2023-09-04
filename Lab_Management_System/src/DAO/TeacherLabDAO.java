package DAO;

import Entity.Laboratory;
import Entity.Teacher;
import Entity.Teacher_lab;

public interface TeacherLabDAO {
    //添加老师-实验室关系
    void addTeacherLab(Teacher teacher, Laboratory labId, String begin_time, String end_time);
    //删除老师-实验室关系
    boolean deleteTeacherLab(int teacherId, int labId);
    //更新老师-实验室关系
    void updateTeacherLab(Teacher_lab teacher_lab);
    boolean isLabApply(int labId);
    boolean isLabApply(Teacher_lab teacher_lab);
}
