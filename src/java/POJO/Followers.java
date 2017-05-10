/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FSEVERI\guidolin3172
 */
@Entity
@Table(name = "FOLLOWERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Followers.findAll", query = "SELECT f FROM Followers f"),
    @NamedQuery(name = "Followers.findById", query = "SELECT f FROM Followers f WHERE f.id = :id"),
    @NamedQuery(name = "Followers.findByNickname", query = "SELECT f FROM Followers f WHERE f.nickname = :nickname"),
    @NamedQuery(name = "Followers.findByPassword", query = "SELECT f FROM Followers f WHERE f.password = :password"),
    @NamedQuery(name = "Followers.findByNome", query = "SELECT f FROM Followers f WHERE f.nome = :nome"),
    @NamedQuery(name = "Followers.findByCognome", query = "SELECT f FROM Followers f WHERE f.cognome = :cognome"),
    @NamedQuery(name = "Followers.findByProvincia", query = "SELECT f FROM Followers f WHERE f.provincia = :provincia"),
    @NamedQuery(name = "Followers.findByEMail", query = "SELECT f FROM Followers f WHERE f.eMail = :eMail"),
    @NamedQuery(name = "Followers.findByIcona", query = "SELECT f FROM Followers f WHERE f.icona = :icona")})
public class Followers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nickname")
    private String nickname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Cognome")
    private String cognome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Provincia")
    private String provincia;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMail")
    private String eMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Icona")
    private String icona;
    @ManyToMany(mappedBy = "followersList")
    private List<Categorie> categorieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utenteCreatore")
    private List<Eventi> eventiList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente")
    private List<Recensioni> recensioniList;

    public Followers() {
    }

    public Followers(Integer id) {
        this.id = id;
    }

    public Followers(Integer id, String nickname, String password, String nome, String cognome, String provincia, String eMail, String icona) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.provincia = provincia;
        this.eMail = eMail;
        this.icona = icona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getIcona() {
        return icona;
    }

    public void setIcona(String icona) {
        this.icona = icona;
    }

    @XmlTransient
    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    @XmlTransient
    public List<Eventi> getEventiList() {
        return eventiList;
    }

    public void setEventiList(List<Eventi> eventiList) {
        this.eventiList = eventiList;
    }

    @XmlTransient
    public List<Recensioni> getRecensioniList() {
        return recensioniList;
    }

    public void setRecensioniList(List<Recensioni> recensioniList) {
        this.recensioniList = recensioniList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Followers)) {
            return false;
        }
        Followers other = (Followers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "POJO.Followers[ id=" + id + " ]";
    }
    
}
