package com.company.project;


import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;

import java.awt.print.Book;
import java.util.LinkedList;
import java.util.List;

//W warstwie wyzszej trzeba bedzie przetwarzac haslo na hasz oraz
// tworzyć powiązania np  dodawać danemu użytkownikowi zamówienie a zamówieniu użytkownika
//Do tej warstwy trafia obiekt który jest już gotowy do zapisu w bazie
public class TestMain {
    public static void main(String[] args) {

        BookJpaDAO bookDao = new BookHibernateDAO();
        BookDTO b1 = new BookDTO("title1","author1","category1", BookDTO.rentalTime.SEVENDAYS,10);
        BookDTO b2 = new BookDTO("title2","author1","category2", BookDTO.rentalTime.ONEDAY,2);
        BookDTO b3 = new BookDTO("title3","author1","category3", BookDTO.rentalTime.THREEMONTHS,1);
        BookDTO b4 = new BookDTO("title4","author2","category4", BookDTO.rentalTime.SEVENDAYS,0);

        List<BookDTO> dtoList = new LinkedList<>();
        dtoList.add(b1);
        dtoList.add(b2);
        dtoList.add(b3);




        bookDao.add(b1);
        bookDao.add(b2);
        bookDao.add(b3);
        bookDao.add(b4);

        System.out.println(dtoList);



        List<BookDTO> result= bookDao.findByAuthor("author1");
        System.out.println(result);












    }
}
