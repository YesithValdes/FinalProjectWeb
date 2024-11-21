/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alexis
 */
@Embeddable
public class BookEditorialPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "book_id")
    private int bookId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "editorial_id")
    private int editorialId;

    public BookEditorialPK() {
    }

    public BookEditorialPK(int bookId, int editorialId) {
        this.bookId = bookId;
        this.editorialId = editorialId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(int editorialId) {
        this.editorialId = editorialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) bookId;
        hash += (int) editorialId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookEditorialPK)) {
            return false;
        }
        BookEditorialPK other = (BookEditorialPK) object;
        if (this.bookId != other.bookId) {
            return false;
        }
        if (this.editorialId != other.editorialId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BookEditorialPK[ bookId=" + bookId + ", editorialId=" + editorialId + " ]";
    }
    
}
