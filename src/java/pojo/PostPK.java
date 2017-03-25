package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matte
 */
@Embeddable
public class PostPK implements Serializable
{

    @NotNull
    @Column(name = "Membro")
    private String membro;
    
    @NotNull
    @Column(name = "Evento")
    private String evento;

    public PostPK()
    {
    }

    public PostPK(String membro, String evento)
    {
        this.membro = membro;
        this.evento = evento;
    }

    public String getMembro()
    {
        return membro;
    }

    public void setMembro(String membro)
    {
        this.membro = membro;
    }

    public String getEvento()
    {
        return evento;
    }

    public void setEvento(String evento)
    {
        this.evento = evento;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (membro != null ? membro.hashCode() : 0);
        hash += (evento != null ? evento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostPK))
        {
            return false;
        }
        PostPK other = (PostPK) object;
        if ((this.membro == null && other.membro != null) || (this.membro != null && !this.membro.equals(other.membro)))
        {
            return false;
        }
        if ((this.evento == null && other.evento != null) || (this.evento != null && !this.evento.equals(other.evento)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "PostPK{" + "membro=" + membro + ", evento=" + evento + '}';
    }

    
    
}
