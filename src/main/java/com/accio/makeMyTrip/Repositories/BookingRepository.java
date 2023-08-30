package com.accio.makeMyTrip.Repositories;

import com.accio.makeMyTrip.Entities.Booking;
import com.accio.makeMyTrip.Entities.Transport;
import com.accio.makeMyTrip.Entities.User;
import com.accio.makeMyTrip.Enums.ModeOfTp;
import com.accio.makeMyTrip.ResponseDto.GetAvailableSeatsRespDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

//    List<Transport> findTransportByCompanyNameAndModeOfTp(String companyNameInput, ModeOfTp modeOfTpInput);
    @Query(value = "select * from booking where journey_date =:journeyDateInput and transport321_trans_id =:transportIdInput", nativeQuery = true)
    public List<Booking> findBooking(LocalDate journeyDateInput,Integer transportIdInput);

    public List<Booking> findBookingByJourneyDateAndUser(LocalDate date, User user);
}
