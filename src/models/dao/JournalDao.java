package models.dao;

import models.pojo.Journal;

import java.util.List;

/**
 * Created by sergey on 18.04.17.
 */
public interface JournalDao {
    List<Journal> findAll();
    List<Journal> findById();
    List<Journal> findByName();
    boolean insertJournal(Journal journal);
    boolean updateJournal(Journal journal);
    boolean deleteJournal(Journal journal);
}
