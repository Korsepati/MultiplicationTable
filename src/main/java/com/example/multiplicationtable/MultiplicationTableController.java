package com.example.multiplicationtable;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MultiplicationTableController
{
    @Autowired
    private MultiplicationTableRepository multiplicationTableRepository;

    @GetMapping("/generatetable/{number}")
    public String generateTableAndSave(@PathVariable int number) {
        StringBuilder tableContent = new StringBuilder();
        try {

        for (int i = 1; i <= 10; i++) {
            int result = number * i;
            tableContent.append(number).append(" * ").append(i).append(" = ").append(result).append("\n");
        }

        MultiplicationTable multiplicationTable = new MultiplicationTable();
        multiplicationTable.setNumber(number);
        multiplicationTable.setTableContent(tableContent.toString());
        multiplicationTableRepository.save(multiplicationTable);
    } catch (Exception ex) {
            return ex.getMessage();
        }

        return tableContent.toString();
    }

    @GetMapping("/gettable/{number}")
    public String getTable(@PathVariable int number) {
        try {

            List<MultiplicationTable> mts = multiplicationTableRepository.getByNumber(number);

            if (mts != null && !mts.isEmpty()) {
                StringBuilder result = new StringBuilder();
                for (MultiplicationTable mt : mts) {
                    result.append("<a href=\"/table/").append(mt.getId()).append("\">Table ").append(mt.getId()).append("</a><br/>");
                }
                return result.toString();
            } else {
                return "No Table Found";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }

    }

    @GetMapping("/table/{id}")
    public String getTableById(@PathVariable Long id, Model model) {
        try {
            MultiplicationTable mt = multiplicationTableRepository.findById(id).orElse(null);

            if (mt != null) {
                String tableContent = mt.getTableContent().replaceAll("\n", "<br/>");
                model.addAttribute("tableContent", tableContent);
                return "tableContent";
            } else {
                model.addAttribute("message", "Table not found");
                return "message";
            }
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }


}