/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "EVENTI")
@NamedQueries(
{
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
})
public class Evento implements Serializable
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
    @Column(name = "Titolo")
    private String titolo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Luogo")
    private String luogo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Size(max = 1024)
    @Column(name = "Descrizione")
    private String descrizione;
    @Size(max = 100)
    @Column(name = "Immagine")
    private String immagine;
    @JoinTable(name = "EVENTO_ARTISTA", joinColumns =
    {
        @JoinColumn(name = "Evento", referencedColumnName = "Id")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Artista", referencedColumnName = "Id")
    })
    @ManyToMany
    private List<Artista> artistaList;
    @JoinColumn(name = "Categoria", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento1")
    private List<Post> postList;

    public Evento()
    {
    }

    public Evento(Integer id)
    {
        this.id = id;
    }

    public Evento(Integer id, String titolo, String luogo, Date data)
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

    public String getDescrizione(){
        return descrizione;
    }
    
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }
    
    public String getImmagine()
    {
        return immagine;
    }

    public void setImmagine(String immagine)
    {
        this.immagine = immagine;
    }

    public List<Artista> getArtistaList()
    {
        return artistaList;
    }

    public void setArtistaList(List<Artista> artistaList)
    {
        this.artistaList = artistaList;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

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
        if (!(object instanceof Evento))
        {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "pojo.Evento[ id=" + id + " ]";
    }
    
}
