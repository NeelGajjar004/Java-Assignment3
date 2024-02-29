/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import Entity.Department;
import Entity.Employee;
import Entity.Project;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class EmpEJBModule implements EmpEJBModuleLocal {
    
    @PersistenceContext(unitName = "ass3pu")
    EntityManager em;

    @Override
    public void addDepartment(String deptname) {
        Department d = new Department();
        d.setDeptName(deptname);
        em.persist(d);
    }

    @Override
    public void updateDepartment(Integer deptID, String deptname) {
        Department d = (Department) em.find(Department.class, deptID);
        d.setDeptName(deptname);
        em.merge(d);
    }

    @Override
    public void removeDepartment(Integer deptID) {
        Department d = (Department) em.find(Department.class, deptID);
        em.remove(d);
    }

    @Override
    public Collection<Department> getAllDepartment() {
        return em.createNamedQuery("Department.findAll").getResultList();
    }

    @Override
    public Collection<Department> getDepartmentByDeptName(String deptName) {
        return em.createNamedQuery("Department.findByDeptName").setParameter("deptName", deptName).getResultList();
    }

    @Override
    public void addEmployeeToDepartment(String empName, Integer empSalary, Integer DeptID) {
        
        Department d = (Department) em.find(Department.class, DeptID);
        Collection<Employee> employees = d.getEmployeeCollection();
        
        Employee e = new Employee();
        e.setEmpName(empName);
        e.setEmpSalary(empSalary);
        e.setDeptID(d);
        
        employees.add(e);
        d.setEmployeeCollection(employees);
        
        em.persist(e);
        em.merge(d);
        
    }

    @Override
    public void updateEmployeeToDepartment(Integer empID, String empName, Integer empSalary, Integer DeptID) {
        Department d = (Department) em.find(Department.class, DeptID);
        Collection<Employee> employees = d.getEmployeeCollection();
        
        Employee e = (Employee) em.find(Employee.class, empID);
        
        if(employees.contains(e))
        {
            e.setEmpName(empName);
            e.setEmpSalary(empSalary);
            e.setDeptID(d);
            
            employees.remove(e);
            d.setEmployeeCollection(employees);
        
            em.merge(e);
            em.merge(d);
        }
        
        
    }

    @Override
    public void removeEmployeeToDepartment(Integer empID, Integer DeptID) {
        Department d = (Department) em.find(Department.class, DeptID);
        Collection<Employee> employees = d.getEmployeeCollection();
        
        Employee e = (Employee) em.find(Employee.class, DeptID);
        
        if(employees.contains(e))
        {
            employees.remove(e);
            d.setEmployeeCollection(employees);
            em.remove(e);
        }

    }

    @Override
    public Collection<Employee> getAllEmployee() {
        return em.createNamedQuery("Employee.findAll").getResultList();
    }

    @Override
    public Collection<Employee> getEmployeeByempName(String empName) {
        return em.createNamedQuery("Employee.findByEmpName").setParameter("empName", empName).getResultList();
    }

    @Override
    public Collection<Employee> getEmployeeByempSalary(Integer empSalary) {
        return em.createNamedQuery("Employee.findByEmpSalary").setParameter("empSalary", empSalary).getResultList();
    }

    @Override
    public Collection<Employee> getEmployeeByDepartment(Integer DeptID) {
        Department d = (Department) em.find(Department.class, DeptID);
        return d.getEmployeeCollection();
    }

    @Override
    public void addProject(String projName, Date projDeadline, Integer projCost) {
        Project p = new Project();
        p.setProjName(projName);
        p.setPorjDeadline(projDeadline);
        p.setProjCost(projCost);
        em.persist(p);
    }

    @Override
    public void updateProject(Integer projID, String projName, Date projDeadline, Integer projCost) {
        Project p = (Project) em.find(Project.class, projID);
        p.setProjName(projName);
        p.setPorjDeadline(projDeadline);
        p.setProjCost(projCost);
        em.merge(p);
    }

    @Override
    public void removeProject(Integer projID) {
        Project p = (Project) em.find(Project.class, projID);
        em.remove(p);
    }

    @Override
    public Collection<Project> getAllProject() {
        return em.createNamedQuery("Project.findAll").getResultList();
    }

    @Override
    public Collection<Project> getProjectByprojName(String projName) {
        return em.createNamedQuery("Project.findByProjName").setParameter("projName", projName).getResultList();
    }

    @Override
    public Collection<Project> getProjectByprojDeadline(Date projDeadline) {
        return em.createNamedQuery("Project.findByPorjDeadline").setParameter("projDeadline", projDeadline).getResultList();
    }

    @Override
    public Collection<Project> getProjectByprojCost(Integer projCost) {
        return em.createNamedQuery("Project.findByProjCost").setParameter("projCost", projCost).getResultList();
    }

    @Override
    public void addEmployeeToProject(Integer projID, Collection<Integer> empIDs) {
        Project p = (Project) em.find(Project.class, projID);
        Collection<Employee> employees = p.getEmployeeCollection();
        
        for(Integer empID : empIDs)
        {
            Employee e = (Employee) em.find(Employee.class, empID);
            
            if(!employees.contains(e))
            {
                Collection<Project> projects = e.getProjectCollection();
                employees.add(e);
                projects.add(p);
            
                e.setProjectCollection(projects);
                p.setEmployeeCollection(employees);
                
                em.merge(e);
            }
        }
    }

    @Override
    public void removeEmployeeToProject(Integer projID, Collection<Integer> empIDs) {
        Project p = (Project) em.find(Project.class, projID);
        Collection<Employee> employees = p.getEmployeeCollection();
        
        for(Integer empID : empIDs)
        {
            Employee e = (Employee) em.find(Employee.class, empID);
            
            if(employees.contains(e))
            {
                Collection<Project> projects = e.getProjectCollection();
                employees.remove(e);
                projects.remove(p);
            
                e.setProjectCollection(projects);
                p.setEmployeeCollection(employees);
                
                em.merge(e);
            }
        }
    }

}
