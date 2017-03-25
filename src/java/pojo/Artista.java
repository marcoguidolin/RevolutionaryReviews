package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matte
 */
@Entity
@Table(name = "artisti")
public class Artista implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    
    @NotNull
    @Column(name = "Nome")
    private String nome;
    
    @Column(name = "Cognome")
    private String cognome;
    
    @ManyToMany(mappedBy = "artistaList", fetch = FetchType.EAGER)
    private List<Evento> eventoList;

    public Artista()
    {
    }

    public Artista(String id)
    {
        this.id = id;
    }

    public Artista(String id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public String getCognome()
    {
        return cognome;
    }

    public void setCognome(String cognome)
    {
        this.cognome = cognome;
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
        if (!(object instanceof Artista))
        {
            return false;
        }
        Artista other = (Artista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Artista{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", eventoList=" + eventoList + '}';
    }

    
}
