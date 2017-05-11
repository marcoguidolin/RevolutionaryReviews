/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author FSEVERI\scolaro3313
 */
@Entity
@Table(name = "RECENSIONI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recensioni.findAll", query = "SELECT r FROM Recensioni r"),
    @NamedQuery(name = "Recensioni.findById", query = "SELECT r FROM Recensioni r WHERE r.id = :id"),
    @NamedQuery(name = "Recensioni.findByVotoEvento", query = "SELECT r FROM Recensioni r WHERE r.votoEvento = :votoEvento"),
    @NamedQuery(name = "Recensioni.findByCommento", query = "SELECT r FROM Recensioni r WHERE r.commento = :commento"),
    @NamedQuery(name = "Recensioni.findByVotoPos", query = "SELECT r FROM Recensioni r WHERE r.votoPos = :votoPos"),
    @NamedQuery(name = "Recensioni.findByVotoNeg", query = "SELECT r FROM Recensioni r WHERE r.votoNeg = :votoNeg")})
public class Recensioni implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VotoEvento")
    private int votoEvento;
    @Size(max = 1000)
    @Column(name = "Commento")
    private String commento;
    @Column(name = "VotoPos")
    private Integer votoPos;
    @Column(name = "VotoNeg")
    private Integer votoNeg;
    @JoinColumn(name = "Evento", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Eventi evento;
    @JoinColumn(name = "Utente", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Followers utente;

    public Recensioni() {
    }

    public Recensioni(Integer id) {
        this.id = id;
    }

    public Recensioni(Integer id, int votoEvento) {
        this.id = id;
        this.votoEvento = votoEvento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVotoEvento() {
        return votoEvento;
    }

    public void setVotoEvento(int votoEvento) {
        this.votoEvento = votoEvento;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public Integer getVotoPos() {
        return votoPos;
    }

    public void setVotoPos(Integer votoPos) {
        this.votoPos = votoPos;
    }

    public Integer getVotoNeg() {
        return votoNeg;
    }

    public void setVotoNeg(Integer votoNeg) {
        this.votoNeg = votoNeg;
    }

    public Eventi getEvento() {
        return evento;
    }

    public void setEvento(Eventi evento) {
        this.evento = evento;
    }

    public Followers getUtente() {
        return utente;
    }

    public void setUtente(Followers utente) {
        this.utente = utente;
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
        if (!(object instanceof Recensioni)) {
            return false;
        }
        Recensioni other = (Recensioni) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Recensioni[ id=" + id + " ]";
    }
    
}
