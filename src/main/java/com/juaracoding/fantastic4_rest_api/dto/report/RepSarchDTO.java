package com.juaracoding.fantastic4_rest_api.dto.report;

public class RepSarchDTO {

    private String roomId;
    private String namaRoom;
    private String tanggal;
    private String schedule;

    public RepSarchDTO(String roomId, String namaRoom, String tanggal, String schedule) {
        this.roomId = roomId;
        this.namaRoom = namaRoom;
        this.tanggal = tanggal;
        this.schedule = schedule;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getNamaRoom() {
        return namaRoom;
    }

    public void setNamaRoom(String namaRoom) {
        this.namaRoom = namaRoom;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
