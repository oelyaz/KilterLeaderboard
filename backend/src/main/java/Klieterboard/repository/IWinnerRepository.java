package Klieterboard.repository;

import Klieterboard.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWinnerRepository extends JpaRepository<Winner, Integer> {
}
