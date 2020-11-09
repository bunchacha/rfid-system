package NewStart;

import java.io.*;
import java.io.File;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.*;

public class Excel {
    static void output(double data1[][]) throws IOException, RowsExceededException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(new File("Mota(100-2000).xls"));//워크북 생성
        WritableSheet s1 = workbook.createSheet("sheet 0",  0); //시트생성
        WritableSheet s2 = workbook.createSheet("sheet 1",  1); //시트생성
        
        for(int j=1 ; j<21 ; j++){       
        	for(int i=1 ; i<10001 ; i++){
        		Label label = new Label(j, i, ""+data1[i][j]); //라벨객체 생성.
        		s1.addCell(label); //셀에 라벨 추가
        	}
        }
        workbook.write(); //파일로 쓰기
        workbook.close(); //닫기
    }
}