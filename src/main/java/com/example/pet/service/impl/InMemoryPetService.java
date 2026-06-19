package com.example.pet.service.impl;


import com.example.pet.service.PetService;
import com.example.pet.model.Pet;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryPetService implements PetService {

    private final Map<Long, Pet> storage = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(100);

    @PostConstruct
    public void init() {
        Pet p1 = new Pet();
        p1.setId(1L);
        p1.setName("凯凯");
        p1.setType("狗");
        p1.setAge(10);
        p1.setGender("公");
        p1.setBreed("柴犬");
        p1.setOwner("张三");
        p1.setRemark("已接种疫苗");
        p1.setImageUrl("/images/default-pet.png");
        storage.put(p1.getId(), p1);

        Pet p2 = new Pet();
        p2.setId(2L);
        p2.setName("咪咪");
        p2.setType("猫");
        p2.setAge(2);
        p2.setGender("母");
        p2.setBreed("英短");
        p2.setOwner("李四");
        p2.setRemark("性格温顺");
        p2.setImageUrl("/images/default-pet.png");
        storage.put(p2.getId(), p2);
    }

    @Override
    public List<Pet> list() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Pet getById(Long id) {
        return storage.get(id);
    }

    @Override
    public void save(Pet pet) {
        long id = idGen.incrementAndGet();
        pet.setId(id);
        storage.put(id, pet);
    }

    @Override
    public void update(Pet pet) {
        storage.put(pet.getId(), pet);
    }

    @Override
    public String generateReport(Long id, Pet pet, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {

            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // 中文字体，防止中文乱码
            BaseFont baseFont = BaseFont.createFont(
                    "STSong-Light",
                    "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED
            );

            Font titleFont = new Font(baseFont, 22, Font.BOLD);
            Font subTitleFont = new Font(baseFont, 14, Font.BOLD);
            Font normalFont = new Font(baseFont, 12, Font.NORMAL);
            Font smallFont = new Font(baseFont, 10, Font.NORMAL);

            // 标题
            Paragraph title = new Paragraph("宠物信息报告", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // 报告编号
            Paragraph reportNo = new Paragraph("报告编号：" + id, smallFont);
            reportNo.setAlignment(Element.ALIGN_RIGHT);
            reportNo.setSpacingAfter(20);
            document.add(reportNo);


            String imageUrl = pet.getImageUrl();

            if (imageUrl == null || "".equals(imageUrl.trim())) {
                imageUrl = "/images/default-pet.png";
            }

            Image image = null;

            // 处理 resources/static 下的默认图片
            if (imageUrl.startsWith("/images/")) {
                String classpathPath = "static" + imageUrl;
                ClassPathResource resource = new ClassPathResource(classpathPath);

                if (resource.exists()) {
                    image = Image.getInstance(resource.getURL());
                }
            } else {
                // 处理本地磁盘图片，比如 D:/xxx/xxx.png
                File imgFile = new File(imageUrl);

                if (imgFile.exists()) {
                    image = Image.getInstance(imageUrl);
                }
            }

            if (image != null) {
                image.scaleToFit(220, 220);
                image.setAlignment(Element.ALIGN_CENTER);
                image.setSpacingAfter(20);
                document.add(image);
            }

            // 小标题
            Paragraph infoTitle = new Paragraph("基本信息", subTitleFont);
            infoTitle.setSpacingBefore(10);
            infoTitle.setSpacingAfter(10);
            document.add(infoTitle);

            // 表格
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 5});
            table.setSpacingBefore(10);
            table.setSpacingAfter(20);

            addCell(table, "名称", subTitleFont, true);
            addCell(table, pet.getName(), normalFont, false);

            addCell(table, "品种", subTitleFont, true);
            addCell(table, pet.getBreed(), normalFont, false);

            addCell(table, "年龄", subTitleFont, true);
            addCell(table, pet.getAge() == null ? "" : pet.getAge() + " 岁", normalFont, false);

            addCell(table, "性别", subTitleFont, true);
            addCell(table, pet.getGender(), normalFont, false);

            addCell(table, "颜色", subTitleFont, true);
//            addCell(table, pet.getColor(), normalFont, false);

            addCell(table, "备注", subTitleFont, true);
            addCell(table, pet.getRemark(), normalFont, false);

            document.add(table);

            // 说明区域
            Paragraph descTitle = new Paragraph("报告说明", subTitleFont);
            descTitle.setSpacingBefore(10);
            descTitle.setSpacingAfter(8);
            document.add(descTitle);

            Paragraph desc = new Paragraph(
                    "本报告根据当前宠物基础信息自动生成，用于信息归档、展示和后续业务处理参考。",
                    normalFont
            );
            desc.setFirstLineIndent(24);
            desc.setLeading(22);
            document.add(desc);

            // 页脚
            Paragraph footer = new Paragraph("Generated by Pet Report System", smallFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(40);
            document.add(footer);

        } catch (Exception e) {
            throw new RuntimeException("生成宠物 PDF 报告失败", e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
        return "ok";
    }
    private void addCell(PdfPTable table, String text, Font font, boolean header) {
        PdfPCell cell = new PdfPCell(new Phrase(text == null ? "" : text, font));

        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        if (header) {
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(230, 240, 250));
        } else {
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBackgroundColor(Color.WHITE);
        }

        cell.setBorderColor(new Color(200, 200, 200));
        table.addCell(cell);
    }
}

