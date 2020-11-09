package NewStart;

import java.io.*;
import java.io.File;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.*;

public class Excel_Modified {
    static void output(double data1[][], double data2[][], double data3[][], double data4[][]) throws IOException, RowsExceededException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(new File("Time_Round(Mota 314).xls"));//워크북 생성
        WritableSheet s1 = workbook.createSheet("TimeSlot A",  0); //시트생성
        WritableSheet s2 = workbook.createSheet("TimeSlot B",  1); //시트생성
        WritableSheet s3 = workbook.createSheet("Round A",  2); //시트생성
        WritableSheet s4 = workbook.createSheet("Round B",  3); //시트생성
        
        for(int j=1 ; j<19 ; j++){       
        	for(int i=1 ; i<10001 ; i++){
        		Label label = new Label(j, i, ""+data1[i][j]); //라벨객체 생성.
        		s1.addCell(label); //셀에 라벨 추가
        	}
        }
        for(int j=1 ; j<19 ; j++){       
        	for(int i=1 ; i<10001 ; i++){
        		Label label = new Label(j, i, ""+data2[i][j]); //라벨객체 생성.
        		s2.addCell(label); //셀에 라벨 추가
        	}
        }
        for(int j=1 ; j<19 ; j++){       
        	for(int i=1 ; i<10001 ; i++){
        		Label label = new Label(j, i, ""+data3[i][j]); //라벨객체 생성.
        		s3.addCell(label); //셀에 라벨 추가
        	}
        }
        for(int j=1 ; j<19 ; j++){       
        	for(int i=1 ; i<10001 ; i++){
        		Label label = new Label(j, i, ""+data4[i][j]); //라벨객체 생성.
        		s4.addCell(label); //셀에 라벨 추가
        	}
        }
        workbook.write(); //파일로 쓰기
        workbook.close(); //닫기
    }
}