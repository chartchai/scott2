package camt.scott2.backend.dao.impl;

import camt.scott2.backend.dao.XlsxInfoDao;
import camt.scott2.backend.entity.XlsxInfo;
import camt.scott2.backend.repository.XlsxInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class XlsxInfoDaoImpl implements XlsxInfoDao {
    
    private final XlsxInfoRepository xlsxInfoRepository;

    @Override
    public Mono<XlsxInfo> save(XlsxInfo entity) {
        return xlsxInfoRepository.save(entity);
    }

    @Override
    public Mono<XlsxInfo> findById(String id) {
        return xlsxInfoRepository.findById(id);
    }

    @Override
    public Flux<XlsxInfo> findAll() {
        return xlsxInfoRepository.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return xlsxInfoRepository.deleteById(id);
    }

    @Override
    public Mono<Void> delete(XlsxInfo entity) {
        return xlsxInfoRepository.delete(entity);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return xlsxInfoRepository.existsById(id);
    }

    @Override
    public Mono<Long> count() {
        return xlsxInfoRepository.count();
    }

    @Override
    public Flux<XlsxInfo> findByCourseId(String courseId) {
        return xlsxInfoRepository.findByCourseId(courseId);
    }

    @Override
    public Flux<XlsxInfo> findByYearAndSemester(int year, int semester) {
        return xlsxInfoRepository.findByYearAndSemester(year, semester);
    }

    @Override
    public Flux<XlsxInfo> findByEmail(String email) {
        return xlsxInfoRepository.findByEmail(email);
    }

    @Override
    public Flux<XlsxInfo> findByFilename(String filename) {
        return xlsxInfoRepository.findByFilename(filename);
    }
}