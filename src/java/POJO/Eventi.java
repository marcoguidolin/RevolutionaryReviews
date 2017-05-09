/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FSEVERI\guidolin3172
 */
@Entity
@Table(name = "EVENTI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventi.findAll", query = "SELECT e FROM Eventi e"),
    @NamedQuery(name = "Eventi.findById", query = "SELECT e FROM Eventi e WHERE e.id = :id"),
    @NamedQuery(name = "Eventi.findByTitolo", query = "SELECT e FROM Eventi e WHERE e.titolo = :titolo"),
    @NamedQuery(name = "Eventi.findByData", query = "SELECT e FROM Eventi e WHERE e.data = :data"),
    @NamedQuery(name = "Eventi.findByProgramma", query = "SELECT e FROM Eventi e WHERE e.programma = :programma"),
    @NamedQuery(name = "Eventi.findByDescrizione", query = "SELECT e FROM Eventi e WHERE e.descrizione = :descrizione"),
    @NamedQuery(name = "Eventi.findByDurata", query = "SELECT e FROM Eventi e WHERE e.durata = :durata"),
    @NamedQuery(name = "Eventi.findBySponsor", query = "SELECT e FROM Eventi e WHERE e.sponsor = :sponsor"),
    @NamedQuery(name = "Eventi.findBySocial1", query = "SELECT e FROM Eventi e WHERE e.social1 = :social1"),
    @NamedQuery(name = "Eventi.findBySocial2", query = "SELECT e FROM Eventi e WHERE e.social2 = :social2"),
    @NamedQuery(name = "Eventi.findByBiglietti", query = "SELECT e FROM Eventi e WHERE e.biglietti = :biglietti")})
public class Eventi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Titolo")
    private String titolo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Programma")
    private String programma;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Descrizione")
    private String descrizione;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Durata")
    private int durata;
    
    @Size(max = 50)
    @Column(name = "Sponsor")
    private String sponsor;
   
    @Size(max = 100)
    @Column(name = "Social1")
    private String social1;
    
    @Size(max = 100)
    @Column(name = "Social2")
    private String social2;
    
    @Size(max = 200)
    @Column(name = "Biglietti")
    private String biglietti;
    
    @ManyToMany(mappedBy = "eventiList")
    private List<Artisti> artistiList;
    
    @JoinColumn(name = "UtenteCreatore", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Followers utenteCreatore;
    
    @JoinColumn(name = "Categoria", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Categorie categoria;
    
    @JoinColumn(name = "Location", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Locations location;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventi")
    private List<Recensioni> recensioniList;

    public Eventi() {
    }

    public Eventi(Integer id) {
        this.id = id;
    }

    public Eventi(Integer id, String titolo, Date data, String programma, String descrizione, int durata) {
        this.id = id;
        this.titolo = titolo;
        this.data = data;
        this.programma = programma;
        this.descrizione = descrizione;
        this.durata = durata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getProgramma() {
        return programma;
    }

    public void setProgramma(String programma) {
        this.programma = programma;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getSocial1() {
        return social1;
    }

    public void setSocial1(String social1) {
        this.social1 = social1;
    }

    public String getSocial2() {
        return social2;
    }

    public void setSocial2(String social2) {
        this.social2 = social2;
    }

    public String getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(String biglietti) {
        this.biglietti = biglietti;
    }

    @XmlTransient
    public List<Artisti> getArtistiList() {
        return artistiList;
    }

    public void setArtistiList(List<Artisti> artistiList) {
        this.artistiList = artistiList;
    }

    public Followers getUtenteCreatore() {
        return utenteCreatore;
    }

    public void setUtenteCreatore(Followers utenteCreatore) {
        this.utenteCreatore = utenteCreatore;
    }

    public Categorie getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorie categoria) {
        this.categoria = categoria;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
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
        if (!(object instanceof Eventi)) {
            return false;
        }
        Eventi other = (Eventi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "POJO.Eventi[ id=" + id + " ]";
    }
    
}
