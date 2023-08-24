package ru.dream.checkingsleep.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.DreamMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.repository.DreamRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DreamServiceImpl implements DreamService {
    private final DreamRepository dreamRepository;
    private final DreamMapper dreamMapper;

    @Override
    public DreamDto getDreamById(UUID id) {
        Dream dream = dreamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return dreamMapper.toDto(dream);
    }

    @Override
    public List<LocalDateTime> getDayStartById(UUID id) {
        return Collections.singletonList(dreamRepository.findDayStartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found")));
    }

    @Override
    public List<LocalDateTime> getDayFinishById(UUID id) {
        return Collections.singletonList(dreamRepository.findDayFinishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found")));
    }

    @Override
    public List<LocalDateTime> getNightStartById(UUID id) {
        return Collections.singletonList(dreamRepository.findNightStartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found")));
    }

    @Override
    public List<LocalDateTime> getNightFinishById(UUID id) {
        return Collections.singletonList(dreamRepository.findNightFinishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found")));
    }

    @Override
    public DreamCreateDto createDream(DreamCreateDto dreamCreateDto) {
        Dream dream = dreamRepository.save(dreamMapper.toCreateEntity(dreamCreateDto));
        return dreamMapper.toCreateDto(dream);
    }

    @Override
    public DreamUpdateDto updateDream(DreamUpdateDto dreamUpdateDto) {
        Dream dream = dreamRepository.findById(dreamUpdateDto.getId()).orElseThrow();
        DreamUpdateDto dto = dreamMapper.toUpdateDto(dream);
        Dream dream1 = dreamMapper.toUpdateEntity(dto);
        Dream dream2 = dreamRepository.save(dream1);
 /*     dream.setDayStart(dreamUpdateDto.getDayStart());
        dream.setDayFinish(dreamUpdateDto.getDayFinish());
        dream.setNightStart(dreamUpdateDto.getNightStart());
        dream.setNightFinish(dreamUpdateDto.getNightFinish());
        var comment1 = dreamUpdateDto.getComment();
        var commentModel = commentMapper.toUpdateEntity(comment1);
        dream.setComment(commentModel);
        var tag1 = dreamUpdateDto.getTag();
        var tagModel = tagMapper.toUpdateEntity(tag1);
        dream.setTags(Collections.singletonList(tagModel));
        Dream savedDream = dreamRepository.save(dream); */
        return dreamMapper.toUpdateDto(dream2);
    }

    @Override
    public void deleteDream(UUID id) {
        dreamRepository.deleteById(id);
    }

    @Override
    public List<DreamDto> getAllDreams() {
        List<Dream> dreams = dreamRepository.findAll();
        return dreams.stream()
                .map(dreamMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DreamDto> getDreamByUser(UserDto user) {
        List<Dream> dream = dreamRepository.findByUser(user);
        return dream.stream()
                .map(dreamMapper::toDto)
                .collect(Collectors.toList());
    }

 /*  @Override
    public Map<LocalDateTime, Double> getDiagrams(LocalDateTime dayStart, LocalDateTime dayFinish) {
        List<Dream> diagrams = dreamRepository.findAllByDayStartIsBeforeAndDayFinishIsAfter(dayStart, dayFinish);
        diagrams.stream()
                .map(dreamMapper::toDto)
                .collect()
       return null;
   }*/

}
