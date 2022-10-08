/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author HP
 */
public class JavaApplication2 {
    public static String[] veriokuma() throws FileNotFoundException{
        int kisisay=0;
        File dosyaAdi=new File("C:\\Users\\HP\\Desktop\\asiListesi.txt");
        Scanner okuyucu=new Scanner(dosyaAdi);
        Scanner oku=new Scanner(dosyaAdi);
        String okunan=null;
        while(okuyucu.hasNextLine()){
            okunan=okuyucu.nextLine();
            //System.out.println(okunan);
            kisisay++;
        }
        //System.out.println(kisisay);//KİSİ SAYISINI VERİR
        String[] dizi=new String[kisisay];
        int i=0;
        while(oku.hasNextLine()){
            okunan=oku.nextLine();
            dizi[i]=okunan;
            i++;
        }
        return dizi;
    }
    public static void veriyazma(String[] dizi) throws IOException{
        try{
            File dosyaAdi=new File("C:\\Users\\HP\\Desktop\\asiListesi.txt");
            dosyaAdi.createNewFile();
            FileWriter yazici=new FileWriter(dosyaAdi,false);
            for (int i = 0; i < dizi.length; i++) {
                yazici.write(String.valueOf(dizi[i])+"\n");
            }
            yazici.close();
        }
        catch(IOException e){
            System.out.println("Yazma isleminde bir hata olustu");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        int sayac=veriokuma().length;
        while(true){
            String[] ilk=veriokuma();
            int[] num=new int[veriokuma().length+1];
            String[] dizi=new String[ilk.length+1];
            for (int i = 0; i < ilk.length; i++) {
                dizi[i]=ilk[i];
            }
            System.out.print("          MENÜ            \n___________________________\nYeni Kayıt İçin              1:\nRandevu Almak İçin           2:\nRandevuları Listelemek İçin  3:\nÇıkış için                   0:\n\nLÜTFEN SEÇİMİNİZİ GİRİNİZ     :");
            Scanner oku=new Scanner(System.in);
            int secim=oku.nextInt();
            if(secim==0){//ÇIKIŞ
                System.out.println("PROGRAM SONLANMISTIR !!!");
                break;
            }
            if(secim==1){//YENİ KAYIT
                System.out.print("Öğrenci No: ");
                String gecicino=oku.next();
                System.out.print("Ad ve Soyadı: ");
                String geciciad=oku.next();
                String gecici="";
                gecici+=gecicino+","+geciciad;
                dizi[sayac]=gecici;
                gecici="";
                sayac++;
                for (int i = 0; i < dizi.length; i++) {
                    String tut="";
                    for (int j = 0; j < dizi[i].length(); j++) {//NUMARALARI AYRI DİZİYE ALDIK
                        if(dizi[i].charAt(j)==','){
                            num[i]=Integer.parseInt(tut);
                            break;
                        }
                        else{
                            tut+=dizi[i].charAt(j);
                        }
                    }
                }
                for (int i = 0; i < num.length; i++) {//SIRALAMAYI BURADA YAPTIK
                    int enk=i;
                    for (int j = i+1; j < num.length; j++) {
                        if(num[j]<num[enk]){
                            enk=j;
                        }
                    }
                    int a=num[i];
                    String b=dizi[i];
                    num[i]=num[enk];
                    dizi[i]=dizi[enk];
                    num[enk]=a;
                    dizi[enk]=b;
                }
                veriyazma(dizi);//SIRALADIĞIMIZ VERİYİ TXT DOSYASINA YAZDIK
                System.out.println("KAYDINIZ BASARILI BİR SEKİLDE YAPILMISTIR...");
            }
            if(secim==2){
                for (int i = 0; i < dizi.length-1; i++) {
                    String tut="";
                    for (int j = 0; j < dizi[i].length(); j++) {//NUMARALARI AYRI DİZİYE ALDIK
                        if(dizi[i].charAt(j)==','){
                            num[i]=Integer.parseInt(tut);
                            break;
                        }
                        else{
                            tut+=dizi[i].charAt(j);
                        }
                    }
                }
                System.out.print("Öğrenci No: ");
                int no=oku.nextInt();
                int sıra=0;
                for (int i = 0; i < num.length; i++) {
                    if(num[i]==no){
                        sıra=i;
                    }
                }
                System.out.println("\n"+dizi[sıra]+"\n\n");
                System.out.print("  AŞI TERCİHİ \n______________\nBiontech   : 1\nSinovac    : 2\nSputnik     :3\nTercihinizi giriniz: ");
                int tercih=oku.nextInt();
                System.out.println("Lütfen aşı tarihi giriniz: ");
                String tarih=oku.next();
                if(tercih==1){
                    dizi[sıra]+=",Biontech,";
                }
                if(tercih==2){
                    dizi[sıra]+=",Sinovac,";
                }
                if(tercih==3){
                    dizi[sıra]+=",Sputnik,";
                }
                dizi[sıra]+=tarih;
                veriyazma(dizi);
                System.out.println("\n RANDEVUNUZ OLUSTURULMUSTUR...");
            }
            if(secim==3){
                System.out.print("Sıra No  Öğrenci Numarası    Adı Soyadı     Aşı Firması           Tarih\n");
                for (int i = 0; i < dizi.length-1; i++) {
                    String klm="";
                    System.out.print(i+1+"         ");
                    for (int j = 0; j < dizi[i].length(); j++) {
                        if(dizi[i].charAt(j)==','){
                            System.out.print(klm+"          ");
                            klm="";
                            continue;
                        }
                        if(j==dizi[i].length()-1){
                            klm+=dizi[i].charAt(j);
                            System.out.print(klm);
                            klm="";
                        }
                        else{
                            klm+=dizi[i].charAt(j);
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
}
