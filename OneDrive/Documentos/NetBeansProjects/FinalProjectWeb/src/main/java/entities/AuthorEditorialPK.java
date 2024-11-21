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
public class AuthorEditorialPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "author_id")
    private int authorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "editorial_id")
    private int editorialId;

    public AuthorEditorialPK() {
    }

    public AuthorEditorialPK(int authorId, int editorialId) {
        this.authorId = authorId;
        this.editorialId = editorialId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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
        hash += (int) authorId;
        hash += (int) editorialId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorEditorialPK)) {
            return false;
        }
        AuthorEditorialPK other = (AuthorEditorialPK) object;
        if (this.authorId != other.authorId) {
            return false;
        }
        if (this.editorialId != other.editorialId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AuthorEditorialPK[ authorId=" + authorId + ", editorialId=" + editorialId + " ]";
    }
    
}
