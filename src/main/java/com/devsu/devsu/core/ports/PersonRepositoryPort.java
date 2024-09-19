package com.devsu.devsu.core.ports;

import com.devsu.devsu.infrastructure.repository.entities.PersonEntity;

public interface PersonRepositoryPort {

    Long create(PersonEntity person);

}
