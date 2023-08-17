package ru.dream.checkingsleep.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.DreamMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.Tag;
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
    public DreamDto getDreamById(Long id) {
        Dream dream = dreamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return dreamMapper.toDto(dream);
    }

    @Override
    public List<LocalDateTime> getDayStartById(Long id) {
        List<LocalDateTime> localDateTime = Collections.singletonList(dreamRepository.findDayStartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found")));
        return (List<LocalDateTime>) dreamMapper.toDto((Dream) localDateTime);
    }

    @Override
    public List<LocalDateTime> getDayFinishById(Long id) {
        List<LocalDateTime> localDateTime = Collections.singletonList(dreamRepository.findDayFinishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found")));
        return (List<LocalDateTime>) dreamMapper.toDto((Dream) localDateTime);
    }

    @Override
    public DreamDto createDream(DreamCreateDto dreamCreateDto) {
        Dream dream = dreamRepository.save(dreamMapper.toEntity(dreamCreateDto));
        return dreamMapper.toDto(dream);
    }
    @Override
    public DreamDto updateDream(DreamUpdateDto dreamUpdateDto) {
        Dream dream = dreamRepository.findById(dreamUpdateDto.getId()).orElseThrow();
        dream.setDayStart(dreamUpdateDto.getDayStart());
        dream.setDayFinish(dreamUpdateDto.getDayFinish());
        dream.setNightStart(dreamUpdateDto.getNightStart());
        dream.setNightFinish(dreamUpdateDto.getNightFinish());
        dream.setComment(dreamUpdateDto.getComment());
        dream.setTags((List<Tag>) dreamUpdateDto.getTag());

        Dream savedDream = dreamRepository.save(dream);
        return dreamMapper.toDto(savedDream);
    }
    @Override
    public void deleteDream (UUID id) {
        dreamRepository.deleteById(id);
    }

    @Override
    public List<DreamDto> getAllDreams() {
        List<Dream> dreams = dreamRepository.findAll();
        return dreams.stream().map(this::).collect(Collectors.toList());
    }

    @Override
    public List<DreamDto> getDreamByUser(UserDto user) {
        Dream dream = dreamRepository.findByUser(user));
        return dreamMapper.toDto(dream);

    }

 /*   @Override
    public List<DreamDto> getAllDreams() {
        List<Dream> dreams = dreamRepository.findAll();
        return dreams.stream().map(this::dreamMapper.toDto()).collect(Collectors.toList());
    }*/
}
