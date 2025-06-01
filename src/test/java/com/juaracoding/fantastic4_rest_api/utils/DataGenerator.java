package com.juaracoding.fantastic4_rest_api.utils;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataGenerator {
    private Faker faker = new Faker(new Locale("in_ID", "ID"));
    private boolean isValid = false;
    private Matcher matcher = null;
    private int intLoop = 0;
    private final Random rand = new Random();


    public String dataId() {
        isValid = false;
        intLoop = 0;
        String id = "";
        while (!isValid) {
            try {
                id = faker.bothify("??????????#####").replaceAll("[^A-Za-z0-9]", "");
                // Ensure length between 5 and 50
                if (id.length() < 5) {
                    id += faker.bothify("?????").replaceAll("[^A-Za-z0-9]", "");
                }
                if (id.length() > 50) {
                    id = id.substring(0, 50);
                }
                matcher = Pattern.compile("^([A-Za-z0-9]{5,50})$").matcher(id);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("Tried 250 times to generate ID and failed!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return id;
    }

    public String dataNama() {
        isValid = false;
        intLoop = 0;
        String nama = "";
        while (!isValid) {
            try {
                nama = faker.name().name();
                matcher = Pattern.compile("^[a-zA-Z\\s?]{4,50}$").matcher(nama);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return nama;
    }

    public String dataEmail() {
        isValid = false;
        intLoop = 0;
        String email = "";
        while (!isValid) {
            try {
                email = faker.internet().emailAddress();
                matcher = Pattern.compile("^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$").matcher(email);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Email SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return email;
    }

    public String dataNoTelp() {
        isValid = false;
        intLoop = 0;
        String noTelp = "";
        while (!isValid) {
            try {
                // Generate a valid Indonesian phone number
                noTelp = "+628" + faker.number().numberBetween(10000000L, 999999999999L);
                matcher = Pattern.compile("^\\+628[1-9][0-9]{7,12}$").matcher(noTelp);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA No Telp SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return noTelp;
    }

    public String dataPassword() {
        isValid = false;
        intLoop = 0;
        String password = "";
        while (!isValid) {
            try {
                password = faker.internet().password(8, 15, true, true, true);
                matcher = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w@#\\-\\$]{9,16}$").matcher(password);
                isValid = matcher.find();
                if (intLoop == 200) {
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA PASSWORD SEBANYAK 200 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return password;
    }

    public String dataDepartement() {
        isValid = false;
        intLoop = 0;
        String departement = "";
        while (!isValid) {
            try {
                departement = faker.company().profession().replaceAll("[^A-Za-z0-9 ]", "");
                if (departement.length() < 3) {
                    departement += " IT";
                }
                matcher = Pattern.compile("^[A-Za-z0-9 ]{3,100}$").matcher(departement);
                isValid = matcher.find();
                if (intLoop == 250) {
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
        isValid = false;
        intLoop = 0;
        String jabatan = "";
        while (!isValid) {
            try {
                jabatan = faker.job().title().replaceAll("[^A-Za-z0-9 ]", "");
                if (jabatan.length() < 3) {
                    jabatan += " IT";
                }
                matcher = Pattern.compile("^[A-Za-z0-9 ]{3,100}$").matcher(jabatan);
                isValid = matcher.find();
                if (intLoop == 250) {
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

    public String dataIdRuangan() {
        isValid = false;
        intLoop = 0;
        String idRuangan = "";
        while (!isValid) {
            try {
                // Prefix with R or RM, then random uppercase letters and digits
                String prefix = faker.bool().bool() ? "R" : "RM";
                String randomPart = faker.bothify("##########").replaceAll("[^0-9]", "");
                idRuangan = prefix + randomPart;
                // Ensure length between 5 and 50
                if (idRuangan.length() < 5) {
                    idRuangan += faker.bothify("########").replaceAll("[^0-9]", "");
                }
                if (idRuangan.length() > 50) {
                    idRuangan = idRuangan.substring(0, 50);
                }
                matcher = Pattern.compile("^([A-Z0-9]{5,50})$").matcher(idRuangan);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("Tried 250 times to generate dataIdRuangan and failed!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return idRuangan;
    }

    public String dataNamaRuangan() {
        boolean isValid = false;
        int intLoop = 0;
        String namaRuangan = "";

        while (!isValid) {
            try {
                String[] types = {"Meeting", "Diskusi", "Presentasi", "Kelas", "Workshop"};
                String type = types[faker.random().nextInt(types.length)];
                int number = faker.number().numberBetween(1, 100);
                namaRuangan = "Ruang " + type + " " + number;
                matcher = Pattern.compile("^([a-zA-Z0-9\\s]{5,50})$").matcher(namaRuangan);
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
                dataLokasi = "Lantai ".concat(faker.random().nextInt(1, 20).toString());
                matcher = Pattern.compile("^([a-zA-Z0-9\\s]{2,50})$").matcher(dataLokasi);
                isValid = matcher.find();
                if (intLoop == 250) {
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

    public short[] dataKapasitas() {
        boolean isValid = false;
        int intLoop = 0;
        short minKapasitas = 1;
        short maxKapasitas = 9999;

        while (!isValid) {
            try {
                minKapasitas = (short) faker.number().numberBetween(1, 9999);
                maxKapasitas = (short) faker.number().numberBetween(minKapasitas, 10000); // max >= min
                isValid = minKapasitas >= 1 && maxKapasitas >= minKapasitas && maxKapasitas <= 9999;

                if (intLoop == 250) {
                    System.out.println("Tried 250 times to generate kapasitas and failed!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return new short[]{minKapasitas, maxKapasitas};
    }

    public String dataIdFasilitas() {
        boolean isValid = false;
        int intLoop = 0;
        String idFasilitas = "";
        while (!isValid) {
            try {
                idFasilitas = "FS" + faker.bothify("##########").replaceAll("[^A-Za-z0-9]", "");
                // Ensure length between 5 and 50
                if (idFasilitas.length() < 5) {
                    idFasilitas += faker.bothify("?????").replaceAll("[^A-Za-z0-9]", "");
                }
                if (idFasilitas.length() > 50) {
                    idFasilitas = idFasilitas.substring(0, 50);
                }
                matcher = Pattern.compile("^([A-Za-z0-9]{5,50})$").matcher(idFasilitas);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("Tried 250 times to generate dataIdFasilitas and failed!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return idFasilitas;
    }

    public String dataNamaFasilitas() {
        boolean isValid = false;
        int intLoop = 0;
        String namaFasilitas = "";
        while (!isValid) {
            try {
                String[] fasilitasList = {"LCD", "Papan Tulis", "AC", "Speaker", "Microphone", "WiFi", "Kursi", "Meja"};
                namaFasilitas = fasilitasList[faker.random().nextInt(fasilitasList.length)];
                // Validasi pola: 3-50 karakter, huruf/angka/spasi
                matcher = Pattern.compile("^[a-zA-Z\\s]{3,50}$").matcher(namaFasilitas);
                isValid = matcher.find();
                if (intLoop == 250) {
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

    public Short dataJumlahFasilitas() {
        isValid = false;
        intLoop = 0;
        short jumlahFasilitas = 0;

        while (!isValid) {
            try {
                jumlahFasilitas = (short) faker.number().numberBetween(1, 100);
                isValid = jumlahFasilitas >= 1 && jumlahFasilitas <= 100;

                if (intLoop == 250) {
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Jumlah Fasilitas SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return jumlahFasilitas;
    }

    public long dataIdPesan() {
        int intLoop = 0;
        while (intLoop < 250) {
            long idPesan = faker.number().numberBetween(1L, 99999L);
            if (idPesan > 0) {
                return idPesan;
            }
            intLoop++;
        }
        throw new RuntimeException("Gagal membuat ID Pesan setelah 250 percobaan.");
    }

    public String dataNamaPertemuan() {
        boolean isValid = false;
        int intLoop = 0;
        String namaPertemuan = "";
        while (!isValid) {
            try {
                namaPertemuan = "Pertemuan".concat(faker.programmingLanguage().name());
                // Validasi pola: 3-50 karakter, huruf/angka/spasi
                matcher = Pattern.compile("^[A-Za-z0-9\\s]{3,100}$").matcher(namaPertemuan);
                isValid = matcher.find();
                if (intLoop == 250) {
                    System.out.println("SUDAH MENCOBA MEMBUAT DATA Nama Pertemuan SEBANYAK 250 KALI DAN GAGAL !!");
                    System.exit(1);
                }
                intLoop++;
            } catch (Exception e) {
                isValid = false;
            }
        }
        return namaPertemuan;
    }

    public String dataTanggalPemesanan1() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    public String dataTanggalPemesanan() {
        isValid = false;
        int intLoop = 0;
        String tanggalPemesanan = "";
        while (!isValid) {
            tanggalPemesanan = new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday());
            Matcher matcher = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(tanggalPemesanan);

            isValid = matcher.find();
            if (matcher.find()) {
                System.out.println("SUDAH MENCOBA MEMBUAT DATA TANGGAL PEMESANAN SEBANYAK 250 KALI DAN GAGAL !!");
                System.exit(1);
            }
            intLoop++;
        }
        return tanggalPemesanan;
    }

    public String dataTanggalPertemuan1() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    public String dataTanggalPertemuan2() {
        LocalDate now = LocalDate.now();
        LocalDate future = now.plusDays(rand.nextInt(15)); // 0–14 hari ke depan
        return future.toString();
    }

    public String dataTanggalPertemuan() {
        isValid = false;
        int intLoop = 0;
        String tanggalPertemuan = "";
        while (!isValid) {
            tanggalPertemuan = new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday());
            Matcher matcher = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(tanggalPertemuan);

            isValid = matcher.find();
            if (matcher.find()) {
                System.out.println("SUDAH MENCOBA MEMBUAT DATA TANGGAL PERTEMUAN SEBANYAK 250 KALI DAN GAGAL !!");
                System.exit(1);
            }
            intLoop++;
        }
        return tanggalPertemuan;
    }


    public String dataMulai() {
        isValid = false;
        int intLoop = 0;
        String mulai = null;
        while (!isValid) {
            int h = rand.nextInt(8);
            int m = rand.nextInt(60);
            int s = rand.nextInt(60);
            mulai = String.format("%02d:%02d:%02d", h, m, s);
            Matcher matcher = Pattern.compile("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$").matcher(mulai);

            isValid = matcher.find();
            if (matcher.find()) {
                System.out.println("SUDAH MENCOBA MEMBUAT DATA MULAI SEBANYAK 250 KALI DAN GAGAL !!");
                System.exit(1);
            }
            intLoop++;
        }
        return mulai;
    }

    public String dataBerakhir(String mulai) {
        LocalTime start = LocalTime.parse(mulai);
        LocalTime end = start.plusMinutes(30 + rand.nextInt(210)); // Tambah 30–240 menit
        return end.toString();
    }

    public String dataBerakhir() {
        isValid = false;
        int intLoop = 0;
        String berakhir = null;
        while (!isValid) {
            int h = rand.nextInt(8);
            int m = rand.nextInt(60);
            int s = rand.nextInt(60);
            berakhir = String.format("%02d:%02d:%02d", h, m, s);
            Matcher matcher = Pattern.compile("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$").matcher(berakhir);

            isValid = matcher.find();
            if (matcher.find()) {
                System.out.println("SUDAH MENCOBA MEMBUAT DATA BERAKHIR SEBANYAK 250 KALI DAN GAGAL !!");
                System.exit(1);
            }
            intLoop++;
        }
        return berakhir;
    }

    public String dataDurasi() {
        isValid = false;
        intLoop = 0;
        while (!isValid) {
            int index = rand.nextInt(8);
            BigDecimal durasi = BigDecimal.valueOf(0.5 + index * 0.5).setScale(1, RoundingMode.HALF_UP);
            Matcher matcher = Pattern.compile("^(0\\.5|1(\\.0)?|1\\.5|2(\\.0)?|2\\.5|3(\\.0)?|3\\.5|4(\\.0)?)$").matcher(durasi.toString());

            isValid = matcher.find();
            if (matcher.find()) {
                return durasi.toString();
            }
            intLoop++;
        }
        throw new RuntimeException("SUDAH MENCOBA MEMBUAT DATA DURASI SEBANYAK 250 KALI DAN GAGAL !!");
    }

    public String dataStatus() {
        isValid = false;
        intLoop = 0;
        while (!isValid) {
            String[] options = {"pending", "approved", "cancelled"};
            String status = options[faker.random().nextInt(options.length)];
            Matcher matcher = Pattern.compile("^(pending|approved|cancelled)$").matcher(status);

            isValid = matcher.find();
            if (matcher.find()) {
                return status;
            }
            intLoop++;
        }
        throw new RuntimeException("SUDAH MENCOBA MEMBUAT STATUS SEBANYAK 250 KALI DAN GAGAL !!");
    }

    public String dataNamaTim() {
        isValid = false;
        intLoop = 0;
        String namaTim = "";
        namaTim = faker.team().name();
        return namaTim;
    }
}