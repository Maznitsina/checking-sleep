package ru.dream.checkingsleep.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.mappers.DreamMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.repository.DreamRepository;


@Service
@RequiredArgsConstructor
public class DreamServiceImpl implements DreamService {
    private final DreamRepository dreamRepository;

    @Override
    public DreamDto getDreamById(Long id) {
        Dream dream = dreamRepository.findById(id);
        return DreamMapper.INSTANCE.dreamToDto(dream);
    }

    @Override
    public DreamDto getStartById(Long id) {
        Specification<Dream> specification = Specification.where(new Specification<Dream>() {
            @Override
            public Predicate toPredicate(Root<Dream> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("id"), id);
            }
        });

        Dream dream = dreamRepository.findOne(specification).orElseThrow();
        return DreamMapper.INSTANCE.dreamToDto(dream);
    }


    @Override
    public DreamDto getFinishById(Long id) {
        Dream dream = dreamRepository.findFinishById(id);
        return DreamMapper.INSTANCE.dreamToDto(dream);
    }


}
