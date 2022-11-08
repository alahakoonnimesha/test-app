package com.nishiProductions.TestApplication.repository.transformer;

public interface BaseTransformer <T, I>{
    I transformEntityToDto(T type);

    T transformDtoToEntity(I type);
}
