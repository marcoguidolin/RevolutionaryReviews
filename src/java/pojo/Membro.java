/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author FSEVERI\parlato2889
 */
@Entity
@Table(name = "MEMBRI")
@NamedQueries({
    @NamedQuery(name = "Membro.findAll", query = "SELECT m FROM Membro m")})
public class Membro implements Serializable {
    @Lob
    @Column(name = "Avatar")
    private byte[] avatar;

    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 50)
    @Column(name = "Zona")
    private String zona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotore", fetch = FetchType.EAGER)
    private List<Evento> eventoList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Cognome")
    private String cognome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Mail")
    private String mail;
    @JoinTable(name = "MEMBRO_CATEGORIA", joinColumns = {
        @JoinColumn(name = "Membro", referencedColumnName = "Username")}, inverseJoinColumns = {
        @JoinColumn(name = "Categoria", referencedColumnName = "Id")})
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categoria> categoriaList;
    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membro1", fetch = FetchType.EAGER, orphanRemoval=true)
    private List<Post> postList;

    public Membro() {
    }

    public Membro(String username) {
        this.username = username;
    }

    public Membro(String username, String password, String nome, String cognome, String mail, String zona) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.zona = zona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    
    public String getAvatarString()
    {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(avatar);
    }


    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membro)) {
            return false;
        }
        Membro other = (Membro) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Membro{" + "username=" + username + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome + ", mail=" + mail + ", avatar=" + avatar + ", categoriaList=" + categoriaList + ", postList=" + postList + '}';
    }

    public String getZona()
    {
        return zona;
    }

    public void setZona(String zona)
    {
        this.zona = zona;
    }


    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
    
}
