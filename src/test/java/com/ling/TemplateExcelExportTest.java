package com.ling;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateExcelExportTest {

  
    @Test
    public void fe_map23() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "doc/21.xlsx");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", "2014-12-25");
        map.put("sunCourses", "2");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, Object>> listMap = new ArrayList();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> lm = new HashMap();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("name", "数学" + i);
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "开源项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");
            Map<String, Object> chineseTeacher1 = new HashMap<>();
            chineseTeacher1.put("name", "王老师" + i);
            lm.put("chineseTeacher", chineseTeacher1);
            List<Map<String, Object>> studentList = new ArrayList();
            for (int i1 = 0; i1 < 2; i1++) {
                Map<String, Object> student = new HashMap<>();
                student.put("name", "小明" + i1);
                studentList.add(student);
            }
            lm.put("students", studentList);
            listMap.add(lm);
        }
        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/home/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/home/excel/ling_map616.xls");
        workbook.write(fos);
        fos.close();
    }


}
