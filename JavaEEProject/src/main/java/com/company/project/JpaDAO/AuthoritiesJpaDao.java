package com.company.project.JpaDAO;

import com.company.project.Models.AuthoritiesDTO;

public interface AuthoritiesJpaDao {
    void add(AuthoritiesDTO a);
    void remove(AuthoritiesDTO a);
    AuthoritiesDTO get(long id);
    void update(AuthoritiesDTO a);
}
