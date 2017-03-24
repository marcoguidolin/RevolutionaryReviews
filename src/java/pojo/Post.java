/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "POST")
public class Post implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected PostPK postPK;
    
    @NotNull
    @Column(name = "Commento")
    private String commento;
    
    @NotNull
    @Column(name = "Voto")
    private int voto;
    
    @JoinColumn(name = "Membro", referencedColumnName = "Nickname", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Membri membri;
    
    @JoinColumn(name = "Evento", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Eventi eventi;

    public Post()
    {
    }

    public Post(PostPK postPK)
    {
        this.postPK = postPK;
    }

    public Post(PostPK postPK, String commento, int voto)
    {
        this.postPK = postPK;
        this.commento = commento;
        this.voto = voto;
    }

    public Post(String membro, int evento)
    {
        this.postPK = new PostPK(membro, evento);
    }

    public PostPK getPostPK()
    {
        return postPK;
    }

    public void setPostPK(PostPK postPK)
    {
        this.postPK = postPK;
    }

    public String getCommento()
    {
        return commento;
    }

    public void setCommento(String commento)
    {
        this.commento = commento;
    }

    public int getVoto()
    {
        return voto;
    }

    public void setVoto(int voto)
    {
        this.voto = voto;
    }

    public Membri getMembri()
    {
        return membri;
    }

    public void setMembri(Membri membri)
    {
        this.membri = membri;
    }

    public Eventi getEventi()
    {
        return eventi;
    }

    public void setEventi(Eventi eventi)
    {
        this.eventi = eventi;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (postPK != null ? postPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post))
        {
            return false;
        }
        Post other = (Post) object;
        if ((this.postPK == null && other.postPK != null) || (this.postPK != null && !this.postPK.equals(other.postPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "pojo.Post[ postPK=" + postPK + " ]";
    }
    
}
