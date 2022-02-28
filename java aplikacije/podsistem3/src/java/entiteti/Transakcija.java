/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author virtuel
 */
@Entity
@Table(name = "transakcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcija.findAll", query = "SELECT t FROM Transakcija t"),
    @NamedQuery(name = "Transakcija.findByIdTransakcije", query = "SELECT t FROM Transakcija t WHERE t.idTransakcije = :idTransakcije"),
    @NamedQuery(name = "Transakcija.findByDatum", query = "SELECT t FROM Transakcija t WHERE t.datum = :datum"),
    @NamedQuery(name = "Transakcija.findByVreme", query = "SELECT t FROM Transakcija t WHERE t.vreme = :vreme"),
    @NamedQuery(name = "Transakcija.findByIznos", query = "SELECT t FROM Transakcija t WHERE t.iznos = :iznos"),
    @NamedQuery(name = "Transakcija.findByRbRacuna", query = "SELECT t FROM Transakcija t WHERE t.rbRacuna = :rbRacuna"),
    @NamedQuery(name = "Transakcija.findBySvrha", query = "SELECT t FROM Transakcija t WHERE t.svrha = :svrha"),
    @NamedQuery(name = "Transakcija.findByIdRacun", query = "SELECT t FROM Transakcija t WHERE t.idRacun = :idRacun"),
    @NamedQuery(name = "Transakcija.findByTipTrans", query = "SELECT t FROM Transakcija t WHERE t.tipTrans = :tipTrans"),
    @NamedQuery(name = "Transakcija.findByNaRacun", query = "SELECT t FROM Transakcija t WHERE t.naRacun = :naRacun"),
    @NamedQuery(name = "Transakcija.findByIdFil", query = "SELECT t FROM Transakcija t WHERE t.idFil = :idFil")})
public class Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transakcije")
    private Integer idTransakcije;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Column(name = "vreme")
    @Temporal(TemporalType.TIME)
    private Date vreme;
    @Column(name = "iznos")
    private Integer iznos;
    @Column(name = "rb_racuna")
    private Integer rbRacuna;
    @Size(max = 45)
    @Column(name = "svrha")
    private String svrha;
    @Column(name = "id_racun")
    private Integer idRacun;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipTrans")
    private String tipTrans;
    @Column(name = "naRacun")
    private Integer naRacun;
    @Column(name = "idFil")
    private Integer idFil;

    public Transakcija() {
    }

    public Transakcija(Integer idTransakcije) {
        this.idTransakcije = idTransakcije;
    }

    public Transakcija(Integer idTransakcije, String tipTrans) {
        this.idTransakcije = idTransakcije;
        this.tipTrans = tipTrans;
    }

    public Integer getIdTransakcije() {
        return idTransakcije;
    }

    public void setIdTransakcije(Integer idTransakcije) {
        this.idTransakcije = idTransakcije;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public Integer getIznos() {
        return iznos;
    }

    public void setIznos(Integer iznos) {
        this.iznos = iznos;
    }

    public Integer getRbRacuna() {
        return rbRacuna;
    }

    public void setRbRacuna(Integer rbRacuna) {
        this.rbRacuna = rbRacuna;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public Integer getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(Integer idRacun) {
        this.idRacun = idRacun;
    }

    public String getTipTrans() {
        return tipTrans;
    }

    public void setTipTrans(String tipTrans) {
        this.tipTrans = tipTrans;
    }

    public Integer getNaRacun() {
        return naRacun;
    }

    public void setNaRacun(Integer naRacun) {
        this.naRacun = naRacun;
    }

    public Integer getIdFil() {
        return idFil;
    }

    public void setIdFil(Integer idFil) {
        this.idFil = idFil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransakcije != null ? idTransakcije.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcija)) {
            return false;
        }
        Transakcija other = (Transakcija) object;
        if ((this.idTransakcije == null && other.idTransakcije != null) || (this.idTransakcije != null && !this.idTransakcije.equals(other.idTransakcije))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Transakcija[ idTransakcije=" + idTransakcije + " ]";
    }
    
}
