package com.accio.makeMyTrip.Repositories;

import com.accio.makeMyTrip.Entities.Route;
import com.accio.makeMyTrip.Enums.City;
import com.accio.makeMyTrip.Enums.ModeOfTp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

//    This is not working in my intelliJ
//    List<Route> findRouteBySourceAndDestAndModeOfTP(City source, City dest, ModeOfTp modeOfTp);

//    But this is working because we have to write same name of columns but we can make first letter capiatal
//    and can skip '_'
    List<Route> findRouteBySourceAndDestAndModeOfTp(City source, City dest, ModeOfTp modeOfTp);
    List<Route> findRouteBySourceAndDest(City source, City dest);



}
