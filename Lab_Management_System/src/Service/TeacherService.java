package Service;

import java.util.List;

import DAO.InstrumentDAOlmpl;
import DAO.LaboratoryDAOlmpl;
import DAO.TeacherDAOlmpl;
import DAO.TeacherLabDAOlmpl;
import Entity.Instrument;
import Entity.Laboratory;
import Entity.Teacher;
import Entity.Teacher_lab;

public class TeacherService {
    private TeacherDAOlmpl teacherDAOlmpl = new TeacherDAOlmpl();
    private LaboratoryDAOlmpl laboratoryDAOlmpl = new LaboratoryDAOlmpl();
    private InstrumentDAOlmpl instrumentDAOlmpl = new InstrumentDAOlmpl();
    private TeacherLabDAOlmpl teacherLabDAOlmpl = new TeacherLabDAOlmpl();

    //教师注册
    public Teacher register(String name, String teacherId, String password){
        if(teacherDAOlmpl.getTeacher(teacherId)!=null){
            System.out.println("该教师已注册");
            return null;
        }else{
            teacherDAOlmpl.addTeacher(new Teacher(name, teacherId, password));
            System.out.println("注册成功");
            return teacherDAOlmpl.getTeacher(teacherId);
        }
    }
    //教师登录
    public Teacher login(String teacherId, String password){
        if(teacherDAOlmpl.getTeacher(teacherId)==null){
            System.out.println("该教师未注册");
            return null;
        }else{
            Teacher teacher = teacherDAOlmpl.getTeacher(teacherId);
            if(teacher.getPassword().equals(password)){
                System.out.println("登录成功");
                return teacher;
            }else{
                System.out.println("密码错误");
                System.out.println("输入的密码：" + password);
                System.out.println("数据库中存储的密码：" + teacher.getPassword());
                return null;
            }
        }
    }
    //教师修改密码
    public void changePassword(String teacherId, String oldPassword, String newPassword){
        if(teacherDAOlmpl.getTeacher(teacherId)==null){
            System.out.println("该教师未注册");
            return;
        }else{
            Teacher teacher = teacherDAOlmpl.getTeacher(teacherId);
            if(teacher.getPassword().equals(oldPassword)){
                teacher.setPassword(newPassword);
                teacherDAOlmpl.updateTeacher(teacher);
                System.out.println("修改成功");
            }else{
                System.out.println("密码错误");
            }
        }
    }
    //教师查看实验室
    public void viewLaboratory(){
        List<Laboratory> laboratories = laboratoryDAOlmpl.getAllLaboratories();
        for (Laboratory laboratory : laboratories) {
            System.out.println(laboratory.getId() + " " + laboratory.getName() + " " + laboratory.getLocation() + " " + laboratory.getMaxNumberOfStudents());
        }
    }
    //教师查看仪器
    public void viewInstrument(){
        List<Instrument> instruments = instrumentDAOlmpl.getAllInstruments();
        for (Instrument instrument : instruments) {
            System.out.println(laboratoryDAOlmpl.getLaboratory(instrument.getLab_id()).getName()+" "+instrument.getId() + " " + instrument.getName() + " " + instrument.getDescription() + " " + instrument.isDamaged());
        }
    }
    //查看所有损坏仪器
    public void viewDamagedInstrument(){
        List<Instrument> instruments = instrumentDAOlmpl.getAllInstruments();
        for (Instrument instrument : instruments) {
            if(instrument.isDamaged()){
                System.out.println(laboratoryDAOlmpl.getLaboratory(instrument.getLab_id()).getName()+" "+instrument.getId() + " " + instrument.getName() + " " + instrument.getDescription() + " " + instrument.isDamaged());
            }
        }
    }

    public void viewInstrumentByLabId(int lab_id){
        List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByLab_id(lab_id);
        for (Instrument instrument : instruments) {
            System.out.println(laboratoryDAOlmpl.getLaboratory(instrument.getLab_id()).getName()+" "+instrument.getId() + " " + instrument.getName() + " " + instrument.getDescription() + " " + instrument.isDamaged());
        }
    }
    //教师根据实验室名称申请实验室
    public void applyLaboratory(Teacher teacher, int lab_id, String begin_time, String end_time){
        teacherLabDAOlmpl.addTeacherLab(teacher, laboratoryDAOlmpl.getLaboratory(lab_id), begin_time, end_time);
    }
    //教师申报实验室仪器损坏
    public void applyInstrumentDamaged(int instrument_id){
        Instrument instrument = instrumentDAOlmpl.getInstrument(instrument_id);
        instrument.setDamaged(true);
        instrumentDAOlmpl.updateInstrument(instrument);
    }
    public void viewLaboratoryOrder() {
        teacherLabDAOlmpl.viewTeacherLab();
    }
    //增加实验室
    public void addLaboratory(String name, String location, int maxNumberOfStudents){
        laboratoryDAOlmpl.addLaboratory(new Laboratory(name, location, maxNumberOfStudents));
    }
    //删除实验室
    public void deleteLaboratory(int id){
        laboratoryDAOlmpl.deleteLaboratory(laboratoryDAOlmpl.getLaboratory(id));
    }
    //修改实验室
    public void updateLaboratory(int id, String name, String location, int maxNumberOfStudents){
        Laboratory laboratory = laboratoryDAOlmpl.getLaboratory(id);
        laboratory.setName(name);
        laboratory.setLocation(location);
        laboratory.setMaxNumberOfStudents(maxNumberOfStudents);
        laboratoryDAOlmpl.updateLaboratory(laboratory);
    }
    //增加仪器
    public void addInstrument(int lab_id, String name, String description){
        instrumentDAOlmpl.addInstrument(new Instrument(name,description,lab_id));
    }
    //删除仪器
    public void deleteInstrument(int id){
        instrumentDAOlmpl.deleteInstrument(instrumentDAOlmpl.getInstrument(id));
    }
    //修改仪器
    public void updateInstrument(int id, String name, String description){
        Instrument instrument = instrumentDAOlmpl.getInstrument(id);
        instrument.setName(name);
        instrument.setDescription(description);
        instrumentDAOlmpl.updateInstrument(instrument);
    }

    public List<Teacher_lab> getLabApply(Teacher teacher) {
        return teacherLabDAOlmpl.getLabApply(teacher);
    }

    public static void main(String[] args) {
        TeacherService teacherService = new TeacherService();
        // teacherService.register("张三", "123", "123");
        // teacherService.login("123", "123");
        // teacherService.changePassword("123", "123", "1234");
        //teacherService.viewLaboratory();
        //teacherService.viewInstrument();
        teacherService.register("张三", "211210400209", "123456");
    }
    
    
}
