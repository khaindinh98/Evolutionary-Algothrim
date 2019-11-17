package trituenhantao;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sub4sa
 */
public class CaThe implements Comparable<CaThe> {

    public int[][] lichDiLam;
    public int soNhanVien;
    public ArrayList<NhanVien> tapNhanVien;

    public CaThe(CaThe caThe) {
        this.soNhanVien = caThe.soNhanVien;
        this.tapNhanVien = caThe.tapNhanVien;
        this.lichDiLam = new int[soNhanVien][9];
        for (int i = 0; i < soNhanVien; i++) {
            for (int j = 2; j <= 8; j++) {
                int value = caThe.lichDiLam[i][j];
                //System.out.println(value);
                this.lichDiLam[i][j] = value;
            }
        }
        //CaThe.copyArray2D(caThe.lichDiLam,this.lichDiLam);
        //System.out.println(hamThichNghi());
    }

    public CaThe(int soNhanVien, ArrayList<NhanVien> tapNhanVien) {
        this.soNhanVien = soNhanVien;
        this.tapNhanVien = tapNhanVien;
        lichDiLam = new int[soNhanVien][9];
        //int bd=-70;
        Random r = new Random();
        //do{
        for (int j = 2; j <= 8; j++) {
            int tongCaTrongNgay = 0;
            do {
                for (int i = 0; i < soNhanVien; i++) {
                    int rd = r.nextInt(4);
                    NhanVien nv = tapNhanVien.get(i);
                    int ca1 = nv.lichHoc[1][j];
                    int ca2 = nv.lichHoc[2][j];
                    int ca3 = nv.lichHoc[3][j];
                    //System.out.println(ca1+""+ca2+""+ca3);
                    while ((rd == 3 && ca3 == 1) || (rd == 2 && ca2 == 1) || (rd == 1 && ca1 == 1)) {
                        rd = r.nextInt(4);
                    }
                    lichDiLam[i][j] = rd;
                }
                tongCaTrongNgay = tinhTongCaTrongNgay(j);
            } while (tongCaTrongNgay <= 1);
        }
        //}while(this.hamThichNghi()<=-50);
    }

    public static void copyArray2D(int[][] input, int[][] output) {
        //System.out.print(input[0].length+" "+input.length);
        for (int i = 0; i < input.length; i++) {
            for (int j = 2; j <= 8; j++) {
                int value = input[i][j];
                System.out.println(value);
                output[i][j] = value;
            }
        }
    }

    public int khongTrungCaHoc(int i, int j) {
        int diem = 0;
        int value = lichDiLam[i][j];
        NhanVien nv = tapNhanVien.get(i);
        int ca1 = nv.lichHoc[1][j];
        int ca2 = nv.lichHoc[2][j];
        int ca3 = nv.lichHoc[3][j];
        //System.out.println(ca1+""+ca2+""+ca3);
        if ((value == 3 && ca3 != 1) || (value == 2 && ca2 != 1) || (value == 1 && ca1 != 1) || (value == 0 && (ngayNghiTrongTuan(i) == 6))) {
            diem = 1;
        }
        return diem;
    }

    public int tinhTongCaTrongNgay(int index) {
        int tongCaTrongNgay = 0;
        int tongCa0 = 0, tongCa1 = 0, tongCa2 = 0, tongCa3 = 0;
        for (int i = 0; i < soNhanVien; i++) {
            if (lichDiLam[i][index] == 0) {
                tongCa0 += 1;
            }
            if (lichDiLam[i][index] == 1) {
                tongCa1 += 1;
            }
            if (lichDiLam[i][index] == 2) {
                tongCa2 += 1;
            }
            if (lichDiLam[i][index] == 3) {
                tongCa3 += 1;
            }
        }
        if (tongCa1 >= 2) {
            tongCaTrongNgay += 1;
        }
        if (tongCa2 >= 2) {
            tongCaTrongNgay += 1;
        }
        if (tongCa3 >= 2) {
            tongCaTrongNgay += 1;
        }
        if (tongCa0 > 1) {
            tongCaTrongNgay = 0;
        }
        return tongCaTrongNgay;
    }

    public int ngayNghiTrongTuan(int indexNhanVien) {
        int ngayNghi = 7;
        for (int j = 2; j <= 7; j++) {
            if (lichDiLam[indexNhanVien][j] == 0) {
                ngayNghi -= 1;
            }
        }
        if (lichDiLam[indexNhanVien][8] == 0 || ngayNghi == 7) {
            ngayNghi = 0;
        }
        return ngayNghi;
    }

    public int ca3NhanVien(int indexNhanVien) {
        int ca3 = 0;
        for (int j = 2; j <= 7; j++) {
            if (lichDiLam[indexNhanVien][j] == 3) {
                ca3 += 1;
            }
        }
        if (ca3 != 0) {
            return ca3 / ca3;
        }
        return 0;
    }

    public double hamThichNghi() {
        int ca3 = 0;
        double diemThichNghi = 0;
        double tongCaTrongTuan = 0.0;

        double tongNgayNghiCuaNhanVien = 0.0;
        for (int j = 2; j <= 8; j++) {
            int tongCaTrongNgay = tinhTongCaTrongNgay(j);
            //System.out.print("*"+tinhTongCaTrongNgay(j)/3.0+" ");
            tongCaTrongTuan += tinhTongCaTrongNgay(j) / 3.0;
        }
        for (int i = 0; i < soNhanVien; i++) {
            double ngayNghiCuaNhanVien = ngayNghiTrongTuan(i) / 6.0;
            tongNgayNghiCuaNhanVien += ngayNghiCuaNhanVien;
            ca3 += ca3NhanVien(i);
        }
        int diem = 0;
        for (int j = 2; j <= 8; j++) {
            for (int i = 0; i < soNhanVien; i++) {
                diem += khongTrungCaHoc(i, j);
            }
        }
        //System.out.print(diem+"*0 ");

        double doChinhXac = (double) diem / (7 * soNhanVien);

        diemThichNghi += (tongCaTrongTuan / 7.0) * 30.0;
        //System.out.print((tongCaTrongTuan/7.0)*30.0+"*1 ");

        diemThichNghi += doChinhXac * 30.0;
        //System.out.print(doChinhXac*30.0+"*2 ");

        diemThichNghi += ((double) tongNgayNghiCuaNhanVien / soNhanVien) * 30.0;
        //System.out.print(((double)tongNgayNghiCuaNhanVien/soNhanVien)*30.0+"*3 ");

        diemThichNghi += ((double) ca3 / soNhanVien) * 10.0;
        //System.out.print(((double)ca3/soNhanVien)*10.0+"*4 ");

        return diemThichNghi;
    }

    @Override
    public int compareTo(CaThe o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Double x = hamThichNghi();
        return x.compareTo(o.hamThichNghi());
    }

    public String toString() {

        String temp = "";
        for (int i = 0; i < lichDiLam.length; i++) {
            for (int j = 2; j <= 8; j++) {
                temp = temp + lichDiLam[i][j] + " ";
            }
            temp = temp + "\n";
        }
        temp += "\n";
        return temp;
    }
}

