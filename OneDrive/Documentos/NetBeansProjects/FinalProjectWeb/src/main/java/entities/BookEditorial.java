/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "book_editorial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookEditorial.findAll", query = "SELECT b FROM BookEditorial b"),
    @NamedQuery(name = "BookEditorial.findByBookId", query = "SELECT b FROM BookEditorial b WHERE b.bookEditorialPK.bookId = :bookId"),
    @NamedQuery(name = "BookEditorial.findByEditorialId", query = "SELECT b FROM BookEditorial b WHERE b.bookEditorialPK.editorialId = :editorialId")})
public class BookEditorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BookEditorialPK bookEditorialPK;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Books books;
    @JoinColumn(name = "editorial_id", referencedColumnName = "editorial_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Editorial editorial;

    public BookEditorial() {
    }

    public BookEditorial(BookEditorialPK bookEditorialPK) {
        this.bookEditorialPK = bookEditorialPK;
    }

    public BookEditorial(int bookId, int editorialId) {
        this.bookEditorialPK = new BookEditorialPK(bookId, editorialId);
    }

    public BookEditorialPK getBookEditorialPK() {
        return bookEditorialPK;
    }

    public void setBookEditorialPK(BookEditorialPK bookEditorialPK) {
        this.bookEditorialPK = bookEditorialPK;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookEditorialPK != null ? bookEditorialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookEditorial)) {
            return false;
        }
        BookEditorial other = (BookEditorial) object;
        if ((this.bookEditorialPK == null && other.bookEditorialPK != null) || (this.bookEditorialPK != null && !this.bookEditorialPK.equals(other.bookEditorialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BookEditorial[ bookEditorialPK=" + bookEditorialPK + " ]";
    }
    
}
