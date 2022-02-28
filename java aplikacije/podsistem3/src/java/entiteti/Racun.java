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
@Table(name = "racun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racun.findAll", query = "SELECT r FROM Racun r"),
    @NamedQuery(name = "Racun.findByBrojRacuna", query = "SELECT r FROM Racun r WHERE r.brojRacuna = :brojRacuna"),
    @NamedQuery(name = "Racun.findByStanje", query = "SELECT r FROM Racun r WHERE r.stanje = :stanje"),
    @NamedQuery(name = "Racun.findByDozvoljenMinus", query = "SELECT r FROM Racun r WHERE r.dozvoljenMinus = :dozvoljenMinus"),
    @NamedQuery(name = "Racun.findByStatus", query = "SELECT r FROM Racun r WHERE r.status = :status"),
    @NamedQuery(name = "Racun.findByDatum", query = "SELECT r FROM Racun r WHERE r.datum = :datum"),
    @NamedQuery(name = "Racun.findByVreme", query = "SELECT r FROM Racun r WHERE r.vreme = :vreme"),
    @NamedQuery(name = "Racun.findByBrojTransakcija", query = "SELECT r FROM Racun r WHERE r.brojTransakcija = :brojTransakcija"),
    @NamedQuery(name = "Racun.findByMesto", query = "SELECT r FROM Racun r WHERE r.mesto = :mesto"),
    @NamedQuery(name = "Racun.findByIdKomitent", query = "SELECT r FROM Racun r WHERE r.idKomitent = :idKomitent")})
public class Racun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "broj_racuna")
    private Integer brojRacuna;
    @Column(name = "stanje")
    private Integer stanje;
    @Column(name = "dozvoljen_minus")
    private Integer dozvoljenMinus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Column(name = "vreme")
    @Temporal(TemporalType.TIME)
    private Date vreme;
    @Column(name = "broj_transakcija")
    private Integer brojTransakcija;
    @Column(name = "mesto")
    private Integer mesto;
    @Column(name = "id_komitent")
    private Integer idKomitent;

    public Racun() {
    }

    public Racun(Integer brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Racun(Integer brojRacuna, String status) {
        this.brojRacuna = brojRacuna;
        this.status = status;
    }

    public Integer getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(Integer brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Integer getStanje() {
        return stanje;
    }

    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }

    public Integer getDozvoljenMinus() {
        return dozvoljenMinus;
    }

    public void setDozvoljenMinus(Integer dozvoljenMinus) {
        this.dozvoljenMinus = dozvoljenMinus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getBrojTransakcija() {
        return brojTransakcija;
    }

    public void setBrojTransakcija(Integer brojTransakcija) {
        this.brojTransakcija = brojTransakcija;
    }

    public Integer getMesto() {
        return mesto;
    }

    public void setMesto(Integer mesto) {
        this.mesto = mesto;
    }

    public Integer getIdKomitent() {
        return idKomitent;
    }

    public void setIdKomitent(Integer idKomitent) {
        this.idKomitent = idKomitent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojRacuna != null ? brojRacuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racun)) {
            return false;
        }
        Racun other = (Racun) object;
        if ((this.brojRacuna == null && other.brojRacuna != null) || (this.brojRacuna != null && !this.brojRacuna.equals(other.brojRacuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Racun[ brojRacuna=" + brojRacuna + " ]";
    }
    
}
