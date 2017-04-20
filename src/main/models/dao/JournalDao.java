package main.models.dao;

import main.models.pojo.Journal;

import java.util.List;


public interface JournalDao {
    List<Journal> getAll();
    Journal getById(int id);
    boolean insertJournal(Journal journal);
    boolean updateJournal(Journal journal);
    boolean deleteJournal(Journal journal);
}
