package model.dao.impl;

import db.DB;
import db.DbException;
import entities.Department;
import model.dao.DepartmentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBc implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBc(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                    + "(Name) "
                    + "VALUES "
                    + "(?) ",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowAffected = st.executeUpdate();

            if(rowAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }else {
                throw new DbException("Unexpected erro! no rows Affected!");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                    + "SET Name = ? "
                    + "WHERE department.Id = ?");

            st.setInt(2,obj.getId());
            st.setString(1,obj.getName());

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE Id = ?");

            st.setInt(1,id);
            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findyById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department "
                    + "WHERE department.id = ?");

            st.setInt(1,id);
            rs = st.executeQuery();

            if (rs.next()){
               Department dep = new Department();
               dep.setName(rs.getString("Name"));
               dep.setId(rs.getInt("Id"));
               return dep;
            }else {
                return null;
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Department> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department "
                            + "ORDER by Name");

            rs = st.executeQuery();

            while (rs.next()) {
                Department dep = new Department();
                dep.setName(rs.getString("Name"));
                dep.setId(rs.getInt("Id"));
                list.add(dep);
            }
            return list;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
