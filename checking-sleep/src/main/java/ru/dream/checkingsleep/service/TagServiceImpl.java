package ru.dream.checkingsleep.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.mappers.DreamMapper;
import ru.dream.checkingsleep.mappers.TagMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.Tag;
import ru.dream.checkingsleep.repository.TagRepository;
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    @Override
    public TagDto getTagByDream(Dream dream) {
        Specification<Tag> specification = Specification.where(new Specification<Tag>() {
            @Override
            public Predicate toPredicate(Root<Tag> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("dream"), dream.getTags());
            }
        });
        Tag tag = tagRepository.findOne(specification).orElseThrow();
        return TagMapper.INSTANCE.tagToDto(tag);
    }
}
