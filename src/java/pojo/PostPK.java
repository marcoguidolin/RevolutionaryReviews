/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author FSEVERI\parlato2889
 */
@Embeddable
public class PostPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Membro")
    private String membro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Evento")
    private int evento;

    public PostPK()
    {
    }

    public PostPK(String membro, int evento)
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

    public int getEvento()
    {
        return evento;
    }

    public void setEvento(int evento)
    {
        this.evento = evento;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (membro != null ? membro.hashCode() : 0);
        hash += (int) evento;
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
        if (this.evento != other.evento)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "pojo.PostPK[ membro=" + membro + ", evento=" + evento + " ]";
    }
    
}
