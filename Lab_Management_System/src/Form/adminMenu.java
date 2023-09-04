package Form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.InstrumentDAOlmpl;
import DAO.LaboratoryDAOlmpl;
import DAO.TeacherDAOlmpl;
import DAO.TeacherLabDAOlmpl;
import Entity.Instrument;
import Entity.Laboratory;
import Entity.Teacher_lab;
import Service.TeacherService;

public class adminMenu extends JFrame{
    TeacherDAOlmpl teacherDAOlmpl = new TeacherDAOlmpl();
    InstrumentDAOlmpl instrumentDAOlmpl = new InstrumentDAOlmpl();
    LaboratoryDAOlmpl laboratoryDAOlmpl = new LaboratoryDAOlmpl();
    TeacherLabDAOlmpl teacherLabDAOlmpl = new TeacherLabDAOlmpl();
    TeacherService teacherService = new TeacherService();

    public adminMenu() {
        setTitle("管理员主界面");
        setSize(460, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查看实验室信息
        JButton viewLabInfoBtn = new JButton("查看实验室信息");
        viewLabInfoBtn.setBounds(50, 50, 150, 30);
        panel.add(viewLabInfoBtn);
        viewLabInfoBtn.addActionListener(e->{
            viewLabInfo(laboratoryDAOlmpl.getAllLaboratories());
        });

        // 处理实验室申请
        JButton handleLabApplyBtn = new JButton("处理实验室申请");
        handleLabApplyBtn.setBounds(50, 250, 150, 30);
        panel.add(handleLabApplyBtn);
        handleLabApplyBtn.addActionListener(e->{
            handleLabApply();
        });

        //添加实验室
        JButton addLabBtn = new JButton("添加实验室");
        addLabBtn.setBounds(50, 100, 150, 30);
        panel.add(addLabBtn);
        addLabBtn.addActionListener(e->{
            addLab();
        });

        //删除实验室
        JButton deleteLabBtn = new JButton("删除实验室");
        deleteLabBtn.setBounds(50, 150, 150, 30);
        panel.add(deleteLabBtn);
        deleteLabBtn.addActionListener(e->{
            deleteLab();
        });

        //修改实验室信息
        JButton modifyLabBtn = new JButton("修改实验室信息");
        modifyLabBtn.setBounds(50, 200, 150, 30);
        panel.add(modifyLabBtn);
        modifyLabBtn.addActionListener(e->{
            modifyLab();
        });

        //按条件查找实验室
        JButton searchLabBtn = new JButton("按条件查找实验室");
        searchLabBtn.setBounds(50, 300, 150, 30);
        panel.add(searchLabBtn);
        searchLabBtn.addActionListener(e->{
            searchLab();
        });

        //按条件查找仪器
        JButton searchInstrumentBtn = new JButton("按条件查找仪器");
        searchInstrumentBtn.setBounds(250, 300, 150, 30);
        panel.add(searchInstrumentBtn);
        searchInstrumentBtn.addActionListener(e->{
            searchInstrument();
        });

        //查看仪器信息
        JButton viewInstrumentInfoBtn = new JButton("查看仪器信息");
        viewInstrumentInfoBtn.setBounds(250, 50, 150, 30);
        panel.add(viewInstrumentInfoBtn);
        viewInstrumentInfoBtn.addActionListener(e->{
            viewInstrumentInfo(instrumentDAOlmpl.getAllInstruments());
        });

        //添加仪器
        JButton addInstrumentBtn = new JButton("添加仪器");
        addInstrumentBtn.setBounds(250, 100, 150, 30);
        panel.add(addInstrumentBtn);
        addInstrumentBtn.addActionListener(e->{
            addInstrument();
        });

        //删除仪器
        JButton deleteInstrumentBtn = new JButton("删除仪器");
        deleteInstrumentBtn.setBounds(250, 150, 150, 30);
        panel.add(deleteInstrumentBtn);
        deleteInstrumentBtn.addActionListener(e->{
            deleteInstrument();
        });

        //修改仪器信息
        JButton modifyInstrumentBtn = new JButton("修改仪器信息");
        modifyInstrumentBtn.setBounds(250, 200, 150, 30);
        panel.add(modifyInstrumentBtn);
        modifyInstrumentBtn.addActionListener(e->{
            modifyInstrument();
        });

        //处理损坏仪器
        JButton handleInstrumentBtn = new JButton("处理损坏仪器");
        handleInstrumentBtn.setBounds(250, 250, 150, 30);
        panel.add(handleInstrumentBtn);
        handleInstrumentBtn.addActionListener(e->{
            handleInstrument();
        });

        add(panel);
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
    
    private void handleInstrument() {
        JFrame frame = new JFrame();
        frame.setTitle("处理损坏仪器");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        List<Instrument> instruments = instrumentDAOlmpl.getAllInstruments();
        String[] columnNames = {"仪器id", "仪器名称", "所属实验室名称", "是否损坏"};
        Object[][] rowData = new Object[instruments.size()][4];
        for (int i = 0; i < instruments.size(); i++) {
            rowData[i][0] = instruments.get(i).getId();
            rowData[i][1] = instruments.get(i).getName();
            rowData[i][2] = laboratoryDAOlmpl.getLaboratory(instruments.get(i).getLab_id()).getName();
            rowData[i][3] = instruments.get(i).isDamaged()? "损坏" : "正常";
        }
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 350, 200);
        panel.add(scrollPane);
        
        JButton submitBtn = new JButton("提交");
        submitBtn.setBounds(50, 300, 100, 30);
        panel.add(submitBtn);
        submitBtn.addActionListener(e->{
            int row = table.getSelectedRow();
            int instrument_id = Integer.parseInt(table.getValueAt(row, 0).toString());
            Instrument instrument = instrumentDAOlmpl.getInstrument(instrument_id);
            instrument.setDamaged(false);
            instrumentDAOlmpl.updateInstrument(instrument);
            JOptionPane.showMessageDialog(null, "处理成功");
            frame.dispose();    
        });
        
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(300, 300, 100, 30);
        panel.add(cancelBtn);
        cancelBtn.addActionListener(e->{
            frame.dispose();
        });

        frame.add(panel);
    }
    
    private void modifyInstrument() {
        JFrame frame = new JFrame();
        frame.setTitle("修改仪器信息");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        List<Instrument> instruments = instrumentDAOlmpl.getAllInstruments();
        String[] columnNames = {"仪器id", "仪器名称", "所属实验室名称", "是否损坏"};
        Object[][] rowData = new Object[instruments.size()][4];
        for (int i = 0; i < instruments.size(); i++) {
            rowData[i][0] = instruments.get(i).getId();
            rowData[i][1] = instruments.get(i).getName();
            rowData[i][2] = laboratoryDAOlmpl.getLaboratory(instruments.get(i).getLab_id()).getName();
            rowData[i][3] = instruments.get(i).isDamaged()? "损坏" : "正常";
        }
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 350, 200);
        panel.add(scrollPane);

        //取消按钮
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(300, 300, 100, 30);
        panel.add(cancelBtn);
        cancelBtn.addActionListener(e->{
            frame.dispose();
        });

        JButton submitBtn = new JButton("修改");
        submitBtn.setBounds(50, 300, 100, 30);
        panel.add(submitBtn);
        submitBtn.addActionListener(e->{
            JFrame modifyFrame = new JFrame();
            modifyFrame.setTitle("修改仪器信息");
            modifyFrame.setSize(460, 400);
            modifyFrame.setLocationRelativeTo(null);
            modifyFrame.setVisible(true);

            JPanel modifyPanel = new JPanel();
            modifyPanel.setLayout(null);

            int row = table.getSelectedRow();
            int instrument_id = Integer.parseInt(table.getValueAt(row, 0).toString());
            Instrument instrument = instrumentDAOlmpl.getInstrument(instrument_id);

            JLabel idLabel = new JLabel("仪器id");
            idLabel.setBounds(50, 50, 100, 30);
            modifyPanel.add(idLabel);
            JTextField idField = new JTextField();
            idField.setBounds(150, 50, 200, 30);
            idField.setText(String.valueOf(instrument.getId()));
            idField.setEditable(false);
            modifyPanel.add(idField);

            JLabel nameLabel = new JLabel("仪器名称");
            nameLabel.setBounds(50, 100, 100, 30);
            modifyPanel.add(nameLabel);
            JTextField nameField = new JTextField();
            nameField.setBounds(150, 100, 200, 30);
            nameField.setText(instrument.getName());
            modifyPanel.add(nameField);

            JLabel labIdLabel = new JLabel("所属实验室id");
            labIdLabel.setBounds(50, 150, 100, 30);
            modifyPanel.add(labIdLabel);
            JTextField labIdField = new JTextField();
            labIdField.setBounds(150, 150, 200, 30);
            labIdField.setText(String.valueOf(instrument.getLab_id()));
            modifyPanel.add(labIdField);

            JLabel statusLabel = new JLabel("仪器描述");
            statusLabel.setBounds(50, 200, 100, 30);
            modifyPanel.add(statusLabel);
            JTextField statusField = new JTextField();
            statusField.setBounds(150, 200, 200, 30);
            statusField.setText(instrument.getDescription());
            modifyPanel.add(statusField);

            JButton submitBtn1 = new JButton("提交");
            submitBtn1.setBounds(50, 250, 100, 30);
            modifyPanel.add(submitBtn1);
            submitBtn1.addActionListener(e1 -> {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int labId = Integer.parseInt(labIdField.getText());
                String status = statusField.getText();
                instrument.setId(id);
                instrument.setName(name);
                instrument.setLab_id(labId);
                instrument.setDescription(status);
                instrumentDAOlmpl.updateInstrument(instrument);
                JOptionPane.showMessageDialog(null, "修改成功");
                modifyFrame.dispose();
            });

            JButton cancelBtn1 = new JButton("取消");
            cancelBtn1.setBounds(250, 250, 100, 30);
            modifyPanel.add(cancelBtn1);
            cancelBtn1.addActionListener(e1 -> {
                modifyFrame.dispose();
            });

            modifyFrame.add(modifyPanel);
        });
        frame.add(panel);
    }
    
    private void deleteInstrument() {
        JFrame frame = new JFrame();
        frame.setTitle("删除仪器");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        List<Instrument> instruments = instrumentDAOlmpl.getAllInstruments();
        String[] columnNames = {"仪器id", "仪器名称", "所属实验室名称", "是否损坏"};
        Object[][] rowData = new Object[instruments.size()][4];
        for (int i = 0; i < instruments.size(); i++) {
            rowData[i][0] = instruments.get(i).getId();
            rowData[i][1] = instruments.get(i).getName();
            rowData[i][2] = laboratoryDAOlmpl.getLaboratory(instruments.get(i).getLab_id()).getName();
            rowData[i][3] = instruments.get(i).isDamaged()? "损坏" : "正常";
        }
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 350, 200);
        panel.add(scrollPane);

        JButton submitBtn = new JButton("提交");
        submitBtn.setBounds(50, 300, 100, 30);
        panel.add(submitBtn);
        submitBtn.addActionListener(e->{
            int row = table.getSelectedRow();
            int instrument_id = Integer.parseInt(table.getValueAt(row, 0).toString());
            instrumentDAOlmpl.deleteInstrument(instrumentDAOlmpl.getInstrument(instrument_id));
            JOptionPane.showMessageDialog(null, "删除成功");
            frame.dispose();
        });

        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(250, 300, 100, 30);
        panel.add(cancelBtn);
        cancelBtn.addActionListener(e->{
            frame.dispose();
        });

        frame.add(panel);
    }
    
    private void addInstrument() {
        JFrame frame = new JFrame();
        frame.setTitle("添加仪器");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("仪器名称");
        nameLabel.setBounds(50, 50, 100, 30);
        panel.add(nameLabel);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        panel.add(nameField);

        JLabel descriptionLabel = new JLabel("仪器描述");
        descriptionLabel.setBounds(50, 100, 100, 30);
        panel.add(descriptionLabel);
        JTextField descriptionField = new JTextField();
        descriptionField.setBounds(150, 100, 200, 30);
        panel.add(descriptionField);

        JLabel labIdLabel = new JLabel("所属实验室id");
        labIdLabel.setBounds(50, 150, 100, 30);
        panel.add(labIdLabel);
        JTextField labIdField = new JTextField();
        labIdField.setBounds(150, 150, 200, 30);
        panel.add(labIdField);

        JButton submitBtn = new JButton("提交");
        submitBtn.setBounds(50, 250, 100, 30);
        panel.add(submitBtn);
        submitBtn.addActionListener(e->{
            String name = nameField.getText();
            String description = descriptionField.getText();
            int lab_id = Integer.parseInt(labIdField.getText());
            Instrument instrument = new Instrument(name, description, lab_id);
            if(name.equals("")||description.equals("") || labIdField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请填写完整信息");
            }else if(laboratoryDAOlmpl.getLaboratory(lab_id) == null) {
                JOptionPane.showMessageDialog(null, "实验室不存在");
            }else{
                instrumentDAOlmpl.addInstrument(instrument);
                JOptionPane.showMessageDialog(null, "添加成功");
            }
            frame.dispose();
        });

        JButton backBtn = new JButton("返回");
        backBtn.setBounds(250, 250, 100, 30);
        panel.add(backBtn);
        backBtn.addActionListener(e->{
            frame.dispose();
        });

        frame.add(panel);

    }
    
    private void viewInstrumentInfo(List<Instrument> allInstruments) {
        JFrame frame = new JFrame();
        frame.setTitle("查看仪器信息");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton backBtn = new JButton("返回");
        backBtn.setBounds(50, 320, 100, 30);
        panel.add(backBtn);
        backBtn.addActionListener(e->{
            frame.dispose();
        });

        String[] columnNames = {"仪器id", "仪器名称", "仪器描述", "仪器状态", "所属实验室"};
        String[][] tableValues = new String[allInstruments.size()][5];
        for (int i = 0; i < allInstruments.size(); i++) {
            tableValues[i][0] = String.valueOf(allInstruments.get(i).getId());
            tableValues[i][1] = allInstruments.get(i).getName();
            tableValues[i][2] = allInstruments.get(i).getDescription();
            tableValues[i][3] = allInstruments.get(i).isDamaged()? "损坏" : "正常";
            tableValues[i][4] = String.valueOf(allInstruments.get(i).getLab_id());
        }
        JTable table = new JTable(tableValues, columnNames);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 350, 250);
        panel.add(scrollPane);

        frame.add(panel);
    }
    
    private void modifyLab() {
        JFrame frame = new JFrame();
        frame.setTitle("修改实验室信息");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        List<Laboratory> allLaboratories = laboratoryDAOlmpl.getAllLaboratories();

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

        JButton backBtn = new JButton("返回");
        backBtn.setBounds(50, 300, 100, 30);
        panel.add(backBtn);
        backBtn.addActionListener(e -> {
            frame.dispose();
        });

        JButton modifyBtn = new JButton("修改");
        modifyBtn.setBounds(150, 300, 100, 30);
        panel.add(modifyBtn);
        modifyBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择一条实验室");
            } else {
                int id = (int) table.getValueAt(row, 0);
                Laboratory laboratory = laboratoryDAOlmpl.getLaboratory(id);
                JFrame modifyFrame = new JFrame();
                modifyFrame.setTitle("修改实验室信息");
                modifyFrame.setSize(460, 400);
                modifyFrame.setLocationRelativeTo(null);
                modifyFrame.setVisible(true);

                JPanel modifyPanel = new JPanel();
                modifyPanel.setLayout(null);

                JLabel nameLabel = new JLabel("实验室名称");
                nameLabel.setBounds(50, 50, 100, 30);
                modifyPanel.add(nameLabel);
                JTextField nameArea = new JTextField();
                nameArea.setBounds(150, 50, 200, 30);
                nameArea.setText(laboratory.getName());
                modifyPanel.add(nameArea);

                JLabel locationLabel = new JLabel("实验室位置");
                locationLabel.setBounds(50, 100, 100, 30);
                modifyPanel.add(locationLabel);
                JTextField locationArea = new JTextField();
                locationArea.setBounds(150, 100, 200, 30);
                locationArea.setText(laboratory.getLocation());
                modifyPanel.add(locationArea);

                JLabel capacityLabel = new JLabel("实验室容量");
                capacityLabel.setBounds(50, 150, 100, 30);
                modifyPanel.add(capacityLabel);
                JTextField capacityArea = new JTextField();
                capacityArea.setBounds(150, 150, 200, 30);
                capacityArea.setText(String.valueOf(laboratory.getMaxNumberOfStudents()));
                modifyPanel.add(capacityArea);

                JButton confirmBtn = new JButton("确认");
                confirmBtn.setBounds(150, 200, 100, 30);
                modifyPanel.add(confirmBtn);

                confirmBtn.addActionListener(e1 -> {
                    laboratory.setName(nameArea.getText());
                    laboratory.setLocation(locationArea.getText());
                    laboratory.setMaxNumberOfStudents(Integer.parseInt(capacityArea.getText()));
                    laboratoryDAOlmpl.updateLaboratory(laboratory);
                    JOptionPane.showMessageDialog(null, "修改成功");
                    modifyFrame.dispose();
                    frame.dispose();
                });

                JButton cancelBtn = new JButton("取消");
                cancelBtn.setBounds(250, 200, 100, 30);
                modifyPanel.add(cancelBtn);
                cancelBtn.addActionListener(e1 -> {
                    modifyFrame.dispose();
                });

                modifyFrame.add(modifyPanel);
            }
        });

        frame.add(panel);
    }
    
    private void deleteLab() {
        List<Laboratory> allLaboratories = laboratoryDAOlmpl.getAllLaboratories();

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

        // 删除实验室
        JButton deleteBtn = new JButton("删除实验室");
        deleteBtn.setBounds(250, 250, 150, 30);
        panel.add(deleteBtn);
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择一条实验室");
            } else {
                int id = (int) table.getValueAt(row, 0);
                if(id==-1){
                    JOptionPane.showMessageDialog(null, "仓库不能删除");
                    return;
                }
                if(instrumentDAOlmpl.getInstrumentByLab_id(id) != null){
                    List<Instrument> instruments = instrumentDAOlmpl.getInstrumentByLab_id(id);
                    for (Instrument instrument : instruments) {
                        instrument.setLab_id(-1);
                        instrumentDAOlmpl.updateInstrument(instrument);
                    }
                }
                laboratoryDAOlmpl.deleteLaboratory(laboratoryDAOlmpl.getLaboratory(id));
                JOptionPane.showMessageDialog(null, "删除成功");
                frame.dispose();
            }
        });

        frame.add(panel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    
    private void addLab() {
        JFrame frame = new JFrame();
        frame.setTitle("添加实验室");
        frame.setSize(460, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("实验室名称");
        nameLabel.setBounds(50, 50, 100, 30);
        panel.add(nameLabel);
        JTextField nameArea = new JTextField();
        nameArea.setBounds(150, 50, 200, 30);
        panel.add(nameArea);

        JLabel locationLabel = new JLabel("实验室位置");
        locationLabel.setBounds(50, 100, 100, 30);
        panel.add(locationLabel);
        JTextField locationArea = new JTextField();
        locationArea.setBounds(150, 100, 200, 30);
        panel.add(locationArea);

        JLabel capacityLabel = new JLabel("实验室容量");
        capacityLabel.setBounds(50, 150, 100, 30);
        panel.add(capacityLabel);
        JTextField capacityArea = new JTextField();
        capacityArea.setBounds(150, 150, 200, 30);
        panel.add(capacityArea);

        JButton confirmBtn = new JButton("确认");
        confirmBtn.setBounds(100, 200, 100, 30);
        panel.add(confirmBtn);

        confirmBtn.addActionListener(e->{
            Laboratory laboratory = new Laboratory(nameArea.getText(), locationArea.getText(), Integer.parseInt(capacityArea.getText()));
            if(nameArea.getText().equals("")||locationArea.getText().equals("")||capacityArea.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请填写完整信息");
            }else{
                laboratoryDAOlmpl.addLaboratory(laboratory);
                JOptionPane.showMessageDialog(null, "添加成功");
            }
            frame.dispose();
        });

        JButton backBtn = new JButton("返回");
        backBtn.setBounds(250, 200, 100, 30);
        panel.add(backBtn);
        backBtn.addActionListener(e->{
            frame.dispose();
        });

        frame.add(panel);

    }
    
    private void handleLabApply() {
        //获取所有申请并选择
        List<Teacher_lab> allTeacherLabs = teacherLabDAOlmpl.getAllTeacherLabs();
        JFrame frame = new JFrame();
        frame.setTitle("处理实验室申请");
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
        model.addColumn("申请编号");
        model.addColumn("教师名称");
        model.addColumn("实验室名称");
        model.addColumn("申请时间");
        model.addColumn("申请状态");
        for (Teacher_lab teacher_lab : allTeacherLabs) {
            Object[] rowData = new Object[5];
            rowData[0] = teacher_lab.getId();
            rowData[1] = teacherDAOlmpl.getTeacher(teacher_lab.getTeacher_id()).getName();
            rowData[2] = laboratoryDAOlmpl.getLaboratory(teacher_lab.getLab_id()).getName();
            rowData[3] = teacher_lab.getBegin_time()+"-"+teacher_lab.getEnd_time();
            rowData[4] = teacher_lab.isIs_agree()? "已同意" : "未同意";
            model.addRow(rowData);
        }
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 350, 200);
        panel.add(scrollPane);

        //选择申请
        JButton selectBtn = new JButton("同意");
        selectBtn.setBounds(200, 300, 100, 30);
        panel.add(selectBtn);
        selectBtn.addActionListener(e->{
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择一条申请");
            } else {
                int id = (int) table.getValueAt(row, 0);
                Teacher_lab teacher_lab = teacherLabDAOlmpl.getTeacherLab(id);
                if(teacherLabDAOlmpl.isLabApply(teacher_lab)==false){
                    if (teacher_lab.isIs_agree()) {
                        JOptionPane.showMessageDialog(null, "该申请已通过");
                    } else {
                        teacher_lab.setIs_agree(true);
                        teacherLabDAOlmpl.updateTeacherLab(teacher_lab);
                        JOptionPane.showMessageDialog(null, "已通过该申请");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "该实验室已被申请");
                }
            }
        });

        frame.add(panel);
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
    
    public static void main(String[] args) {
        new adminMenu();
    }

}
