package POJO;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




/**
 *
 * @author FSEVERI\scolaro3313
 */
@Entity
@Table(name = "VISTAVOTI")
public class VistaVoti implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "Id")
    @Id
    private Integer id;
    @Column(name = "Media")
    private double media;
 
    public VistaVoti() {
    }

    public VistaVoti(Integer id) {
        this.id = id;
    }

    public VistaVoti(Integer id, double media) {
        this.id = id;
        this.media = media;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VistaVoti other = (VistaVoti) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (Double.doubleToLongBits(this.media) != Double.doubleToLongBits(other.media)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VistaVoti{" + "id=" + id + ", media=" + media + '}';
    }

   
}
