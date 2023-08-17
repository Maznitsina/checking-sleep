package ru.dream.checkingsleep.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.*;
import ru.dream.checkingsleep.mappers.DreamMapper;
import ru.dream.checkingsleep.mappers.TagMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.Tag;
import ru.dream.checkingsleep.repository.TagRepository;
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private  final TagMapper tagMapper;
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
        return tagMapper.toDto(tag);
    }
    @Override
    public TagDto createTag(TagCreateDto tagCreateDto) {
        Tag tag = tagRepository.save(tagMapper.toEntity(tagCreateDto));
        return tagMapper.toDto(tag);
    }

    @Override
    public TagDto updateTag(TagUpdateDto tagUpdateDto) {
            Tag tag = tagRepository.findById(tagUpdateDto.getId()).orElseThrow();
            tag.setTag(tagUpdateDto.getTag());
            Tag savedTag = tagRepository.save(tag);
            return tagMapper.toDto(savedTag);
        }
    }
