/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import Entity.Department;
import Entity.Employee;
import Entity.Project;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface EmpEJBModuleLocal {
   
    //Department
    void addDepartment(String deptname);
    void updateDepartment(Integer deptID,String deptname);
    void removeDepartment(Integer deptID);
    
    Collection<Department> getAllDepartment();   
    Collection<Department> getDepartmentByDeptName(String deptName);   
    
    //Employee[one(Department) to many(Employees) Relationships]
    void addEmployeeToDepartment(String empName,Integer empSalary,Integer DeptID);
    void updateEmployeeToDepartment(Integer empID,String empName,Integer empSalary,Integer DeptID);
    void removeEmployeeToDepartment(Integer empID,Integer DeptID);
    
    Collection<Employee> getAllEmployee();
    Collection<Employee> getEmployeeByempName(String empName);
    Collection<Employee> getEmployeeByempSalary(Integer empSalary);
    Collection<Employee> getEmployeeByDepartment(Integer DeptID);
    
    //Project
    void addProject(String projName,Date projDeadline,Integer projCost); 
    void updateProject(Integer projID,String projName,Date projDeadline,Integer projCost); 
    void removeProject(Integer projID);
   
    Collection<Project> getAllProject();
    Collection<Project> getProjectByprojName(String projName);
    Collection<Project> getProjectByprojDeadline(Date projDeadline);
    Collection<Project> getProjectByprojCost(Integer projCost);
    
    //Many(Employees) To Many(Project) Relationship     
    void addEmployeeToProject(Integer projID,Collection<Integer> empIDs);
    void removeEmployeeToProject(Integer projID,Collection<Integer> empIDs);
        
}
