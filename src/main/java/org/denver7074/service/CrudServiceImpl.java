package org.denver7074.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CrudServiceImpl implements CrudService {

    EntityManager entityManager;

    @Override
    public <T> T save(T entity) {
        T merge = entityManager.merge(entity);
        return CastUtils.cast(merge);
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        return entityManager.createQuery("select e from "
                + clazz.getSimpleName() + " e", clazz).getResultList();
    }

    @Override
    public <T, ID extends Number> void delete(Class<T> clazz, ID id) {
        entityManager.remove(findById(clazz, id));
    }

    @Override
    public <T, ID extends Number> T findById(Class<T> clazz, ID id) {
        return entityManager.find(clazz, id);
    }

}
