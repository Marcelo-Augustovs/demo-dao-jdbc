package application;

import db.DB;
import entities.Department;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBc;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = new DepartmentDaoJDBc(DB.getConnection());
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Test 1: Department findById ===");
        Department dep = departmentDao.findyById(2);
        System.out.println(dep);

        System.out.println("\n=== Test 2: Department findAll ===");
        List<Department> list = departmentDao.findAll();

        for(Department obj : list){
            System.out.println(obj);
        }

        System.out.println("\n=== Test 3: Department Insert ===");
        Department newDepartment = new Department(null,"d1");
        departmentDao.insert(newDepartment);
        System.out.println("Inserid! department Id= " + newDepartment.getId());

        System.out.println("\n=== Test 4: Department Update ===");
        dep = departmentDao.findyById(5);
        dep.setName("Automobiles");
        departmentDao.update(dep);
        System.out.println("Update Complete");

        System.out.println("\n=== Test 5: Department Delete ===");
        System.out.print("Enter id for delete test ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete complete");
    }
}
