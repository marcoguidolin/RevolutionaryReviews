/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "ARTISTI")
@NamedQueries(
{
    @NamedQuery(name = "Artista.findAll", query = "SELECT a FROM Artista a")
})
public class Artista implements Serializable
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
    @Size(max = 30)
    @Column(name = "Cognome")
    private String cognome;
    @Lob
    @Column(name = "Immagine")
    private byte[] immagine;
    @ManyToMany(mappedBy = "artistaList")
    private List<Evento> eventoList;

    public Artista()
    {
    }

    public Artista(Integer id)
    {
        this.id = id;
    }

    public Artista(Integer id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public Artista(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
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

    public byte[] getImmagine()
    {
        return immagine;
    }

    public void setImmagine(byte[] immagine)
    {
        this.immagine = immagine;
    }
    
    public String getImmagineString()
    {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(immagine);
    }

    @Override
    public String toString()
    {
        return "Artista{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", immagine=" + immagine + ", eventoList=" + eventoList + '}';
    }
    
}
