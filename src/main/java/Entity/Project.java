/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "project")
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByProjID", query = "SELECT p FROM Project p WHERE p.projID = :projID"),
    @NamedQuery(name = "Project.findByProjName", query = "SELECT p FROM Project p WHERE p.projName = :projName"),
    @NamedQuery(name = "Project.findByPorjDeadline", query = "SELECT p FROM Project p WHERE p.porjDeadline = :porjDeadline"),
    @NamedQuery(name = "Project.findByProjCost", query = "SELECT p FROM Project p WHERE p.projCost = :projCost")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projID")
    private Integer projID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "projName")
    private String projName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porjDeadline")
    @Temporal(TemporalType.DATE)
    private Date porjDeadline;
    @Basic(optional = false)
    @NotNull
    @Column(name = "projCost")
    private int projCost;
    @ManyToMany(mappedBy = "projectCollection")
    private Collection<Employee> employeeCollection;

    public Project() {
    }

    public Project(Integer projID) {
        this.projID = projID;
    }

    public Project(Integer projID, String projName, Date porjDeadline, int projCost) {
        this.projID = projID;
        this.projName = projName;
        this.porjDeadline = porjDeadline;
        this.projCost = projCost;
    }

    public Integer getProjID() {
        return projID;
    }

    public void setProjID(Integer projID) {
        this.projID = projID;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public Date getPorjDeadline() {
        return porjDeadline;
    }

    public void setPorjDeadline(Date porjDeadline) {
        this.porjDeadline = porjDeadline;
    }

    public int getProjCost() {
        return projCost;
    }

    public void setProjCost(int projCost) {
        this.projCost = projCost;
    }

    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projID != null ? projID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projID == null && other.projID != null) || (this.projID != null && !this.projID.equals(other.projID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Project[ projID=" + projID + " ]";
    }
    
}
