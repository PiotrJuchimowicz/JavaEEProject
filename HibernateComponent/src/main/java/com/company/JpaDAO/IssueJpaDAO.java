package com.company.JpaDAO;

import com.company.Models.IssueDTO;

import java.util.List;

public interface IssueJpaDAO {
    void add(IssueDTO i);

    IssueDTO get(long id);

    void update(IssueDTO i);

    void remove(IssueDTO i);

    //Znajduje wypożyczenia książki o danym id
    List<IssueDTO> findIssuesOfThisBook(long id);

    List<IssueDTO> findIssuesByThisUser(long id);

    List<IssueDTO> findAllIssues();

    //Znajduje rezerwacje i tylko rezerwacje(jeśli książka została wypożyczona to nie będzie uwzględniona w wynikach) danej książki
    List<IssueDTO> findReservationsOfThisBook(long id);

    List<IssueDTO> findReservationsByThisUser(long id);

    List<IssueDTO> findAllReservations();
    //podajesz date i zwraca stringa



}
