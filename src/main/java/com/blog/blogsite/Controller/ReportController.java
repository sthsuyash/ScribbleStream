package com.blog.blogsite.Controller;

import com.blog.blogsite.Service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService _reportService;

    @GetMapping("/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return _reportService.exportReport(format);
    }
}
