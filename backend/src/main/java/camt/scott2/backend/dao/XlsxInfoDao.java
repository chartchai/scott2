package camt.scott2.backend.dao;

import camt.scott2.backend.entity.XlsxInfo;
import reactor.core.publisher.Flux;

public interface XlsxInfoDao extends GenericDao<XlsxInfo, String> {
    Flux<XlsxInfo> findByCourseId(String courseId);
    Flux<XlsxInfo> findByYearAndSemester(int year, int semester);
    Flux<XlsxInfo> findByEmail(String email);
    Flux<XlsxInfo> findByFilename(String filename);
}