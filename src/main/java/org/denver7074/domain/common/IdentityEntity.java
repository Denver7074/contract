package org.denver7074.domain.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class IdentityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
