package com.juaracoding.fantastic4_rest_api.utils;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataGenerator {
        private Faker faker = new Faker(new Locale("in_ID","ID"));
        private boolean isValid = false;
        private Matcher matcher = null;
        private int intLoop = 0;

    public String dataEmail() {
        isValid = false;
        intLoop = 0;
        String email = "";
        while(!isValid){
            try{
                email = faker.internet().emailAddress();
                matcher = Pattern.compile("^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$").matcher(email);
                isValid = matcher.find();
                if(intLoop==250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Email SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            }catch (Exception e){
                isValid = false;
            }
        }
        return email;
    }

    public String dataNoTelp() {
        isValid = false;
        intLoop = 0;
        String noTelp = "";
        while(!isValid){
            try{
                noTelp = faker.phoneNumber().phoneNumber();
                matcher = Pattern.compile("^(\\+62)8[0-9]{9,13}$").matcher(noTelp);
                isValid = matcher.find();
                if(intLoop==250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA No Telp SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            }catch (Exception e){
                isValid = false;
            }
        }
        return noTelp;
    }

    public String dataNamaDepan() {
        isValid = false;
        intLoop = 0;
        String namaDepan = "";
        while(!isValid){
            try{
                namaDepan = faker.name().firstName();
                matcher = Pattern.compile("^[a-zA-Z\\s?]{4,50}$").matcher(namaDepan);
                isValid = matcher.find();
                if(intLoop==250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama Depan SEBANYAK 15 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            }catch (Exception e){
                isValid = false;
            }
        }
        return namaDepan;
    }

    public String dataNamaTengah() {
        isValid = false;
        intLoop = 0;
        String namaTengah = "";
        while(!isValid){
            try{
                namaTengah = faker.name().nameWithMiddle().split(" ")[1];
                matcher = Pattern.compile("^[a-zA-Z\\s?]{4,50}$").matcher(namaTengah);
                isValid = matcher.find();
                if(intLoop==250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama Tengah SEBANYAK 15 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            }catch (Exception e){
                isValid = false;
            }
        }
        return namaTengah;
    }

    public String dataNamaBelakang() {
        isValid = false;
        intLoop = 0;
        String namaBelakang = "";
        while(!isValid){
            try{
                namaBelakang = faker.name().lastName();
                matcher = Pattern.compile("^[a-zA-Z\\s?]{4,50}$").matcher(namaBelakang);
                isValid = matcher.find();
                if(intLoop==250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama Belakang SEBANYAK 15 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            }catch (Exception e){
                isValid = false;
            }
        }
        return namaBelakang;
    }

    public String dataNamaLengkap() {
        isValid = false;
        intLoop = 0;
        String namaLengkap = "";
        while(!isValid){
            try{
                namaLengkap = faker.name().fullName();
                matcher = Pattern.compile("^[a-zA-Z\\s?]{4,50}$").matcher(namaLengkap);
                isValid = matcher.find();
                if(intLoop==250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama Lengkap SEBANYAK 15 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            }catch (Exception e){
                isValid = false;
            }
        }
        return namaLengkap;
    }


    public String dataPassword() {
        isValid = false;
        intLoop = 0;
        String password = "";
        while(!isValid){
            try{
                password = faker.internet().password(8,15,true,true,true);
                matcher = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w@#\\-\\$]{9,16}$").matcher(password);
                isValid = matcher.find();
                if(intLoop==200){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA PASSWORD SEBANYAK 200 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            }catch (Exception e){
                isValid = false;
            }
        }
        return password;
    }
    public String dataDepartement() {
        boolean isValid = false;
        int intLoop = 0;
        String departement = "";
        while (!isValid) {
            try {
                departement = faker.name().name();
                matcher = Pattern.compile("^[A-Za-z0-9 ]+$").matcher(departement);
                isValid = matcher.find();
                if (intLoop == 250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Departemen SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return departement;
    }

    public String dataJabatan() {
        boolean isValid = false;
        int intLoop = 0;
        String jabatan = "";
        while (!isValid) {
            try {
                jabatan = faker.name().name();
                matcher = Pattern.compile("^[A-Za-z0-9 ]+$").matcher(jabatan);
                isValid = matcher.find();
                if (intLoop == 250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Jabatan SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return jabatan;
    }

    public String dataNamaRuangan() {
        boolean isValid = false;
        int intLoop = 0;
        String namaRuangan = "";

        while (!isValid) {
            try {
                namaRuangan = faker.name().name();
                matcher = Pattern.compile("(\"^([a-zA-Z0-9\\\\s]{5,50})$\");").matcher(namaRuangan);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama Ruangan SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return namaRuangan;
    }


    public String dataLokasi() {
        boolean isValid = false;
        int intLoop = 0;
        String dataLokasi = "";
        while (!isValid) {
            try {
                dataLokasi = faker.name().name();
                matcher = Pattern.compile("^([a-zA-Z0-9\\s]{2,50})$").matcher(dataLokasi);
                isValid = matcher.find();
                if (intLoop == 250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA lokasi SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return dataLokasi;
    }

    public String dataNamaFasilitas() {
        boolean isValid = false;
        int intLoop = 0;
        String namaFasilitas = "";
        while (!isValid) {
            try {
                namaFasilitas = faker.name().name();
                // Validasi pola: 3-50 karakter, huruf/angka/spasi
                matcher = Pattern.compile("^[a-zA-Z\\s]{3,50}$").matcher(namaFasilitas);
                isValid = matcher.find();
                if (intLoop == 250){
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama Fasilitas SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return namaFasilitas;
    }
    public String dataKota() {
        isValid = false;
        intLoop = 0;
        String namaKota   = "";
        namaKota = faker.address().cityName();
        return namaKota;
    }

    public String dataNamaTim() {
        isValid = false;
        intLoop = 0;
        String namaKota   = "";
        namaKota = faker.team().name();
        return namaKota;
    }

}
