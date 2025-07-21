package camt.scott2.backend.dao.impl;

import camt.scott2.backend.dao.XlsxInfoDao;
import camt.scott2.backend.entity.XlsxInfo;
import camt.scott2.backend.repository.XlsxInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class XlsxInfoDaoImpl implements XlsxInfoDao {
    
    private final XlsxInfoRepository xlsxInfoRepository;

    @Override
    public XlsxInfo save(XlsxInfo entity) {
        return xlsxInfoRepository.save(entity);
    }

    @Override
    public Optional<XlsxInfo> findById(String id) {
        return xlsxInfoRepository.findById(id);
    }

    @Override
    public List<XlsxInfo> findAll() {
        return xlsxInfoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        xlsxInfoRepository.deleteById(id);
    }

    @Override
    public void delete(XlsxInfo entity) {
        xlsxInfoRepository.delete(entity);
    }

    @Override
    public boolean existsById(String id) {
        return xlsxInfoRepository.existsById(id);
    }

    @Override
    public long count() {
        return xlsxInfoRepository.count();
    }

    @Override
    public List<XlsxInfo> findByCourseId(String courseId) {
        return xlsxInfoRepository.findByCourseId(courseId);
    }

    @Override
    public List<XlsxInfo> findByYearAndSemester(int year, int semester) {
        return xlsxInfoRepository.findByYearAndSemester(year, semester);
    }

    @Override
    public List<XlsxInfo> findByEmail(String email) {
        return xlsxInfoRepository.findByEmail(email);
    }

    @Override
    public List<XlsxInfo> findByFilename(String filename) {
        return xlsxInfoRepository.findByFilename(filename);
    }
}