package com.data.repository;

import com.data.model.Resume;

import java.util.List;

public interface ResumeRepository {
    List<Resume> findAll();
    Resume findById(int id);
    boolean save(Resume resume);
    boolean update(Resume resume);
    boolean delete(int id);
}
