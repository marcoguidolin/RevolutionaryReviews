/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "MEMBRI")
public class Membri implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "Nickname")
    private String nickname;
    
    @NotNull
    @Column(name = "Password")
    private String password;
    
    @NotNull
    @Column(name = "Nome")
    private String nome;
    
    @NotNull
    @Column(name = "Cognome")
    private String cognome;
    
    @NotNull
    @Column(name = "Mail")
    private String mail;
    
    @JoinTable(name = "MEMBRO_CATEGORIA", joinColumns =
    {
        @JoinColumn(name = "Membro", referencedColumnName = "Nickname")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Categoria", referencedColumnName = "Id")
    })
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categorie> categorieList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membri", fetch = FetchType.EAGER)
    private List<Post> postList;

    public Membri()
    {
    }

    public Membri(String nickname)
    {
        this.nickname = nickname;
    }

    public Membri(String nickname, String password, String nome, String cognome, String mail)
    {
        this.nickname = nickname;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    @XmlTransient
    public List<Categorie> getCategorieList()
    {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList)
    {
        this.categorieList = categorieList;
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
        hash += (nickname != null ? nickname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membri))
        {
            return false;
        }
        Membri other = (Membri) object;
        if ((this.nickname == null && other.nickname != null) || (this.nickname != null && !this.nickname.equals(other.nickname)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "pojo.Membri[ nickname=" + nickname + " ]";
    }
    
}
