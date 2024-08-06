package ait.employee_company.dao;


import ait.employee_company.model.Employee;
import ait.employee_company.model.SalesManager;
import ait.employee_company.model.Worker;

public class CompanyImpl implements Company{

    private Employee[] employees; // array for objects
    private int size; // current size of array

    //constructor
    public CompanyImpl(int capacity) {
        employees = new Employee[capacity]; // this. - не обязательно
        // this.size = 0;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if(employee == null){
            return false;
        }
        if(employees.length == size){
            return false;
        }
        if(findEmployee(employee.getId()) != null){
            return false;
        }
        employees[size] = employee;
        size++;
        return true;
    }

    @Override
    public Employee findEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if(employees[i].getId() == id){
                return employees[i];
            }
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == employee.getId()) {
                employees[i] = employee;
                return employee;
            }
        }
        return null;
    }

    @Override
    public Employee revoveEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if(employees[i].getId() == id){
                Employee victim = employees[i];
                employees[i] = employees[size - 1];
                employees[size - 1] = null;
                size--;
                return victim;
            }
        }
        return null;
    }

    @Override
    public void printEmployee() {
        for (int i = 0; i < size; i++) {
            System.out.println( employees[i] );
        }
    }

    @Override
    public int quantity() {
        return size;
    }

    @Override
    public double totalSalary() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += employees[i].salary();
        }
        return sum;
    }

    @Override
    public double totalSales() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            if(employees[i] instanceof SalesManager sm){
               sum += sm.getSalesValue();
            }
        }
        return sum;
    }

    @Override
    public Employee[] findEmployeeGrateHoursThan(double hours) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if(employees[i].getHours() > hours){
                count++;
            }
        }
        Employee[] res = new Employee[count];
        for (int i = 0, j = 0; j < res.length; i++) {
            if(employees[i].getHours() > hours){
                res[j++] = employees[i];
            }
            // j++;
        }
        return res;
    }
}
