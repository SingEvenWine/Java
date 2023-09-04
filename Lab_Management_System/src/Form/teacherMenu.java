package Form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.InstrumentDAOlmpl;
import DAO.LaboratoryDAOlmpl;
import DAO.TeacherDAOlmpl;
import DAO.TeacherLabDAO;
import DAO.TeacherLabDAOlmpl;
import Entity.Instrument;
import Entity.Laboratory;
import Entity.Teacher;
import Entity.Teacher_lab;
import Service.TeacherService;

public class teacherMenu extends JFrame {
    Teacher teacher;
    TeacherService teacherService = new TeacherService();
    TeacherDAOlmpl teacherDAOlmpl = new TeacherDAOlmpl();
    InstrumentDAOlmpl instrumentDAOlmpl = new InstrumentDAOlmpl();
    LaboratoryDAOlmpl laboratoryDAOlmpl = new LaboratoryDAOlmpl();
    TeacherLabDAO teacherLabDAOlmpl = new TeacherLabDAOlmpl();

    public teacherMenu(Teacher teacher) {
        this.teacher = teacher;
        setTitle("教师主界面");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu labMenu = new JMenu("实验室");
        menuBar.add(labMenu);

        JMenuItem viewLabInfoItem = new JMenuItem("查看实验室信息");
        labMenu.add(viewLabInfoItem);
        viewLabInfoItem.addActionListener(e -> {
            viewLabInfo(laboratoryDAOlmpl.getAllLaboratories());
        });

        JMenuItem applyLabItem = new JMenuItem("申请实验室");
        labMenu.add(applyLabItem);
        applyLabItem.addActionListener(e->{
            applyLab();
        });

        JMenuItem viewLabApplyItem = new JMenuItem("查看已申请实验室");
        labMenu.add(viewLabApplyItem);
        viewLabApplyItem.addActionListener(e->{
            viewLabApply();
        });
        //注销已申请实验室
        JMenuItem cancelLabApplyItem = new JMenuItem("注销已申请实验室");
        labMenu.add(cancelLabApplyItem);
        cancelLabApplyItem.addActionListener(e->{
            cancelLabApply();
        });

        JMenu instrumentMenu = new JMenu("仪器");
        menuBar.add(instrumentMenu);

        JMenuItem viewInstrumentInfoItem = new JMenuItem("查看仪器信息");
        instrumentMenu.add(viewInstrumentInfoItem);
        viewInstrumentInfoItem.addActionListener(e -> {
            viewInstrumentInfo(instrumentDAOlmpl.getAllInstruments());
        });

        JMenuItem reportDamageItem = new JMenuItem("申报仪器损坏");
        instrumentMenu.add(reportDamageItem);
        reportDamageItem.addActionListener(e -> {
            reportDamage();
        });
        //查询信息
        JMenu infoMenu = new JMenu("信息");
        menuBar.add(infoMenu);
        //按条件查询实验室
        JMenuItem queryLabItem = new JMenuItem("按条件查询实验室");
        infoMenu.add(queryLabItem);
        queryLabItem.addActionListener(e->{
            searchLab();
        });
        //按条件查询仪器
        JMenuItem queryInstrumentItem = new JMenuItem("按条件查询仪器");
        infoMenu.add(queryInstrumentItem);
        queryInstrumentItem.addActionListener(e->{
            searchInstrument();
        });
        
        //显示个人信息
        JLabel teacherInfo = new JLabel(
                "<html>教师信息" + "<br>姓名：" + teacher.getName() + " <br>职工号：" + teacher.getTeacherId() + "</html>");
        teacherInfo.setFont(new Font("微软雅黑", Font.BOLD, 25));
        teacherInfo.setBounds(10, 10, 300, 30);
        JPanel panel = new JPanel(new BorderLayout(ALLBITS, ABORT));
        panel.add(teacherInfo, BorderLayout.NORTH);
        
        //修改密码
        JButton changePassword = new JButton("修改密码");
        changePassword.setBounds(10, 50, 100, 30);
        panel.add(changePassword, BorderLayout.SOUTH);
        add(panel);
        changePassword.addActionListener(e -> {
            String oldPassword = JOptionPane.showInputDialog("请输入旧密码");
            if (oldPassword == null) {
                return;
            }
            if (!oldPassword.equals(teacher.getPassword())) {
                JOptionPane.showMessageDialog(null, "旧密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String newPassword = JOptionPane.showInputDialog("请输入新密码");
            if (newPassword == null) {
                return;
            }
            teacher.setPassword(newPassword);
            teacherDAOlmpl.updateTeacher(teacher);
            JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.PLAIN_MESSAGE);
        });
    }

    private void searchInstrument() {
        JFrame frame = new JFrame();
        frame.setTitle("按条件查找仪器");
        frame.setSize(260, 270);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //按仪器名称查找仪器
        JButton searchInstrumentByNameBtn = new JButton("按仪器名称查找仪器");
        searchInstrumentByNameBtn.setBounds(50, 50, 150, 30);
        panel.add(searchInstrumentByNameBtn);
        searchInstrumentByNameBtn.addActionListener(e->{
            searchInstrumentByName();
        });

        //按仪器id查找仪器
        JButton searchInstrumentByIdBtn = new JButton("按仪器id查找仪器");
        searchInstrumentByIdBtn.setBounds(50, 100, 150, 30);
        panel.add(searchInstrumentByIdBtn);
        searchInstrumentByIdBtn.addActionListener(e->{
            searchInstrumentById();
        });
        
        //按实验室查找仪器
        JButton searchInstrumentByLabBtn = new JButton("按实验室查找仪器");
        searchInstrumentByLabBtn.setBounds(50, 150, 150, 30);
        panel.add(searchInstrumentByLabBtn);
        searchInstrumentByLabBtn.addActionListener(e->{
            searchInstrumentByLab();
        });

        frame.add(panel);
    }

    private void searchLab() {
        JFrame frame = new JFrame();
        frame.setTitle("按条件查找实验室");
        frame.setSize(260, 360);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //按仪器查找实验室
        JButton searchLabByInstrumentBtn = new JButton("按仪器查找实验室");
        searchLabByInstrumentBtn.setBounds(50, 50, 150, 30);
        panel.add(searchLabByInstrumentBtn);
        searchLabByInstrumentBtn.addActionListener(e->{
            searchLabByInstrument();
        });

        //按实验室名称查找实验室
        JButton searchLabByNameBtn = new JButton("按实验室名称查找实验室");
        searchLabByNameBtn.setBounds(50, 100, 150, 30);
        panel.add(searchLabByNameBtn);
        searchLabByNameBtn.addActionListener(e->{
            searchLabByName();
        });

        //按位置查找实验室
        JButton searchLabByLocationBtn = new JButton("按位置查找实验室");
        searchLabByLocationBtn.setBounds(50, 150, 150, 30);
        panel.add(searchLabByLocationBtn);
        searchLabByLocationBtn.addActionListener(e->{
            searchLabByLocation();
        });

        //按id查找实验室
        JButton searchLabByIdBtn = new JButton("按id查找实验室");
        searchLabByIdBtn.setBounds(50, 200, 150, 30);
        panel.add(searchLabByIdBtn);
        searchLabByIdBtn.addActionListener(e->{
            searchLabById();
        });

        frame.add(panel);
    }

    private void searchInstrumentByLab() {
        String labName = JOptionPane.showInputDialog("请输入实验室名称");
        Laboratory laboratory = laboratoryDAOlmpl.getLaboratory(labName);
        List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByLab_id(laboratory.getId());
        if(instruments.size() == 0) {
            JOptionPane.showMessageDialog(null, "该实验室没有仪器");
        }else {
            viewInstrumentInfo(instruments);
        }
    }
    
    private void searchInstrumentById() {
        String instrumentId = JOptionPane.showInputDialog("请输入仪器id");
        Instrument instrument = instrumentDAOlmpl.getInstrument(Integer.parseInt(instrumentId));
        if(instrument == null) {
            JOptionPane.showMessageDialog(null, "仪器不存在");
        }else {
            JFrame frame = new JFrame();
            frame.setTitle("仪器信息");
            frame.setSize(270, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            JPanel panel = new JPanel();
            panel.setLayout(null);

            JLabel instrumentIdLabel = new JLabel("仪器id");
            instrumentIdLabel.setBounds(50, 50, 80, 30);
            panel.add(instrumentIdLabel);
            JTextField instrumentIdField = new JTextField();
            instrumentIdField.setBounds(120, 50, 100, 30);
            instrumentIdField.setEditable(false);
            instrumentIdField.setText(instrument.getId()+"");
            panel.add(instrumentIdField);

            JLabel instrumentNameLabel = new JLabel("仪器名称");
            instrumentNameLabel.setBounds(50, 100, 80, 30);
            panel.add(instrumentNameLabel);
            JTextField instrumentNameField = new JTextField();
            instrumentNameField.setBounds(120, 100, 100, 30);
            instrumentNameField.setEditable(false);
            instrumentNameField.setText(instrument.getName());
            panel.add(instrumentNameField);
            //仪器描述
            JLabel instrumentDescriptionLabel = new JLabel("仪器描述");
            instrumentDescriptionLabel.setBounds(50, 150, 80, 30);
            panel.add(instrumentDescriptionLabel);
            JTextField instrumentDescriptionField = new JTextField();
            instrumentDescriptionField.setBounds(120, 150, 100, 30);
            instrumentDescriptionField.setEditable(false);
            instrumentDescriptionField.setText(instrument.getDescription());
            panel.add(instrumentDescriptionField);
            //仪器状态
            JLabel instrumentStatusLabel = new JLabel("仪器状态");
            instrumentStatusLabel.setBounds(50, 200, 80, 30);
            panel.add(instrumentStatusLabel);
            JTextField instrumentStatusField = new JTextField();
            instrumentStatusField.setBounds(120, 200, 100, 30);
            instrumentStatusField.setEditable(false);
            instrumentStatusField.setText(instrument.isDamaged()? "损坏":"完好");
            panel.add(instrumentStatusField);

            frame.add(panel);
        }
    }
    
    private void searchInstrumentByName() {
        JFrame frame = new JFrame();
        frame.setTitle("按仪器名称查找仪器");
        frame.setSize(260, 270);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel instrumentNameLabel = new JLabel("仪器名称");
        instrumentNameLabel.setBounds(50, 50, 80, 30);
        panel.add(instrumentNameLabel);

        JTextField instrumentNameField = new JTextField();
        instrumentNameField.setBounds(120, 50, 100, 30);
        panel.add(instrumentNameField);

        JButton searchBtn = new JButton("查找");
        searchBtn.setBounds(50, 100, 150, 30);
        panel.add(searchBtn);
        searchBtn.addActionListener(e->{
            String instrumentName = instrumentNameField.getText();
            if(instrumentName.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入仪器名称", "错误", JOptionPane.ERROR_MESSAGE);
            }else {
                List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByName(instrumentName);
                if(instruments.size() == 0) {
                    JOptionPane.showMessageDialog(null, "没有找到仪器", "错误", JOptionPane.ERROR_MESSAGE);
                }else {
                    viewInstrumentInfo(instruments);
                }
            }
        });

        frame.add(panel);
    }

    private void searchLabById() {
        String id = JOptionPane.showInputDialog("请输入实验室id");
        Laboratory laboratory =new Laboratory();
        if(laboratoryDAOlmpl.getLaboratory(Integer.parseInt(id)) == null){
            JOptionPane.showMessageDialog(null, "实验室不存在");
            return;
        }else{
            laboratory = laboratoryDAOlmpl.getLaboratory(Integer.parseInt(id));
        }

        JFrame frame = new JFrame();
        frame.setTitle("按id查找实验室");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("实验室名称");
        label.setBounds(50, 50, 100, 30);
        panel.add(label);
        JTextField textField = new JTextField();
        textField.setBounds(150, 50, 200, 30);
        textField.setText(laboratory.getName());
        textField.setEditable(false);
        panel.add(textField);

        JLabel label1 = new JLabel("实验室位置");
        label1.setBounds(50, 100, 100, 30);
        panel.add(label1);
        JTextField textField1 = new JTextField();
        textField1.setBounds(150, 100, 200, 30);
        textField1.setText(laboratory.getLocation());
        textField1.setEditable(false);
        panel.add(textField1);

        JLabel label2 = new JLabel("实验室id");
        label2.setBounds(50, 150, 100, 30);
        panel.add(label2);
        JTextField textField2 = new JTextField();
        textField2.setBounds(150, 150, 200, 30);
        textField2.setText(String.valueOf(laboratory.getId()));
        textField2.setEditable(false);
        panel.add(textField2);

        JLabel label3 = new JLabel("实验室最大容量");
        label3.setBounds(50, 200, 100, 30);
        panel.add(label3);
        JTextField textField3 = new JTextField();
        textField3.setBounds(150, 200, 200, 30);
        textField3.setText(String.valueOf(laboratory.getMaxNumberOfStudents()));
        textField3.setEditable(false);
        panel.add(textField3);

        //查看实验室内仪器
        JButton viewInstrumentBtn = new JButton("查看实验室内仪器");
        viewInstrumentBtn.setBounds(50, 250, 150, 30);
        panel.add(viewInstrumentBtn);
        viewInstrumentBtn.addActionListener(e->{
            JTable table = new JTable();
            DefaultTableModel model = new DefaultTableModel();

            model.setColumnIdentifiers(new Object[]{"仪器名称", "仪器id", "仪器描述", "仪器状态"});
            List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByLab_id(Integer.parseInt(id));
            for (Instrument instrument : instruments) {
                model.addRow(new Object[]{instrument.getName(), instrument.getId(), instrument.getDescription(), instrument.isDamaged()? "损坏" : "正常"});

            }
            table.setModel(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 300, 350, 200);
            JOptionPane.showMessageDialog(null, scrollPane);
        });

        frame.add(panel);
    }
    
    private void searchLabByLocation() {
        String location = JOptionPane.showInputDialog("请输入实验室位置");
        List<Laboratory> laboratories = laboratoryDAOlmpl.getAllLaboratoriesByLocation(location);
        viewLabInfo(laboratories);
    }
    
    private void searchLabByName() {
        String name = JOptionPane.showInputDialog("请输入实验室名称");
        Laboratory laboratory = laboratoryDAOlmpl.getLaboratory(name);

        JFrame frame = new JFrame();
        frame.setTitle("按实验室名称查找实验室");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("实验室名称");
        label.setBounds(50, 50, 100, 30);
        panel.add(label);
        JTextField textField = new JTextField();
        textField.setBounds(150, 50, 200, 30);
        textField.setText(laboratory.getName());
        textField.setEditable(false);
        panel.add(textField);

        JLabel label1 = new JLabel("实验室位置");
        label1.setBounds(50, 100, 100, 30);
        panel.add(label1);
        JTextField textField1 = new JTextField();
        textField1.setBounds(150, 100, 200, 30);
        textField1.setText(laboratory.getLocation());
        textField1.setEditable(false);
        panel.add(textField1);

        JLabel label2 = new JLabel("实验室id");
        label2.setBounds(50, 150, 100, 30);
        panel.add(label2);
        JTextField textField2 = new JTextField();
        textField2.setBounds(150, 150, 200, 30);
        textField2.setText(String.valueOf(laboratory.getId()));
        textField2.setEditable(false);
        panel.add(textField2);

        JLabel label3 = new JLabel("实验室最大容量");
        label3.setBounds(50, 200, 100, 30);
        panel.add(label3);
        JTextField textField3 = new JTextField();
        textField3.setBounds(150, 200, 200, 30);
        textField3.setText(String.valueOf(laboratory.getMaxNumberOfStudents()));
        textField3.setEditable(false);
        panel.add(textField3);

        //查看实验室内仪器
        JButton viewInstrumentBtn = new JButton("查看实验室内仪器");
        viewInstrumentBtn.setBounds(50, 250, 150, 30);
        panel.add(viewInstrumentBtn);
        viewInstrumentBtn.addActionListener(e->{
            JTable table = new JTable();
            DefaultTableModel model = new DefaultTableModel();

            model.setColumnIdentifiers(new Object[]{"仪器名称", "仪器id", "仪器描述", "仪器状态"});
            List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByLab_id(laboratory.getId());
            for (Instrument instrument : instruments) {
                model.addRow(new Object[]{instrument.getName(), instrument.getId(), instrument.getDescription(), instrument.isDamaged()? "损坏" : "正常"});

            }
            table.setModel(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 300, 350, 200);
            JOptionPane.showMessageDialog(null, scrollPane);
        });

        frame.add(panel);

    }
    
    private void searchLabByInstrument() {
        //根据仪器名查找实验室
        String name = JOptionPane.showInputDialog("请输入仪器名称");
        List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByName(name);
        List<Laboratory> laboratories = new ArrayList<>();
        for (Instrument instrument : instruments) {
            laboratories.add(laboratoryDAOlmpl.getLaboratory(instrument.getLab_id()));
        }
        viewLabInfo(laboratories);
    }

    private void cancelLabApply() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("实验室编号");
        model.addColumn("实验室名称");
        model.addColumn("开始时间");
        model.addColumn("结束时间");
        model.addColumn("申请状态");
        List<Teacher_lab> teacher_labs = teacherService.getLabApply(teacher);
        for (Teacher_lab teacher_lab : teacher_labs) {
            Object rowData[] = new Object[5];
            rowData[0] = teacher_lab.getLab_id();
            rowData[1] = laboratoryDAOlmpl.getLaboratory(teacher_lab.getLab_id()).getName();
            rowData[2] = teacher_lab.getBegin_time();
            rowData[3] = teacher_lab.getEnd_time();
            rowData[4] = teacher_lab.isIs_agree()? "已通过":"未通过";
            model.addRow(rowData);
        }
        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(550, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scrollPane, "查看已申请实验室", JOptionPane.PLAIN_MESSAGE);

        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "未选择实验室", "错误", JOptionPane.ERROR_MESSAGE);
        }else{
            int labId = (int) table.getValueAt(row, 0);
            if(teacherLabDAOlmpl.deleteTeacherLab(teacher.getId(), labId)){
                JOptionPane.showMessageDialog(null, "注销成功", "成功", JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "注销失败", "失败", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void viewLabApply() {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("实验室编号");
        model.addColumn("实验室名称");
        model.addColumn("开始时间");
        model.addColumn("结束时间");
        model.addColumn("申请状态");
        List<Teacher_lab> teacher_labs = teacherService.getLabApply(teacher);
        for (Teacher_lab teacher_lab : teacher_labs) {
            Object rowData[] = new Object[5];
            rowData[0] = teacher_lab.getLab_id();
            rowData[1] = laboratoryDAOlmpl.getLaboratory(teacher_lab.getLab_id()).getName();
            rowData[2] = teacher_lab.getBegin_time();
            rowData[3] = teacher_lab.getEnd_time();
            rowData[4] = teacher_lab.isIs_agree()? "已通过":"未通过";
            model.addRow(rowData);
        }
        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(550, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scrollPane, "查看已申请实验室", JOptionPane.PLAIN_MESSAGE);
        
    }

    private void reportDamage() {
        JFrame frame = new JFrame();
        frame.setTitle("申报仪器损坏");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton backBtn = new JButton("返回");
        backBtn.setBounds(50, 300, 100, 30);
        panel.add(backBtn);
        backBtn.addActionListener(e->{
            frame.dispose();
        });

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("仪器编号");
        model.addColumn("仪器名称");
        model.addColumn("仪器描述");
        model.addColumn("仪器损坏情况");
        List<Instrument> allInstruments = instrumentDAOlmpl.getAllInstruments();
        for (Instrument instrument : allInstruments) {
            Object rowData[] = new Object[4];
            rowData[0] = instrument.getId();
            rowData[1] = instrument.getName();
            rowData[2] = instrument.getDescription();
            rowData[3] = instrument.isDamaged()? "损坏":"正常";
            model.addRow(rowData);
        }
        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(550, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(0, 0, 460, 200);

        JButton submitBtn = new JButton("提交");
        submitBtn.setBounds(300, 300, 100, 30);
        panel.add(submitBtn);
        submitBtn.addActionListener(e->{
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "未选择仪器", "错误", JOptionPane.ERROR_MESSAGE);
            }else{
                int instrumentId = (int) table.getValueAt(row, 0);
                Instrument instrument = instrumentDAOlmpl.getInstrument(instrumentId);
                instrument.setDamaged(true);
                instrumentDAOlmpl.updateInstrument(instrument);
                JOptionPane.showMessageDialog(null, "申报成功", "成功", JOptionPane.PLAIN_MESSAGE);
            }
        });

        frame.setContentPane(panel);
    }

    private void applyLab() {
        JFrame frame = new JFrame();
        frame.setTitle("申请实验室");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton backBtn = new JButton("返回");
        backBtn.setBounds(50, 300, 100, 30);
        panel.add(backBtn);
        backBtn.addActionListener(e->{
            frame.dispose();
        });

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("实验室编号");
        model.addColumn("实验室名称");
        model.addColumn("实验室位置");
        model.addColumn("实验室容量");
        List<Laboratory> allLaboratories = laboratoryDAOlmpl.getAllLaboratories();
        for (Laboratory laboratory : allLaboratories) {
            Object[] rowData = new Object[4];
            rowData[0] = laboratory.getId();
            rowData[1] = laboratory.getName();
            rowData[2] = laboratory.getLocation();
            rowData[3] = laboratory.getMaxNumberOfStudents();
            model.addRow(rowData);
        }
        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(550, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 460, 300);
        panel.add(scrollPane);

        JButton applyBtn = new JButton("申请");
        applyBtn.setBounds(300, 300, 100, 30);
        panel.add(applyBtn);
        applyBtn.addActionListener(e->{
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "未选择实验室", "错误", JOptionPane.ERROR_MESSAGE);
            }else{
                int labId = (int) table.getValueAt(row, 0);
                if(teacherLabDAOlmpl.isLabApply(labId)){
                    JOptionPane.showMessageDialog(null, "该实验室已申请", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    String beginTime = JOptionPane.showInputDialog("请输入开始时间");
                    String endTime = JOptionPane.showInputDialog("请输入结束时间");
                    teacherService.applyLaboratory(teacher, labId, beginTime, endTime);
                    JOptionPane.showMessageDialog(null, "申请成功", "成功", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        frame.setContentPane(panel);
    }

    private void viewLabInfo(List<Laboratory> allLaboratories) {
        JFrame frame = new JFrame();
        frame.setTitle("实验室信息");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton backBtn = new JButton("返回");
        backBtn.setBounds(50, 300, 100, 30);
        panel.add(backBtn);
        backBtn.addActionListener(e->{
            frame.dispose();
        });

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("实验室编号");
        model.addColumn("实验室名称");
        model.addColumn("实验室位置");
        model.addColumn("实验室容量");
        for (Laboratory laboratory : allLaboratories) {
            Object[] rowData = new Object[4];
            rowData[0] = laboratory.getId();
            rowData[1] = laboratory.getName();
            rowData[2] = laboratory.getLocation();
            rowData[3] = laboratory.getMaxNumberOfStudents();
            model.addRow(rowData);
        }
        table.setModel(model);
        table.setBounds(50, 50, 350, 200);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 350, 200);
        panel.add(scrollPane);

        // 查看实验室仪器信息
        JButton viewBtn = new JButton("查看仪器信息");
        viewBtn.setBounds(250, 300, 150, 30);
        panel.add(viewBtn);
        viewBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择一条实验室");
            } else {
                int id = (int) table.getValueAt(row, 0);
                List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByLab_id(id);
                viewInstrumentInfo(instruments);
            }
        });

        frame.add(panel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void viewInstrumentInfo(List<Instrument> allInstruments) {
        // 创建表格
        JTable table = new JTable();

        // 创建表格模型
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("仪器编号");
        model.addColumn("仪器名称");
        model.addColumn("仪器描述");
        model.addColumn("仪器所在实验室");
        model.addColumn("仪器状态");

        // 添加数据到表格模型
        for (Instrument instrument : allInstruments) {
            Object[] row = new Object[5];
            row[0] = instrument.getId();
            row[1] = instrument.getName();
            row[2] = instrument.getDescription();
            row[3] = laboratoryDAOlmpl.getLaboratory(instrument.getLab_id()).getName();
            row[4] = instrument.isDamaged() ? "损坏" : "正常";
            model.addRow(row);
        }

        // 设置表格模型
        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        // 显示表格
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scrollPane, "所有仪器信息", JOptionPane.PLAIN_MESSAGE);
    }
    public static void main(String[] args) {
        TeacherDAOlmpl teacherDAOlmpl = new TeacherDAOlmpl();
        new teacherMenu(teacherDAOlmpl.getTeacher("211210400209"));
    }
}
