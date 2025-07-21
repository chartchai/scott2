package camt.scott2.backend.repository;

import camt.scott2.backend.entity.XlsxInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XlsxInfoRepository extends MongoRepository<XlsxInfo, String> {
    List<XlsxInfo> findByCourseId(String courseId);
    List<XlsxInfo> findByYearAndSemester(int year, int semester);
    List<XlsxInfo> findByEmail(String email);
    List<XlsxInfo> findByFilename(String filename);
}