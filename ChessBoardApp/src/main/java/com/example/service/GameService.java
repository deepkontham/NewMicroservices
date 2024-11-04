package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Game;
import com.example.model.User;
import com.example.repository.GameRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    // Initialize a new game
    public Game startNewGame(User whitePlayer, User blackPlayer) {
        Game game = new Game();
        game.setWhitePlayer(whitePlayer);
        game.setBlackPlayer(blackPlayer);
        game.setGameState("initial_state"); // Store initial game state (e.g., FEN notation)
        return gameRepository.save(game);
    }

    // Handle move validation (you will need to implement chess rules)
    public boolean isValidMove(Game game, String move) {
        // Implement chess move validation here
    	
        return true;
    }

    // Update game state after a valid move
    public Game makeMove(Game game, String move) {
        if (isValidMove(game, move)) {
            // Update game state
            game.setGameState("new_state"); // Update with new board state
            return gameRepository.save(game);
        }
        throw new IllegalArgumentException("Invalid move");
    }
}
