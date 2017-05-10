package POJO;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "VISTAVOTI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vistavoti.findAll", query = "SELECT v FROM Vistavoti v"),
    @NamedQuery(name = "Vistavoti.findById", query = "SELECT v FROM Vistavoti v WHERE v.id = :id"),
    @NamedQuery(name = "Vistavoti.findByMedia", query = "SELECT v FROM Vistavoti v WHERE v.media = :media")})
public class Vistavoti implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    @Size(min = 1, max = 50)
    private int id;
    
    @Column(name = "Media")
    private BigDecimal media;

    public Vistavoti() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(BigDecimal media) {
        this.media = media;
    }
    
}
