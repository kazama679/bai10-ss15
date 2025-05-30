package com.data.repository;

import com.data.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumeRepositoryImp implements ResumeRepository{
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Resume> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Resume> resumes = new ArrayList<Resume>();
        try{
            conn = dataSource.getConnection();
            callSt = conn.prepareCall("{call display_resume()}");
            ResultSet rs = callSt.executeQuery();
            while(rs.next()){
                Resume resume = new Resume();
                resume.setId(rs.getInt("id"));
                resume.setFullName(rs.getString("fullName"));
                resume.setEmail(rs.getString("email"));
                resume.setPhoneNumber(rs.getString("phoneNumber"));
                resume.setEducation(rs.getString("education"));
                resume.setExperience(rs.getString("experience"));
                resume.setSkills(rs.getString("skills"));
                resumes.add(resume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resumes;
    }

    @Override
    public Resume findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Resume resume = new Resume();
        try {
            conn = dataSource.getConnection();
            callSt = conn.prepareCall("{call find_by_resume(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                resume.setId(rs.getInt("id"));
                resume.setFullName(rs.getString("fullName"));
                resume.setEmail(rs.getString("email"));
                resume.setPhoneNumber(rs.getString("phoneNumber"));
                resume.setEducation(rs.getString("education"));
                resume.setExperience(rs.getString("experience"));
                resume.setSkills(rs.getString("skills"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resume;
    }

    @Override
    public boolean save(Resume resume) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = dataSource.getConnection();
            callSt = conn.prepareCall("{call add_resume(?,?,?,?,?,?)}");
            callSt.setString(1, resume.getFullName());
            callSt.setString(2, resume.getEmail());
            callSt.setString(3, resume.getPhoneNumber());
            callSt.setString(4, resume.getEducation());
            callSt.setString(5, resume.getExperience());
            callSt.setString(6, resume.getSkills());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Resume resume) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = dataSource.getConnection();
            callSt = conn.prepareCall("{call update_resume(?,?,?,?,?,?,?)}");
            callSt.setInt(1, resume.getId());
            callSt.setString(2, resume.getFullName());
            callSt.setString(3, resume.getEmail());
            callSt.setString(4, resume.getPhoneNumber());
            callSt.setString(5, resume.getEducation());
            callSt.setString(6, resume.getExperience());
            callSt.setString(7, resume.getSkills());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = dataSource.getConnection();
            callSt = conn.prepareCall("{call delete_resume(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
