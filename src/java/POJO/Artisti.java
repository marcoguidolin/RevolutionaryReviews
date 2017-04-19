/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FSEVERI\guidolin3172
 */
@Entity
@Table(name = "ARTISTI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artisti.findAll", query = "SELECT a FROM Artisti a"),
    @NamedQuery(name = "Artisti.findById", query = "SELECT a FROM Artisti a WHERE a.id = :id"),
    @NamedQuery(name = "Artisti.findByNome", query = "SELECT a FROM Artisti a WHERE a.nome = :nome"),
    @NamedQuery(name = "Artisti.findByDescrizione", query = "SELECT a FROM Artisti a WHERE a.descrizione = :descrizione"),
    @NamedQuery(name = "Artisti.findBySitoUfficiale", query = "SELECT a FROM Artisti a WHERE a.sitoUfficiale = :sitoUfficiale"),
    @NamedQuery(name = "Artisti.findBySocial", query = "SELECT a FROM Artisti a WHERE a.social = :social")})
public class Artisti implements Serializable {
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
    @Size(min = 1, max = 500)
    @Column(name = "Descrizione")
    private String descrizione;
    @Size(max = 100)
    @Column(name = "SitoUfficiale")
    private String sitoUfficiale;
    @Size(max = 100)
    @Column(name = "Social")
    private String social;
    @JoinTable(name = "EVENTI_ARTISTI", joinColumns = {
        @JoinColumn(name = "Artisti", referencedColumnName = "Id")}, inverseJoinColumns = {
        @JoinColumn(name = "Eventi", referencedColumnName = "Id")})
    @ManyToMany
    private List<Eventi> eventiList;

    public Artisti() {
    }

    public Artisti(Integer id) {
        this.id = id;
    }

    public Artisti(Integer id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
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

    public String getSitoUfficiale() {
        return sitoUfficiale;
    }

    public void setSitoUfficiale(String sitoUfficiale) {
        this.sitoUfficiale = sitoUfficiale;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
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
        if (!(object instanceof Artisti)) {
            return false;
        }
        Artisti other = (Artisti) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "POJO.Artisti[ id=" + id + " ]";
    }
    
}
