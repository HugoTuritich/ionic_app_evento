package com.google.mlkit.vision.QReveIonic.java;

public class Acre_Inv
{
    public static String acre_inv;
    public static String cod_barra;
    public static int invitado_id;
    public static String nom_inv;
    public static int acreditado_id;
    public static String nom_acre;
    public static String des_ven;
    public static String autorizado;
    public static int estado;


    public  String getAcre_inv() {
        String retorna = "";
        if (this.acre_inv.equals("I"))
            retorna = "ACOMPAÑANTE";
        else {
            retorna = "INVITADO";
        }
        return retorna;
    }

    public static void setAcre_inv(String acre_invi) {
        Acre_Inv.acre_inv = acre_invi;
    }

    public static String getCod_barra() {
        return cod_barra;
    }

    public static void setCod_barra(String cod_barra) {
        Acre_Inv.cod_barra = cod_barra;
    }

    public static int getInvitado_id() {
        return invitado_id;
    }

    public static void setInvitado_id(int invitado_id) {
        Acre_Inv.invitado_id = invitado_id;
    }

    public static String getNom_inv() {
        return nom_inv;
    }

    public static void setNom_inv(String nom_inv) {
        Acre_Inv.nom_inv = nom_inv;
    }

    public static int getAcreditado_id() {
        return acreditado_id;
    }

    public static void setAcreditado_id(int acreditado_id) {
        Acre_Inv.acreditado_id = acreditado_id;
    }

    public static String getNom_acre() {
        return nom_acre;
    }

    public static void setNom_acre(String nom_acre) {
        Acre_Inv.nom_acre = nom_acre;
    }

    public static String getDes_ven() {
        return des_ven;
    }

    public static void setDes_ven(String des_ven) {
        Acre_Inv.des_ven = des_ven;
    }

    public static String getAutorizado() {
        return autorizado;
    }

    public static void setAutorizado(String autorizado) {
        Acre_Inv.autorizado = autorizado;
    }

    public static int getEstado() {
        return estado;
    }

    public static void setEstado(int estado) {
        Acre_Inv.estado = estado;
    }
}
