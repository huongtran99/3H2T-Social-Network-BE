package com.codegym.service.reaction;

import com.codegym.model.entity.Reaction;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IReactionService extends IGeneralService<Reaction> {
    Integer countByReaction(Long id);

    Integer checkReaction(Long postId, Long userId);

    Optional<Reaction> getReaction(Long postId, Long userId);
}
