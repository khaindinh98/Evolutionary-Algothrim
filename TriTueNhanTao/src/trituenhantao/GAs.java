package trituenhantao;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sub4sa
 */

import java.util.*;
import java.io.*;

class NhanVien {

    public Information inf;
    public int[][] lichHoc;

    public NhanVien() {
        //Nhan du lieu
    }

    public NhanVien(Scanner sc) {
        this.getInformation(sc);
    }

    public void getInformation(Scanner sc) {
        int[][] lichHoc = new int[4][9];
        String fullName = sc.nextLine();
        String birthDay = sc.nextLine();
        String address = sc.nextLine();
        for (int i = 1; i <= 3; i++) {
            if (sc.hasNextLine()) {
                String[] temp = ((String) sc.nextLine()).split(" ");
                for (int j = 2; j <= 8; j++) {
                    lichHoc[i][j] = Integer.parseInt(temp[j - 2]);
                }
            }
        }
        this.inf = new Information(fullName,birthDay,address);
        this.lichHoc = lichHoc;
    }

    public String toString() {

        String temp = inf.fullName + "\n";
        for (int i = 1; i <= 3; i++) {
            for (int j = 2; j <= 8; j++) {
                temp = temp + lichHoc[i][j] + " ";
            }
            temp += "\n";
        }
        temp += "\n";
        return temp;
    }
}


public class GAs {

    public ArrayList<CaThe> quanThe;
    public ArrayList<NhanVien> tapNhanVien;
    public int soNhanVien;

    public GAs(String input, int soCaTheBanDau) {
        try {
            quanThe = new ArrayList<CaThe>();
            tapNhanVien = new ArrayList<NhanVien>();
            Scanner scanner = new Scanner(new File(input));
            while (scanner.hasNextLine()) {
                NhanVien nv = new NhanVien(scanner);
                //System.out.print(nv);
                tapNhanVien.add(nv);
            }
            soNhanVien = tapNhanVien.size();
            for (int i = 1; i <= soCaTheBanDau; i++) {
                quanThe.add(new CaThe(soNhanVien, tapNhanVien));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int[][] copyArray2D(int[][] input) {
        //System.out.print(input[0].length+" "+input.length);

        int[][] output = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; ++i) {
            for (int j = 0; j < input[0].length; ++j) {
                int value = input[i][j];
                output[i][j] = value;
            }
        }
        return output;
    }

    private ArrayList<CaThe> chonLoc(int soCaThe) {

        Random rd = new Random();
        ArrayList<Double> T = new ArrayList<Double>();
        ArrayList<CaThe> caTheDuocChon = new ArrayList<CaThe>();
        double F = 0;
        double t = 0;
        T.add(0.0);
        for (int i = 0; i < quanThe.size(); i++) {
            CaThe caThe = quanThe.get(i);
            F += caThe.hamThichNghi();
            T.add(F);
        }
        //System.out.println(T);
        int soCaTheDuocChon = 0;
        do {
            int a = rd.nextInt((int) F);//error
            double x = (a + rd.nextDouble()) % F;//error
            for (int y = 1; y < T.size(); y++) {
                //System.out.println()
                CaThe caThe = quanThe.get(y - 1);
                if (x < T.get(y)) {
                    caTheDuocChon.add(new CaThe(caThe));
                    soCaTheDuocChon += 1;
                    break;
                }

            }
            //caTheDuocChon.add(quanThe.get(y));

        } while (soCaTheDuocChon < soCaThe);
        //quanThe=caTheDuocChon;
        return caTheDuocChon;
    }

    private void laiGhep(double pLG) {
        Random rd = new Random();
        int n = (int) (pLG * quanThe.size());
        //System.out.println(n+"))");
        int soCaThe = (n % 2) != 0 ? n + 1 : n;
        ArrayList<CaThe> array = chonLoc(soCaThe);
        //System.out.println(n+"))");
        //int x=0;
        while (!array.isEmpty()) {
            CaThe caThe1 = array.get(0);
            array.remove(caThe1);
            CaThe caThe2 = array.get(0);
            array.remove(caThe2);
            int[][] lich1 = copyArray2D(caThe1.lichDiLam);
            int[][] lich2 = copyArray2D(caThe1.lichDiLam);
            //do{
            caThe1.lichDiLam = lich1;
            caThe2.lichDiLam = lich2;
            int r = rd.nextInt(9);
            for (int i = 0; i < soNhanVien; i++) {
                for (int j = r; j <= 8; j++) {
                    int temp = caThe1.lichDiLam[i][j];
                    caThe1.lichDiLam[i][j] = caThe2.lichDiLam[i][j];
                    caThe2.lichDiLam[i][j] = temp;
                }
            }
            //x=0;
            //if(caThe1.hamThichNghi()>=50.0)
            //	x+=1;
            //if(caThe2.hamThichNghi()>=50.0)
            //	x+=1;
            //}while(x<1);
            quanThe.add(caThe1);
            quanThe.add(caThe2);
        }

    }

    private void dotBien(double pDB) {

        //double a[] = new double[999];	
        Random rd = new Random();
        //ArrayList<Double> temp = new ArrayList<Double>();
        //ArrayList<CaThe> caTheDuocChon = new ArrayList<CaThe>();

        double F = 0;
        for (CaThe caThe : quanThe) {
            for (int x = 2; x <= 8; x++) {
                //F += caThe.hamThichNghi();
                //T.add(F);
                for (int y = 0; y < this.soNhanVien; y++) {
                    double pi = rd.nextDouble();
                    if (pi < pDB) {
                        do {
                            caThe.lichDiLam[y][x] = rd.nextInt(4);
                        } while (caThe.khongTrungCaHoc(y, x) == 0);
                    }
                }
            }
        }
    }

    public void print() {
        for (CaThe x : quanThe) {
            //double i=x.hamThichNghi();
            System.out.println(x + "" + x.hamThichNghi() + "\n");
        }
    }

    public ArrayList<CaThe> run(double gioihan) {
        int count;
        do {
            this.quanThe = this.chonLoc(5);
            //System.out.println("Sau chon loc");
            //Collections.sort(this.quanThe);
            //this.print();
            count = 0;
            for (CaThe x : this.quanThe) {
                if (x.hamThichNghi() >= gioihan) {
                    count += 1;
                }
            }
            if (count > 1) {
                break;
            }

            //System.out.println("Sau lai ghep");
            this.laiGhep(0.6);
            //Collections.sort(this.quanThe);
            //this.print();
            count = 0;
            for (CaThe x : this.quanThe) {
                if (x.hamThichNghi() >= gioihan) {
                    count += 1;
                }
            }
            if (count > 1) {
                break;
            }

            //System.out.println("Sau dot bien");
            this.dotBien(0.1);
            //Collections.sort(this.quanThe);
            //this.print();
            count = 0;
            for (CaThe x : this.quanThe) {
                if (x.hamThichNghi() >= gioihan) {
                    count += 1;
                }
            }
        } while (count < 1);
        Collections.sort(this.quanThe);
        return quanThe;
    }
}
