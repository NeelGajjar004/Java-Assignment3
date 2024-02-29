/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmpID", query = "SELECT e FROM Employee e WHERE e.empID = :empID"),
    @NamedQuery(name = "Employee.findByEmpName", query = "SELECT e FROM Employee e WHERE e.empName = :empName"),
    @NamedQuery(name = "Employee.findByEmpSalary", query = "SELECT e FROM Employee e WHERE e.empSalary = :empSalary")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empID")
    private Integer empID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "empName")
    private String empName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "empSalary")
    private int empSalary;
    @JoinTable(name = "employee_project", joinColumns = {
        @JoinColumn(name = "empID", referencedColumnName = "empID")}, inverseJoinColumns = {
        @JoinColumn(name = "projID", referencedColumnName = "projID")})
    @ManyToMany
    private Collection<Project> projectCollection;
    @JoinColumn(name = "DeptID", referencedColumnName = "deptId")
    @ManyToOne(optional = false)
    private Department deptID;

    public Employee() {
    }

    public Employee(Integer empID) {
        this.empID = empID;
    }

    public Employee(Integer empID, String empName, int empSalary) {
        this.empID = empID;
        this.empName = empName;
        this.empSalary = empSalary;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(int empSalary) {
        this.empSalary = empSalary;
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Department getDeptID() {
        return deptID;
    }

    public void setDeptID(Department deptID) {
        this.deptID = deptID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empID != null ? empID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empID == null && other.empID != null) || (this.empID != null && !this.empID.equals(other.empID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Employee[ empID=" + empID + " ]";
    }
    
}
