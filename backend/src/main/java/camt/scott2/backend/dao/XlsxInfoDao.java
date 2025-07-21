package camt.scott2.backend.dao;

import camt.scott2.backend.entity.XlsxInfo;

import java.util.List;

public interface XlsxInfoDao extends GenericDao<XlsxInfo, String> {
    List<XlsxInfo> findByCourseId(String courseId);
    List<XlsxInfo> findByYearAndSemester(int year, int semester);
    List<XlsxInfo> findByEmail(String email);
    List<XlsxInfo> findByFilename(String filename);
}