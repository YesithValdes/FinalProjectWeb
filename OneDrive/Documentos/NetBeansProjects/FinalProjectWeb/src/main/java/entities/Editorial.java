/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "editorial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Editorial.findAll", query = "SELECT e FROM Editorial e"),
    @NamedQuery(name = "Editorial.findByEditorialId", query = "SELECT e FROM Editorial e WHERE e.editorialId = :editorialId"),
    @NamedQuery(name = "Editorial.findByName", query = "SELECT e FROM Editorial e WHERE e.name = :name"),
    @NamedQuery(name = "Editorial.findByCountry", query = "SELECT e FROM Editorial e WHERE e.country = :country")})
public class Editorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "editorial_id")
    private Integer editorialId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "country")
    private String country;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "editorial")
    private BookEditorial bookEditorial;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "editorial")
    private AuthorEditorial authorEditorial;

    public Editorial() {
    }

    public Editorial(Integer editorialId) {
        this.editorialId = editorialId;
    }

    public Editorial(Integer editorialId, String name, String country) {
        this.editorialId = editorialId;
        this.name = name;
        this.country = country;
    }

    public Integer getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(Integer editorialId) {
        this.editorialId = editorialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BookEditorial getBookEditorial() {
        return bookEditorial;
    }

    public void setBookEditorial(BookEditorial bookEditorial) {
        this.bookEditorial = bookEditorial;
    }

    public AuthorEditorial getAuthorEditorial() {
        return authorEditorial;
    }

    public void setAuthorEditorial(AuthorEditorial authorEditorial) {
        this.authorEditorial = authorEditorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (editorialId != null ? editorialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editorial)) {
            return false;
        }
        Editorial other = (Editorial) object;
        if ((this.editorialId == null && other.editorialId != null) || (this.editorialId != null && !this.editorialId.equals(other.editorialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Editorial[ editorialId=" + editorialId + " ]";
    }
    
}
