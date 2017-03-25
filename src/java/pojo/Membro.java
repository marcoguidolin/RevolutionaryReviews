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

/**
 *
 * @author matte
 */
@Entity
@Table(name = "membri")
public class Membro implements Serializable
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
    
    @JoinTable(name = "membro_categoria", joinColumns =
    {
        @JoinColumn(name = "Membro", referencedColumnName = "Nickname")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Categoria", referencedColumnName = "Id")
    })
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categoria> categoriaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membro1", fetch = FetchType.EAGER)
    private List<Post> postList;

    public Membro()
    {
    }

    public Membro(String nickname)
    {
        this.nickname = nickname;
    }

    public Membro(String nickname, String password, String nome, String cognome, String mail)
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

    public List<Categoria> getCategoriaList()
    {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList)
    {
        this.categoriaList = categoriaList;
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
        hash += (nickname != null ? nickname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membro))
        {
            return false;
        }
        Membro other = (Membro) object;
        if ((this.nickname == null && other.nickname != null) || (this.nickname != null && !this.nickname.equals(other.nickname)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Membro{" + "nickname=" + nickname + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome + ", mail=" + mail + ", categoriaList=" + categoriaList + ", postList=" + postList + '}';
    }
    

}
