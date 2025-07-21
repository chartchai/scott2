package camt.scott2.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Document(collection = "xlsx_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class XlsxInfo {
    @Id
    private String id;
    
    private List<LinkedHashMap<String, String>> xlsxData;
    
    private List<List<String>> rawXlsxData;
    
    private String filename;
    
    private String courseId;
    
    private String courseName;
    
    private int year;
    
    private int semester;
    
    private int sheetNo;
    
    private int titleRow;
    
    private int startRow;
    
    private int endRow;
    
    private String studentIdCol;
    
    private String totalScoreCol;
    
    private double fileSize;
    
    private String uploadDate;
    
    private String imgHtml;
    
    private String email;
    
    @Builder.Default
    private List<String> cols = new ArrayList<>();
    
    private double min;
    
    private double max;
    
    private double mean;
    
    private double std;
}