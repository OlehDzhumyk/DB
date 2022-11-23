package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventByName(String name);

    @Procedure("insert_into_event")
    Integer insertWithProcedure(@Param("name_p") String name, @Param("phone_number_p") String phoneNumber, @Param("address_p") String address);

    @Procedure("insert_into_ticket_m_to_m")
    void insertIntoTicketMtoM(@Param("event_name") String eventName, @Param("user_name") String userName);

}
