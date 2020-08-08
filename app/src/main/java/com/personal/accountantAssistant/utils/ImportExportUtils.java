package com.personal.accountantAssistant.utils;

import android.content.Context;
import android.os.Environment;

import com.personal.accountantAssistant.R;
import com.personal.accountantAssistant.db.DatabaseManager;
import com.personal.accountantAssistant.ui.payments.entities.Payments;
import com.personal.accountantAssistant.ui.payments.enums.PaymentsEnum;
import com.personal.accountantAssistant.ui.payments.enums.PaymentsType;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ImportExportUtils {

    private static final int FIRST_SHEET = 0;
    private static final int HEADER_ROW = 0;
    private static final int BODY_ROW = HEADER_ROW + 1;
    private static final int TITLE_POINT_SIZE = 16;

    public static void xlsImport(final Context context,
                                 final PaymentsType type) {
        //TODO
        ToastUtils.showShortText(context, R.string.excel_data_imported);
    }

    public static void xlsExport(final Context context,
                                 final PaymentsType type) {

        final File storageDirectory = Environment.getExternalStorageDirectory();
        final File directory = new File(storageDirectory.getAbsolutePath());
        boolean directoryExist = directory.isDirectory();

        if (!directoryExist) {
            directoryExist = directory.mkdirs();
        }

        if (directoryExist) {

            try {

                String BUYS = "Buys";
                String BILLS = "Bills";
                String xlsFileName = Constants.EMPTY_STR;
                String sheetName = Constants.EMPTY_STR;

                if (PaymentsType.isBuy(type)) {
                    xlsFileName = BUYS;
                    sheetName = BUYS;
                } else if (PaymentsType.isBill(type)) {
                    xlsFileName = BILLS;
                    sheetName = BILLS;
                }

                xlsFileName += "(" + DateUtils.toCurrentDateStr() + ").xls";
                sheetName += "_list";

                final File xlsFile = new File(directory, xlsFileName);
                final WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setLocale(new Locale(LocaleTypes.EN.getLanguage(), LocaleTypes.EN.name()));
                final WritableWorkbook workbook = Workbook.createWorkbook(xlsFile, wbSettings);
                final WritableSheet sheet = workbook.createSheet(sheetName, FIRST_SHEET);

                fillSheetFrom(context, sheet, type);

                workbook.write();
                workbook.close();
                ToastUtils.showShortText(context, R.string.excel_data_exported);

            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }

    private static void fillSheetFrom(final Context context,
                                      final WritableSheet sheet,
                                      final PaymentsType type) {
        setHeaderCell(sheet);

        AtomicInteger rowIndex = new AtomicInteger(BODY_ROW);
        new DatabaseManager(context)
                .getPaymentsRecords()
                .stream()
                .filter(it -> type.equals(it.getType()))
                .forEach(payment -> {
                    int currentRowIndex = rowIndex.get();
                    addCell(sheet, 0, currentRowIndex, payment.getName());
                    addCell(sheet, 1, currentRowIndex, String.valueOf(payment.getQuantity()));
                    addCell(sheet, 2, currentRowIndex, getConditionalDateValueFrom(payment));
                    addCell(sheet, 3, currentRowIndex, String.valueOf(payment.getUnitaryValue()));
                    addCell(sheet, 4, currentRowIndex, String.valueOf(payment.getTotalValue()));
                    addCell(sheet, 5, currentRowIndex, payment.getType().name());
                    addCell(sheet, 6, currentRowIndex, String.valueOf(payment.isActive()));
                    rowIndex.getAndIncrement();
                });
    }

    private static String getConditionalDateValueFrom(final Payments payment) {
        return String.valueOf(payment.isBill() ?
                payment.getDate() :
                Constants.DASH_SEPARATOR);
    }

    private static void setHeaderCell(final WritableSheet sheet) {
        int colIndex = 0;
        for (PaymentsEnum value : PaymentsEnum.values()) {
            addHeaderCell(sheet, colIndex, value.getValue());
            colIndex++;
        }
    }

    private static void addHeaderCell(final WritableSheet sheet,
                                      final int colIndex,
                                      final String cellValue) {
        try {
            final WritableFont cellFont = new WritableFont(WritableFont.ARIAL, TITLE_POINT_SIZE);
            cellFont.setBoldStyle(WritableFont.BOLD);
            final WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
            sheet.setColumnView(colIndex, TITLE_POINT_SIZE);
            addCell(sheet, colIndex, HEADER_ROW, cellValue, cellFormat);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    private static void addCell(final WritableSheet sheet,
                                final int colIndex,
                                final int rowIndex,
                                final String value) {
        addCell(sheet, colIndex, rowIndex, value, null);
    }

    private static void addCell(final WritableSheet sheet,
                                final int colIndex,
                                final int rowIndex,
                                final String value,
                                final WritableCellFormat cellFormat) {
        try {

            final Label cellLabel;

            if (ParserUtils.isNullObject(cellFormat)) {
                cellLabel = new Label(colIndex, rowIndex, value);
            } else {
                cellLabel = new Label(colIndex, rowIndex, value, cellFormat);
            }

            sheet.addCell(cellLabel);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
