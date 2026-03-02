package Klieterboard.service;

import Klieterboard.entity.Winner;
import Klieterboard.repository.IWinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinnerService {

    private final IWinnerRepository winnerRepository;

    @Autowired
    public WinnerService(IWinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
    }

    /**
     * Returns a List of all winners saved in the database
     *
     * @return a List of all winners
     */
    public List<Winner> findAll() {
        return winnerRepository.findAll();
    }

    /**
     * Saves a winner in the database.
     *
     * @param winner winner entity to be saved
     * @return The saved winner entity.
     */
    public Winner saveWinner(Winner winner) {
        return winnerRepository.save(winner);
    }

    /**
     * Inserts a new winner in the database.
     *
     * @param winner winner to be inserted
     * @return The inserted winner entity.
     */
    public Winner insertWinner(Winner winner) {
        return winnerRepository.save(winner);
    }
}
