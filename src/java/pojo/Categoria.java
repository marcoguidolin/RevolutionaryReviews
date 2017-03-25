package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matte
 */
@Entity
@Table(name = "categorie")
public class Categoria implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    
    @NotNull
    @Column(name = "Nome")
    private String nome;
    
    @ManyToMany(mappedBy = "categoriaList", fetch = FetchType.EAGER)
    private List<Membro> membroList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<Evento> eventoList;

    public Categoria()
    {
    }

    public Categoria(String id)
    {
        this.id = id;
    }

    public Categoria(String id, String nome)
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
        return "Categoria{" + "id=" + id + ", nome=" + nome + ", membroList=" + membroList + ", eventoList=" + eventoList + '}';
    }

    
}
