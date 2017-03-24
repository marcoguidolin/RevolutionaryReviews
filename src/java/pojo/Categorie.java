/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

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
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "CATEGORIE")

public class Categorie implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    
    @NotNull
    @Column(name = "Nome")
    private String nome;
    
    @ManyToMany(mappedBy = "categorieList", fetch = FetchType.EAGER)
    private List<Membri> membriList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<Eventi> eventiList;

    public Categorie()
    {
    }

    public Categorie(Integer id)
    {
        this.id = id;
    }

    public Categorie(Integer id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @XmlTransient
    public List<Membri> getMembriList()
    {
        return membriList;
    }

    public void setMembriList(List<Membri> membriList)
    {
        this.membriList = membriList;
    }

    @XmlTransient
    public List<Eventi> getEventiList()
    {
        return eventiList;
    }

    public void setEventiList(List<Eventi> eventiList)
    {
        this.eventiList = eventiList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie))
        {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "pojo.Categorie[ id=" + id + " ]";
    }
    
}
