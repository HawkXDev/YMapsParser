package com.sokol.util.xlsx;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class MyCellStyle {
    private final CellStyle style;
    private final Workbook workbook;

    public MyCellStyle(Workbook workbook) {
        this.workbook = workbook;
        this.style = workbook.createCellStyle();
    }

    public CellStyle centerBold() {
        style.setAlignment(HorizontalAlignment.CENTER);

        var font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        return style;
    }

    public CellStyle date() {
        var format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("mmm.yy"));

        return style;
    }
}
