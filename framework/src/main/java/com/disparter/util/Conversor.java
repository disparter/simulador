package com.disparter.util;

import javax.inject.Singleton;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;
import org.dozer.loader.api.TypeMappingOptions;

@Singleton
public final class Conversor {
    public <S, T> T converter(S entidade, Class<T> tipoVO) {
        if (entidade == null) {
            return null;
        }

        DozerBeanMapper mapper = new DozerBeanMapper();

        return mapper.map(entidade, tipoVO);
    }

    public <S, T> T converter(S entidade, Class<T> tipoVO, BeanMappingBuilder builder) {
        if (entidade == null) {
            return null;
        }

        DozerBeanMapper mapper = new DozerBeanMapper();

        mapper.addMapping(builder);

        return mapper.map(entidade, tipoVO);
    }

    public <S, T> T converter(S entidade, Class<T> tipoVO, String[] excludeFields) {
        if (entidade == null) {
            return null;
        }
        
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                TypeMappingBuilder file = mapping(entidade.getClass(), tipoVO,
                        new TypeMappingOption[] { TypeMappingOptions.oneWay(), TypeMappingOptions.mapNull(true) });

                for (String field : excludeFields) {
                    file.exclude(field);
                }
            }
        };
        return converter(entidade, tipoVO, builder);
    }
}