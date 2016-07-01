package com.br.ufms.schirrel.exportar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.classes.SaidaView;

public class ExportarRelatorio {
	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet;
	int CABECALHO_INDEX = 4;
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat formatArquivo = new SimpleDateFormat("dd-MM-yyyy");
	private List<CellStyle> estilos = new ArrayList<CellStyle>();

	public ExportarRelatorio() {
		workbook = new HSSFWorkbook();
		preencherEstilos();
	}

	void preencherEstilos() {
		estilos.removeAll(estilos);
		CellStyle estilo0 = workbook.createCellStyle();
		CellStyle estilo1 = workbook.createCellStyle();
		CellStyle estilo2 = workbook.createCellStyle();
		CellStyle estilo3 = workbook.createCellStyle();

		Font fonte = workbook.createFont();
		estilo0.setFillPattern(CellStyle.SOLID_FOREGROUND);
		estilo0.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		fonte.setFontHeightInPoints((short) 20);
		fonte.setBoldweight(Font.BOLDWEIGHT_BOLD);
		estilo0.setBorderRight(CellStyle.BORDER_THIN);
		estilo0.setBorderLeft(CellStyle.BORDER_THIN);
		estilo0.setBorderTop(CellStyle.BORDER_THIN);
		estilo0.setBorderBottom(CellStyle.BORDER_THIN);
		estilo0.setAlignment(CellStyle.ALIGN_CENTER);
		estilo0.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo0.setWrapText(true);
		estilo0.setFont(fonte);

		estilos.add(estilo0);

		Font fonte2 = workbook.createFont();
		fonte2.setFontHeightInPoints((short) 12);
		fonte2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		estilo1.setBorderRight(CellStyle.BORDER_THICK);
		estilo1.setBorderLeft(CellStyle.BORDER_THICK);
		estilo1.setBorderTop(CellStyle.BORDER_THICK);
		estilo1.setBorderBottom(CellStyle.BORDER_THICK);
		estilo1.setAlignment(CellStyle.ALIGN_CENTER);
		estilo1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo1.setWrapText(true);
		estilo1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		estilo1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		estilo1.setFont(fonte2);
		estilos.add(estilo1);

		Font fonte3 = workbook.createFont();
		fonte3.setFontHeightInPoints((short) 10);
		estilo2.setBorderRight(CellStyle.BORDER_THIN);
		estilo2.setBorderLeft(CellStyle.BORDER_THIN);
		estilo2.setBorderTop(CellStyle.BORDER_THIN);
		estilo2.setBorderBottom(CellStyle.BORDER_THIN);
		estilo2.setAlignment(CellStyle.ALIGN_CENTER);
		estilo2.setWrapText(true);
		estilo2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		estilo2.setFont(fonte3);
		estilos.add(estilo2);

		estilo3.setWrapText(true);
		estilo3.setBorderRight(CellStyle.BORDER_THIN);
		estilo3.setBorderLeft(CellStyle.BORDER_THIN);
		estilo3.setBorderTop(CellStyle.BORDER_THIN);

		estilo3.setBorderBottom(CellStyle.BORDER_THIN);
		estilos.add(estilo3);

	}

	public void GerarRelatorioDatasDeEntrada(List<Entrada> entradas, String dataIni, String dataFinal) {
		String colunasNomes[] = { "Item", "Unidade", "Fabricante", "Data de Validade", "Data de Entrada",
				"Qtd Recebida", "Qtd Retirada" };
		int colunasWidth[] = { 10000, 10000, 10000, 5000, 5000, 5000, 5000 };

		
		sheet = workbook.createSheet("RelatorioMdCPorDatas");
		// HSSFSheet sheetIndividual = workbook.createSheet("arquivoteste2");
		preencherEstilos();
		Row titulo = sheet.createRow(0);
		Cell primeira = titulo.createCell(0);
		primeira.setCellValue("Relatório de Material de Consumo por Intervalo de Datas " + dataIni + " - " + dataFinal);
		primeira.setCellStyle(estilos.get(0));
		sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 6));

		Row cabecalho = sheet.createRow(CABECALHO_INDEX);
		Cell colunas[] = new Cell[7];

		for (int i = 0; i < colunas.length; i++) {
			colunas[i] = cabecalho.createCell(i);
			colunas[i].setCellValue(colunasNomes[i]);
			colunas[i].setCellStyle(estilos.get(2));
			sheet.setColumnWidth(i, colunasWidth[i]);
		}

		// Cell cellItem = cabecalho.createCell(0);
		// cellItem.setCellValue("Item");
		//
		// Cell cellUnidade = cabecalho.createCell(1);
		// cellUnidade.setCellValue("Unidade");
		//
		// Cell cellFabricante = cabecalho.createCell(2);
		// cellFabricante.setCellValue("Fabricante");
		//
		// Cell cellValidade = cabecalho.createCell(3);
		// cellValidade.setCellValue("Data de Validade");
		//
		// Cell cellRecepcao = cabecalho.createCell(4);
		// cellRecepcao.setCellValue("Data de Entrada");
		//
		// Cell cellQRec = cabecalho.createCell(5);
		// cellQRec.setCellValue("Qtd Recebida");
		//
		// Cell cellQRet = cabecalho.createCell(6);
		// cellQRet.setCellValue("Qtd Retirada");

		format.setLenient(false);
		for (Entrada e : entradas) {

		}
		Cell c1, c2, c3, c4, c5, c6, c7;
		for (int r = CABECALHO_INDEX + 1, i = 0; i < entradas.size(); r++, i++) {
			Row nova = sheet.createRow(r);
			c1 = nova.createCell(0);
			c1.setCellValue(entradas.get(i).getItem().getItem());
			c2 = nova.createCell(1);
			c2.setCellValue(entradas.get(i).getUnidade().getUnidade());
			c3 = nova.createCell(2);
			c3.setCellValue(entradas.get(i).getFabricante().getFabricante());
			c4 = nova.createCell(3);
			c4.setCellValue(format.format(entradas.get(i).getDataValidade()));
			c5 = nova.createCell(4);
			c5.setCellValue(format.format(entradas.get(i).getDataEntrada()));
			c6 = nova.createCell(5);
			c6.setCellValue(entradas.get(i).getQtd());
			c7 = nova.createCell(6);
			c7.setCellValue(entradas.get(i).getRetirada());

			c1.setCellStyle(estilos.get(3));
			c2.setCellStyle(estilos.get(3));
			c3.setCellStyle(estilos.get(3));
			c4.setCellStyle(estilos.get(3));
			c5.setCellStyle(estilos.get(3));
			c6.setCellStyle(estilos.get(3));
			c7.setCellStyle(estilos.get(3));
		}

		Row rodape = sheet.createRow(CABECALHO_INDEX + entradas.size() + 1);
		Cell coluna = rodape.createCell(0);
		coluna.setCellValue("Relatório Gerado pelo Sistema de Estoque de Laboratórios da UFMS-CPCX");
		coluna.setCellStyle(estilos.get(1));
		sheet.addMergedRegion(new CellRangeAddress(CABECALHO_INDEX + entradas.size() + 1,
				CABECALHO_INDEX + entradas.size() + 2, 0, 6));

		try {

			Date hj = new Date(System.currentTimeMillis());
			File file = new File("RelatorioMdCPorDatas-" + formatArquivo.format(hj) + ".xls");
			FileOutputStream out = new FileOutputStream(file, false);
			workbook.write(out);
			out.flush();
			out.close();
			if (file.length() != 0) {

			} else {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void GerarRelatorioDatasDeSaida(List<SaidaView> saidasList, String dataIni, String dataFinal) {
		String colunasNomes[] = { "Item", "Fabricante", "Data de Entrada", "Data de Validade",
				"Usuario", "Data da Retirada", "Qtd Retirada" };
		int colunasWidth[] = { 10000, 10000, 5000, 5000, 5000, 5000, 5000 };
		sheet = workbook.createSheet("RelatorioSMdCPorDatas");
		// HSSFSheet sheetIndividual = workbook.createSheet("arquivoteste2");
		preencherEstilos();
		Row titulo = sheet.createRow(0);
		Cell primeira = titulo.createCell(0);
		primeira.setCellValue("Relatório da Saida de Material de Consumo por Intervalo de Datas " + dataIni + " - " + dataFinal);
		primeira.setCellStyle(estilos.get(0));
		sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 6));

		Row cabecalho = sheet.createRow(CABECALHO_INDEX);
		Cell colunas[] = new Cell[7];

		for (int i = 0; i < colunas.length; i++) {
			colunas[i] = cabecalho.createCell(i);
			colunas[i].setCellValue(colunasNomes[i]);
			colunas[i].setCellStyle(estilos.get(2));
			sheet.setColumnWidth(i, colunasWidth[i]);
		}

		format.setLenient(false);

		Cell c1, c2, c3, c4, c5, c6, c7;
		for (int r = CABECALHO_INDEX + 1, i = 0; i < saidasList.size(); r++, i++) {
			Row nova = sheet.createRow(r);
			c1 = nova.createCell(0);
			c1.setCellValue(saidasList.get(i).getItem());
			c2 = nova.createCell(1);
			c2.setCellValue(saidasList.get(i).getFabricante());
			c3 = nova.createCell(2);
			c3.setCellValue(format.format(saidasList.get(i).getEntrada()));
			c4 = nova.createCell(3);
			c4.setCellValue(format.format(saidasList.get(i).getValidade()));
			c5 = nova.createCell(4);
			c5.setCellValue(saidasList.get(i).getUsuario());
			c6 = nova.createCell(5);
			c6.setCellValue(format.format(saidasList.get(i).getRetirada()));
			c7 = nova.createCell(6);
			c7.setCellValue(saidasList.get(i).getQtd_retirada());

			c1.setCellStyle(estilos.get(3));
			c2.setCellStyle(estilos.get(3));
			c3.setCellStyle(estilos.get(3));
			c4.setCellStyle(estilos.get(3));
			c5.setCellStyle(estilos.get(3));
			c6.setCellStyle(estilos.get(3));
			c7.setCellStyle(estilos.get(3));
		}

		Row rodape = sheet.createRow(CABECALHO_INDEX + saidasList.size() + 1);
		Cell coluna = rodape.createCell(0);
		coluna.setCellValue("Relatório Gerado pelo Sistema de Estoque de Laboratórios da UFMS-CPCX");
		coluna.setCellStyle(estilos.get(1));
		sheet.addMergedRegion(new CellRangeAddress(CABECALHO_INDEX + saidasList.size() + 1,
				CABECALHO_INDEX + saidasList.size() + 2, 0, 6));

		try {

			Date hj = new Date(System.currentTimeMillis());
			File file = new File("RelatorioSMdCPorDatas-" + formatArquivo.format(hj) + ".xls");
			FileOutputStream out = new FileOutputStream(file, false);
			workbook.write(out);
			out.flush();
			out.close();
			if (file.length() != 0) {

			} else {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
