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
@Table(name = "author_editorial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthorEditorial.findAll", query = "SELECT a FROM AuthorEditorial a"),
    @NamedQuery(name = "AuthorEditorial.findByAuthorId", query = "SELECT a FROM AuthorEditorial a WHERE a.authorEditorialPK.authorId = :authorId"),
    @NamedQuery(name = "AuthorEditorial.findByEditorialId", query = "SELECT a FROM AuthorEditorial a WHERE a.authorEditorialPK.editorialId = :editorialId")})
public class AuthorEditorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AuthorEditorialPK authorEditorialPK;
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Authors authors;
    @JoinColumn(name = "editorial_id", referencedColumnName = "editorial_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Editorial editorial;

    public AuthorEditorial() {
    }

    public AuthorEditorial(AuthorEditorialPK authorEditorialPK) {
        this.authorEditorialPK = authorEditorialPK;
    }

    public AuthorEditorial(int authorId, int editorialId) {
        this.authorEditorialPK = new AuthorEditorialPK(authorId, editorialId);
    }

    public AuthorEditorialPK getAuthorEditorialPK() {
        return authorEditorialPK;
    }

    public void setAuthorEditorialPK(AuthorEditorialPK authorEditorialPK) {
        this.authorEditorialPK = authorEditorialPK;
    }

    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
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
        hash += (authorEditorialPK != null ? authorEditorialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorEditorial)) {
            return false;
        }
        AuthorEditorial other = (AuthorEditorial) object;
        if ((this.authorEditorialPK == null && other.authorEditorialPK != null) || (this.authorEditorialPK != null && !this.authorEditorialPK.equals(other.authorEditorialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AuthorEditorial[ authorEditorialPK=" + authorEditorialPK + " ]";
    }
    
}
