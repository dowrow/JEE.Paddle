package data.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entities.Court;

public interface CourtDao extends JpaRepository<Court, Integer>{

	Court findById(int courtId);

}
