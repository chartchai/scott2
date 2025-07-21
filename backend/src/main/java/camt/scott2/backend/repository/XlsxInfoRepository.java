package camt.scott2.backend.repository;

import camt.scott2.backend.entity.XlsxInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface XlsxInfoRepository extends ReactiveMongoRepository<XlsxInfo, String> {
    Flux<XlsxInfo> findByCourseId(String courseId);
    Flux<XlsxInfo> findByYearAndSemester(int year, int semester);
    Flux<XlsxInfo> findByEmail(String email);
    Flux<XlsxInfo> findByFilename(String filename);
}