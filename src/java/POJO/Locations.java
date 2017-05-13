package POJO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe di mappa LOCATIONS
 * 
 */
@Entity
@Table(name = "LOCATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locations.findAll", query = "SELECT l FROM Locations l"),
    @NamedQuery(name = "Locations.findById", query = "SELECT l FROM Locations l WHERE l.id = :id"),
    @NamedQuery(name = "Locations.findByNome", query = "SELECT l FROM Locations l WHERE l.nome = :nome"),
    @NamedQuery(name = "Locations.findByDescrizione", query = "SELECT l FROM Locations l WHERE l.descrizione = :descrizione"),
    @NamedQuery(name = "Locations.findByProvincia", query = "SELECT l FROM Locations l WHERE l.provincia = :provincia"),
    @NamedQuery(name = "Locations.findByIndirizzo", query = "SELECT l FROM Locations l WHERE l.indirizzo = :indirizzo"),
    @NamedQuery(name = "Locations.findBySitoUfficiale", query = "SELECT l FROM Locations l WHERE l.sitoUfficiale = :sitoUfficiale")})
public class Locations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nome")
    private String nome;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Descrizione")
    private String descrizione;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Provincia")
    private String provincia;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Indirizzo")
    private String indirizzo;
    
    @Size(max = 100)
    @Column(name = "SitoUfficiale")
    private String sitoUfficiale;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", fetch = FetchType.EAGER)
    private List<Eventi> eventiList;

    public Locations() {
    }

    public Locations(Integer id) {
        this.id = id;
    }

    public Locations(Integer id, String nome, String descrizione, String provincia, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.provincia = provincia;
        this.indirizzo = indirizzo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSitoUfficiale() {
        return sitoUfficiale;
    }

    public void setSitoUfficiale(String sitoUfficiale) {
        this.sitoUfficiale = sitoUfficiale;
    }

    @XmlTransient
    public List<Eventi> getEventiList() {
        return eventiList;
    }

    public void setEventiList(List<Eventi> eventiList) {
        this.eventiList = eventiList;
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
        if (!(object instanceof Locations)) {
            return false;
        }
        Locations other = (Locations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Locations[ id=" + id + " ]";
    }
    
}
