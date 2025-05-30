package com.data.service;

import com.data.model.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> findAll();
    Resume findById(int id);
    boolean save(Resume resume);
    boolean update(Resume resume);
    boolean delete(int id);
}
