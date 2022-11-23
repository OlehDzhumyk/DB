package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Procedure("insert_10_records_into_user_table")
    void insertTenUser();

    @Procedure("users_ids_sum")
    Integer getUsersIdSum();

    @Procedure("create_2_tables_and_insert_data_dinamically")
    void createTwoTablesAndInsertDataDynamically();

}
