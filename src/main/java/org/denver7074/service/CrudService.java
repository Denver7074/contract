package org.denver7074.service;


import org.denver7074.domain.common.IdentityEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrudService {

    <T> T save(T entity);

    <T> List<T> findAll(Class<T> clazz);

    <T, ID extends Number> void delete(Class<T> clazz, ID id);

    <T, ID extends Number> T findById(Class<T> clazz, ID id);

}
