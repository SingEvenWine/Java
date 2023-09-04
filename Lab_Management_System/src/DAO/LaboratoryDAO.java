package DAO;

import java.util.List;

import Entity.Laboratory;

public interface LaboratoryDAO {
    public void addLaboratory(Laboratory laboratory);
    public void deleteLaboratory(Laboratory laboratory);
    public void updateLaboratory(Laboratory laboratory);
    public Laboratory getLaboratory(int id);
    public Laboratory getLaboratory(String name);
    public List<Laboratory> getAllLaboratories();
}
