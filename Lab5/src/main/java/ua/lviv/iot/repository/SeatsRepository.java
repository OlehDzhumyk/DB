package ua.lviv.iot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.Seats;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Integer> {
}
