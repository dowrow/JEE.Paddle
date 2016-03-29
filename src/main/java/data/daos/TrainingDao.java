package data.daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entities.Court;
import data.entities.Reserve;
import data.entities.Training;

public interface TrainingDao extends JpaRepository<Training, Integer> {
    
	// Buscar que aún no han comenzado
    List<Training> findByStartDateBetween(Calendar startDate, Calendar endDate);

}
