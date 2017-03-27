package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matte
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
    
    @JoinColumn(name = "Membro", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membro membro1;
    
    @JoinColumn(name = "Evento", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento1;

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

    public Membro getMembro1()
    {
        return membro1;
    }

    public void setMembro1(Membro membro1)
    {
        this.membro1 = membro1;
    }

    public Evento getEvento1()
    {
        return evento1;
    }

    public void setEvento1(Evento evento1)
    {
        this.evento1 = evento1;
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
        return "Post{" + "postPK=" + postPK + ", commento=" + commento + ", voto=" + voto + ", membro1=" + membro1 + ", evento1=" + evento1 + '}';
    }
    
}
