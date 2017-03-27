package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matte
 */
@Entity
@Table(name = "EVENTI")
public class Evento implements Serializable
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
    
    @JoinTable(name = "evento_artista", joinColumns =
    {
        @JoinColumn(name = "Evento", referencedColumnName = "Id")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Artista", referencedColumnName = "Id")
    })
    @ManyToMany
    private List<Artista> artistaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento1")
    private List<Post> postList;
    
    @JoinColumn(name = "Categoria", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Categoria categoria;

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

    public List<Artista> getArtistaList()
    {
        return artistaList;
    }

    public void setArtistaList(List<Artista> artistaList)
    {
        this.artistaList = artistaList;
    }

    public List<Post> getPostList()
    {
        return postList;
    }

    public void setPostList(List<Post> postList)
    {
        this.postList = postList;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
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
        return "Evento{" + "id=" + id + ", titolo=" + titolo + ", luogo=" + luogo + ", data=" + data + ", artistaList=" + artistaList + ", postList=" + postList + ", categoria=" + categoria + '}';
    }

}
