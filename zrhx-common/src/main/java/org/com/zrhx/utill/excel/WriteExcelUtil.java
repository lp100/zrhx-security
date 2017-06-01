package org.com.zrhx.utill.excel;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;

import java.awt.image.BufferedImage;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: 导出Excel</p>
 */
public class WriteExcelUtil {

    private WritableWorkbook writableWorkbook;
    private WritableSheet writableSheet;
    private WritableFont writableFont;
    private WritableCellFormat writableCellFormat;
    private Label label;
    private File file;
    private String fileName = "";
    private String sheetName = "";
    private HttpServletResponse response;
    private String savePath = "";
    
    public String getFilePath() {
    	if(file != null){
    		return file.getPath();
    	}
    	return null;
    }
    
    /**
     * 建立目录
     * @param strDirName 目录名

     */
    private void createDirtory(String strDirName) {
        File strDir = new File(strDirName);
        if (!strDir.isDirectory()) { //如果还没有建立目录当前用户的文件夹

            strDir.mkdir();
        }
    }
    
    public WriteExcelUtil(){
    	
    }

    /**
     *
     * @param response HttpServletResponse
     * @param row int  标题在第几列
     * @param col int  标题在第几行
     * @param name String  标题名字
     * @throws Exception
     */
    public WriteExcelUtil(HttpServletResponse response,int row,int col,String name,String savepath,String fileName) throws Exception {
        this.response = response;
        this.fileName=fileName;
        savePath = savepath;
		createDirtory(savePath);

        this.response.setContentType("application/vnd.ms-excel");
        
        sheetName = name;
        //创建文件

        file = new File(savePath,fileName + ".xls");
        writableWorkbook = Workbook.createWorkbook(file);
        writableSheet = writableWorkbook.createSheet(sheetName,0);
        
        
        writableFont = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);
    	
        writableCellFormat = new WritableCellFormat(writableFont);
        
        writableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);//水平居中設定
    	writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中設定

    	
//        label = new Label(row,col,name,writableCellFormat);
//        writableSheet.addCell(label);
    }
    
    /**
     * 合并单元格

     * @param m
     * @param n
     * @param p
     * @param q
     * @throws Exception
     */
    public void mergeCells(int m,int n,int p,int q) throws Exception {
    	writableSheet.mergeCells(m, n, p, q);
    }

    /**
     * 开始写每一行数据
     * @param row int 从第几列开始写
     * @param col int 写在第几行
     * @param name String[] 写入的数据
     * @param font 字体颜色
     * 
     */
    public void startWrite(int row,int col,String[] name,Colour font) throws Exception {
        for (int i = 0; i < name.length; i++) {
        	writableFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, font);
            writableCellFormat = new WritableCellFormat(writableFont);
            writableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);//水平居中設定
        	writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中設定
        	label = new Label(row + i, col, name[i], writableCellFormat);
            writableSheet.addCell(label);
        }
    }
    /**
     * 开始写每一行数据
     * @param row int 从第几列开始写
     * @param col int 写在第几行

     * @param name String[] 写入的数据

     * @param isHead boolean 是否是标题列
     */
    public void startWrite(int row,int col,String[] name,boolean isHead) throws Exception {
        if(isHead) {
            for (int i = 0; i < name.length; i++) {
            	writableFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
                writableCellFormat = new WritableCellFormat(writableFont);
                writableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);//水平居中設定
            	writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中設定
            	label = new Label(row + i, col, name[i], writableCellFormat);
                writableSheet.addCell(label);
                writableSheet.setColumnView(i, 20);
            }
        }else{
            for (int i = 0; i < name.length; i++) {
                writableCellFormat = new WritableCellFormat();
                writableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);//水平居中設定
            	writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中設定
            	label = new Label(row + i, col, name[i], writableCellFormat);
                writableSheet.addCell(label);
            }
        }
    }
    
    /**
     * 
     * @param satrtrow 起始列
     * @param satrtcol 起始行
     * @param endrow 结束列
     * @param endcol 结束行
     * @param name 标题名称
     * @throws Exception
     */
    public void startWriteHead(int satrtrow,int satrtcol,int endrow,int endcol,String name) throws Exception {
    			writableSheet.mergeCells(satrtrow , satrtcol, endrow, endcol);
            	writableFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
                writableCellFormat = new WritableCellFormat(writableFont);
                writableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);//水平居中設定
            	writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中設定
            	label = new Label(satrtrow , satrtcol, name, writableCellFormat);
                writableSheet.addCell(label);
    }
    /**
     * 写图片

     * @param row
     * @param col
     * @param width
     * @param height
     * @param imgUrl
     * @throws Exception
     */
    public void writeImage(int row,int col,double width,double height,String imgUrl) throws Exception {
    	WritableImage ri = new WritableImage(row,col,width,height,new File(imgUrl));
    	writableSheet.addImage(ri);
    }
    /**
     * 写图片

     * @param row
     * @param col
     * @param width
     * @param height
     * @param imgUrl
     */
    public void writeImage(int row,int col,double width,double height,byte[] imgUrl) throws Exception {
    	WritableImage ri = new WritableImage(row,col,width,height,imgUrl);
    	writableSheet.addImage(ri);
    }

	/**
	 * 插入图片到EXCEL
	 * @param pictureFile
	 *            图片file对象
	 * @param cellRow
	 *            行数
	 * @param cellCol
	 *            列数
	 * @throws Exception
	 *             例外
	 */
	public void addPictureToExcel( File pictureFile,
			Integer cellRow, Integer cellCol) throws Exception {
		// 图片时间的高度，宽度
		double picCellWidth = 0.0;
		double picCellHeight = 0.0;
		// 读入图片
		BufferedImage picImage = ImageIO.read(pictureFile);
		// 取得图片的像素高度，宽度
		int picWidth = picImage.getWidth();
		int picHeight = picImage.getHeight();

		// 计算图片的实际宽度
		int picWidth_t = picWidth * 32; // 具体的实验值，原理不清楚。
		for (int x = 0; x < 1234; x++) {
			int bc = (int) Math.floor(cellCol + x);
			// 得到单元格的宽度
			int v = writableSheet.getColumnView(bc).getSize();
			double offset0_t = 0.0;
			if (0 == x)
				offset0_t = (cellCol - bc) * v;
			if (0.0 + offset0_t + picWidth_t > v) {
				// 剩余宽度超过一个单元格的宽度
				double ratio_t = 1.0;
				if (0 == x) {
					ratio_t = (0.0 + v - offset0_t) / v;
				}
				picCellWidth += ratio_t;
				picWidth_t -= (int) (0.0 + v - offset0_t);
			} else { // 剩余宽度不足一个单元格的宽度
				double ratio_r = 0.0;
				if (v != 0)
					ratio_r = (0.0 + picWidth_t) / v;
				picCellWidth += ratio_r;
				break;
			}
		}
		// 计算图片的实际高度
		int picHeight_t = picHeight * 15;
		for (int x = 0; x < 1234; x++) {
			int bc = (int) Math.floor(cellRow + x);
			// 得到单元格的高度
			int v = writableSheet.getRowView(bc).getSize();
			double offset0_r = 0.0;
			if (0 == x)
				offset0_r = (cellRow - bc) * v;
			if (0.0 + offset0_r + picHeight_t > v) {
				// 剩余高度超过一个单元格的高度
				double ratio_q = 1.0;
				if (0 == x)
					ratio_q = (0.0 + v - offset0_r) / v;
				picCellHeight += ratio_q;
				picHeight_t -= (int) (0.0 + v - offset0_r);
			} else {// 剩余高度不足一个单元格的高度
				double ratio_m = 0.0;
				if (v != 0)
					ratio_m = (0.0 + picHeight_t) / v;
				picCellHeight += ratio_m;
				break;
			}
		}
		// 生成一个图片对象。
		WritableImage image = new WritableImage(cellCol, cellRow, picCellWidth,
				picCellHeight, pictureFile);
		// 把图片插入到sheet
		writableSheet.addImage(image);
	}

    /**
     * 保存文件
     * @throws Exception
     */
    public String saveFile()  {
    	try{
	        writableWorkbook.write();
	        writableWorkbook.close();
//	        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
//	        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
//	        BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
//	        int byteRead;
//	        while((byteRead = fis.read()) != -1)
//	        {
//	            os.write(byteRead);
//	        }
//	        os.close();
//	        if (fis != null) fis.close();
//	        os.flush();
	        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return fileName + ".xls";
    }
    
    /**读取Excel文件的内容

     * @param file  待读取的文件
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] readExcel(String[] index,String[] datahead,File file) throws Exception {
        
        Workbook wb = null;
        
        wb= Workbook.getWorkbook(file);
 
        if(wb==null)
            return null;
        
        //获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
        Sheet[] sheet = wb.getSheets();
        
        Map[] rows = null;
        Map cellMap = new HashMap();
        
        if(sheet!=null&&sheet.length>0){
            //对每个工作表进行循环
            for(int i=0;i<sheet.length;i++){
                //得到当前工作表的行数
                int rowNum = sheet[i].getRows();
                rows = new Map[rowNum];
                for(int j=0;j<rowNum;j++){
                    //得到当前行的所有单元格
                    Cell[] cells = sheet[i].getRow(j);
                    if(cells!=null&&cells.length>0){
                        //对每个单元格进行循环
                        for(int k=0;k<cells.length;k++){
                            //读取当前单元格的值

                        	for(int h=0;h<index.length;h++) {
                        		if(k==Integer.parseInt(index[h]))
                        			cellMap.put(datahead[h]+"$"+j, cells[k].getContents());
                        			
                        	}
                        }
                    }
                    rows[j] = cellMap;
                }
            }
        }
        //最后关闭资源，释放内存
        wb.close();
        return rows;
    }
    
    /**
     * 读取Excel文件内容
     * @param userRow: key行号
     * @param valueRow: value行号
     * @param file: 文件
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map readExcel(String userRow,String valueRow,File file) throws Exception {
    	Workbook wb = null;
        
        wb=Workbook.getWorkbook(file);
 
        if(wb==null)
            return null;
        
        //获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
        Sheet[] sheet = wb.getSheets();
        Map cellMap = new HashMap();
        if(sheet!=null&&sheet.length>0){
            //得到当前工作表的行数
            int rowNum = sheet[0].getRows();
            
            for(int j=0;j<rowNum;j++){
                Cell[] cells = sheet[0].getRow(j);
                cellMap.put(cells[getMathCount(userRow)].getContents().trim(), cells[getMathCount(valueRow)].getContents().trim());
            }
        }
        //最后关闭资源，释放内存
        wb.close();
    	return cellMap;
    }
    
    /**
     * 读取Excel文件
     * @param file: Excel文件路径
     * @return ExcelInfoBean: 用来存放Excel读取的信息数据

     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ExcelInfoBean readExcel(File file) throws Exception {
    	
    	Workbook wb = null;
        
    	//读取Excel文件,取得Workbook对像
        wb=Workbook.getWorkbook(file);
        
        if(wb==null)
            return null;
        
        //获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
        Sheet[] sheet = wb.getSheets();
        //Excel数据对像
        ExcelInfoBean excelInfoBean = new ExcelInfoBean();
        //报表头标题内容,这里指Excel文档的第一行.
        String header = "";
        //标题字体的大小

        String titleSize = "";
    	List excelList = new ArrayList();
        List list = new ArrayList();
        //最大的列数
        int celSize = 0;
        
        if(sheet!=null&&sheet.length>0){
        	
            //得到当前工作表的行数
            int rowNum = sheet[0].getRows();
            
            //所有合并单元格的数据集合

            Range[] range = sheet[0].getMergedCells();
            for(int n=0; n<range.length; n++) {
            	//记录合并格的坐标,格式为: 0:3#1:5   表示:第左上A3格到右下B5格为合并单元格

            	String rangeInfo = range[n].getTopLeft().getColumn()+":"+(range[n].getTopLeft().getRow())+"#"+range[n].getBottomRight().getColumn()+":"+(range[n].getBottomRight().getRow());
            	excelList.add(rangeInfo);
            }
            
            //循环读取Excel数据
            for(int j=0;j<rowNum;j++){
            	Cell[] cells = sheet[0].getRow(j);
            	if(cells.length > celSize) {
            		celSize = cells.length;
            	}
            	//取的第一行的数据,作为Excel的标题

            	if(j == 0) {
            		for(int k=0;k<cells.length;k++) {
            			if(!"".equals(cells[k].getContents())) {
            				header = cells[k].getContents();
            				cells[k].getCellFormat().getFont().getBoldWeight();
            				titleSize = Integer.toString(cells[k].getCellFormat().getFont().getPointSize());
            				break;
            			}
            		}
            	}else {
            		List list_c = new ArrayList();
            		for(int n=0;n<cells.length;n++) {
            			list_c.add(cells[n].getContents());
            		}
            		list.add(list_c);
            	}
            }
            excelInfoBean.setHeaderTitle(header);
            excelInfoBean.setDataList(list);
            excelInfoBean.setRangeList(excelList);
            excelInfoBean.setCelSize(celSize);
            excelInfoBean.setTitleSize(titleSize);
        }
        wb.close();
    	return excelInfoBean;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public  ExcelInfoBean readExcel2(File file) throws Exception {
    	
    	Workbook wb = null;
        
    	//读取Excel文件,取得Workbook对像
        wb=Workbook.getWorkbook(file);
        
        if(wb==null)
            return null;
        
        //获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
        Sheet[] sheet = wb.getSheets();
        //Excel数据对像
        ExcelInfoBean excelInfoBean = new ExcelInfoBean();
        List list = new ArrayList();
        if(sheet!=null&&sheet.length>0){

        	for(int s=0;s<sheet.length;s++) {
        		//得到当前工作表的行数
                int rowNum = sheet[s].getRows();
                for(int i=0;i<rowNum;i++) {
                	Cell[] cells = sheet[s].getRow(i);
                	List list_c = new ArrayList();
            		for(int n=0;n<cells.length;n++) {
            			list_c.add(cells[n].getContents());
            		}
            		list.add(list_c);
                }
        	}
        	
        	excelInfoBean.setDataList(list);
            
        }
    	return excelInfoBean;
    }
    
    private int getMathCount(String a) {
    	return ((Integer)getMathMap().get(a.toUpperCase())).intValue();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getMathMap() {
    	Map mathMap = new HashMap();
    	int baseNumber='A';
		for(int i=0;i<20;i++){
			char ch=(char) (baseNumber+i);
			mathMap.put(ch+"", new Integer(i));
		}
    	return mathMap;
    }
    
    @SuppressWarnings("unused")
	private String getColNo(int col) {
    	if(col<0||col>25)
    		return "";
		int baseNumber='A';
		return (char) (col+baseNumber)+"";
    }
}
