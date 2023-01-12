/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.finalproject;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "ktp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ktp.findAll", query = "SELECT k FROM Ktp k"),
    @NamedQuery(name = "Ktp.findById", query = "SELECT k FROM Ktp k WHERE k.id = :id"),
    @NamedQuery(name = "Ktp.findByNama", query = "SELECT k FROM Ktp k WHERE k.nama = :nama"),
    @NamedQuery(name = "Ktp.findByAddress", query = "SELECT k FROM Ktp k WHERE k.address = :address"),
    @NamedQuery(name = "Ktp.findByNik", query = "SELECT k FROM Ktp k WHERE k.nik = :nik")})
public class Ktp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "NIK")
    private String nik;

    public Ktp() {
    }

    public Ktp(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
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
        if (!(object instanceof Ktp)) {
            return false;
        }
        Ktp other = (Ktp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.c.finalproject.Ktp[ id=" + id + " ]";
    }
    
}
