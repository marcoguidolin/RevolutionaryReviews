/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FSEVERI\scolaro3313
 */
@Entity
@Table(name = "CATEGORIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findById", query = "SELECT c FROM Categorie c WHERE c.id = :id"),
    @NamedQuery(name = "Categorie.findByNome", query = "SELECT c FROM Categorie c WHERE c.nome = :nome"),
    @NamedQuery(name = "Categorie.findByDescrizione", query = "SELECT c FROM Categorie c WHERE c.descrizione = :descrizione"),
    @NamedQuery(name = "Categorie.findByIcona", query = "SELECT c FROM Categorie c WHERE c.icona = :icona")})
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Descrizione")
    private String descrizione;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Icona")
    private String icona;
    @JoinTable(name = "CATEGORIA_FOLLOWERS", joinColumns = {
        @JoinColumn(name = "Categoria", referencedColumnName = "Id")}, inverseJoinColumns = {
        @JoinColumn(name = "Followers", referencedColumnName = "Id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Followers> followersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<Eventi> eventiList;

    public Categorie() {
    }

    public Categorie(Integer id) {
        this.id = id;
    }

    public Categorie(Integer id, String nome, String descrizione, String icona) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.icona = icona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIcona() {
        return icona;
    }

    public void setIcona(String icona) {
        this.icona = icona;
    }

    @XmlTransient
    public List<Followers> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(List<Followers> followersList) {
        this.followersList = followersList;
    }

    @XmlTransient
    public List<Eventi> getEventiList() {
        return eventiList;
    }

    public void setEventiList(List<Eventi> eventiList) {
        this.eventiList = eventiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Categorie[ id=" + id + " ]";
    }
    
}
