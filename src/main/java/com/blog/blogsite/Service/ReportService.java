package com.blog.blogsite.Service;

import com.blog.blogsite.Model.Post;
import com.blog.blogsite.Repository.PostRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private PostRepository _postRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Post> posts = _postRepository.findAll();

        // load file and compile it
        File file = ResourceUtils.getFile("classpath:postReport.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // using this datasource we can fill and design the report
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(posts);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Suyash");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        String path = "C://Users//suyas//Desktop";
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "//postReport.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "//postReport.pdf");
        } else if (reportFormat.equalsIgnoreCase("xml")) {
            JasperExportManager.exportReportToXmlFile(jasperPrint, path + "//postReport.xml", false);
        }

        return "Report Generated in path: " + path;

    }
}
