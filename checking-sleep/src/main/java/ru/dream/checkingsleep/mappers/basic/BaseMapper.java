package ru.dream.checkingsleep.mappers.basic;

public interface BaseMapper <DTO, ENTITY> {
    DTO toDto(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
