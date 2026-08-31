package com.paulovargas.api.repository;

import com.paulovargas.api.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByOrderByRootAscPositionAscNameAsc();

    @Query("select m from Client c join c.menus m where c.id = :clientId order by m.root asc, m.position asc, m.name asc")
    List<Menu> findByClientId(@Param("clientId") Long clientId);
}
