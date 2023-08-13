package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.model.Dream;
@Service
public interface TagService {

    TagDto getTagByDream(Dream dream);
}
