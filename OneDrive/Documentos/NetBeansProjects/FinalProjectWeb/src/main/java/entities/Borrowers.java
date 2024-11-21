/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "borrowers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Borrowers.findAll", query = "SELECT b FROM Borrowers b"),
    @NamedQuery(name = "Borrowers.findByBorrowerId", query = "SELECT b FROM Borrowers b WHERE b.borrowerId = :borrowerId"),
    @NamedQuery(name = "Borrowers.findByName", query = "SELECT b FROM Borrowers b WHERE b.name = :name"),
    @NamedQuery(name = "Borrowers.findByBorrowedDate", query = "SELECT b FROM Borrowers b WHERE b.borrowedDate = :borrowedDate"),
    @NamedQuery(name = "Borrowers.findByReturnDate", query = "SELECT b FROM Borrowers b WHERE b.returnDate = :returnDate")})
public class Borrowers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "borrower_id")
    private Integer borrowerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "borrowed_date")
    @Temporal(TemporalType.DATE)
    private Date borrowedDate;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne
    private Books bookId;

    public Borrowers() {
    }

    public Borrowers(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Borrowers(Integer borrowerId, String name) {
        this.borrowerId = borrowerId;
        this.name = name;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (borrowerId != null ? borrowerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Borrowers)) {
            return false;
        }
        Borrowers other = (Borrowers) object;
        if ((this.borrowerId == null && other.borrowerId != null) || (this.borrowerId != null && !this.borrowerId.equals(other.borrowerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Borrowers[ borrowerId=" + borrowerId + " ]";
    }
    
}
