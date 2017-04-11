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

/**
 *
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "CATEGORIE")
@NamedQueries(
{
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
})
public class Categoria implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nome")
    private String nome;
    @Size(max = 100)
    @Column(name = "Immagine")
    private String immagine;
    @ManyToMany(mappedBy = "categoriaList")
    private List<Membro> membroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<Evento> eventoList;

    public Categoria()
    {
    }

    public Categoria(Integer id)
    {
        this.id = id;
    }

    public Categoria(String nome, String immagine) {
        this.nome = nome;
        this.immagine = immagine;
    }

    public Categoria(Integer id, String nome)
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

    public String getImmagine()
    {
        return immagine;
    }

    public void setImmagine(String immagine)
    {
        this.immagine = immagine;
    }

    public List<Membro> getMembroList()
    {
        return membroList;
    }

    public void setMembroList(List<Membro> membroList)
    {
        this.membroList = membroList;
    }

    public List<Evento> getEventoList()
    {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList)
    {
        this.eventoList = eventoList;
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
        if (!(object instanceof Categoria))
        {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "pojo.Categoria[ id=" + id + " ]";
    }
    
}
