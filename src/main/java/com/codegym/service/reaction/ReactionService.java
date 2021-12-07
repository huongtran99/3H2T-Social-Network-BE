package com.codegym.service.reaction;

import com.codegym.model.entity.Reaction;
import com.codegym.repository.IReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReactionService implements IReactionService{

    @Autowired
    private IReactionRepository reactionRepository;

    @Override
    public Iterable<Reaction> findAll() {
        return reactionRepository.findAll();
    }

    @Override
    public Optional<Reaction> findById(Long id) {
        return reactionRepository.findById(id);
    }

    @Override
    public Reaction save(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    @Override
    public void remove(Long id) {
        reactionRepository.deleteById(id);
    }

    @Override
    public Integer countByReaction(Long id) {
        return reactionRepository.countByReaction(id);
    }

    @Override
    public Integer checkReaction(Long postId, Long userId) {
        return reactionRepository.checkReaction(postId, userId);
    }

    @Override
    public Optional<Reaction> getReaction(Long postId, Long userId) {
        return reactionRepository.getReaction(postId, userId);
    }
}
