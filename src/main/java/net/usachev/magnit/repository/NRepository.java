package net.usachev.magnit.repository;

import java.util.List;

public interface NRepository {

    void createTable();

    void save(int record);

    List<Integer> getRecords();

}
