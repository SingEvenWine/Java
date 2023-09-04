package DAO;

import java.util.List;

import Entity.Instrument;

public interface InstrumentDAO {
    public void addInstrument(Instrument instrument);
    public void deleteInstrument(Instrument instrument);
    public void updateInstrument(Instrument instrument);
    public Instrument getInstrument(int id);
    public List<Instrument> getAllInstruments();
}
