package ru.dream.checkingsleep.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.mappers.CommentMapper;
import ru.dream.checkingsleep.model.Comment;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentDto getCommentByDream(Dream dream) {
        Specification<Comment> specification = Specification.where(new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("dream"), dream);
            }
        });
        Comment comment = commentRepository.findOne(specification).orElseThrow();
        return CommentMapper.INSTANCE.commentToDto(comment);

    }
}
