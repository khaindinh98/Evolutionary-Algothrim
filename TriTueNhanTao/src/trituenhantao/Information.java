/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trituenhantao;

/**
 *
 * @author Sub4sa
 */
public class Information{

    public String fullName;
    public String birthDay;
    public String address;

    public Information(String fullName, String birthDay, String address){
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.address = address;
    }

    public String toString() {
        String temp = "" + fullName +"\n" + birthDay + "\n" +address+ "\n";
        return temp;
    }
}

