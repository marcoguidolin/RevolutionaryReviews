/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "EVENTI")
public class Eventi implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    
    @NotNull
    @Column(name = "Titolo")
    private String titolo;
    
    @NotNull
    @Column(name = "Luogo")
    private String luogo;
    
    @NotNull
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @JoinTable(name = "EVENTO_ARTISTA", joinColumns =
    {
        @JoinColumn(name = "Evento", referencedColumnName = "Id")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Artista", referencedColumnName = "Id")
    })
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Artisti> artistiList;
    
    @JoinColumn(name = "Categoria", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Categorie categoria;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventi", fetch = FetchType.EAGER)
    private List<Post> postList;

    public Eventi()
    {
    }

    public Eventi(Integer id)
    {
        this.id = id;
    }

    public Eventi(Integer id, String titolo, String luogo, Date data)
    {
        this.id = id;
        this.titolo = titolo;
        this.luogo = luogo;
        this.data = data;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitolo()
    {
        return titolo;
    }

    public void setTitolo(String titolo)
    {
        this.titolo = titolo;
    }

    public String getLuogo()
    {
        return luogo;
    }

    public void setLuogo(String luogo)
    {
        this.luogo = luogo;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    @XmlTransient
    public List<Artisti> getArtistiList()
    {
        return artistiList;
    }

    public void setArtistiList(List<Artisti> artistiList)
    {
        this.artistiList = artistiList;
    }

    public Categorie getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categorie categoria)
    {
        this.categoria = categoria;
    }

    @XmlTransient
    public List<Post> getPostList()
    {
        return postList;
    }

    public void setPostList(List<Post> postList)
    {
        this.postList = postList;
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
        if (!(object instanceof Eventi))
        {
            return false;
        }
        Eventi other = (Eventi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "pojo.Eventi[ id=" + id + " ]";
    }
    
}
