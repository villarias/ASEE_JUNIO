package com.example.asee_project.database;

import com.example.asee_project.R;
import com.example.asee_project.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class Dataset_Ciudades {

    public List<Ciudad> getDataSet() {
        List<Ciudad> lista = new ArrayList<Ciudad>() {{
            add(new Ciudad("BER","Berlin", "0",R.drawable.berlin));
            add(new Ciudad("DTM","Dortmund","0",R.drawable.dortmund));
            add(new Ciudad("FRA","Frankfurt","0",R.drawable.frankfurt));
            add(new Ciudad("HAM","Hamburgo","0",R.drawable.hamburgo));
            add(new Ciudad("MUC","Munich","0",R.drawable.munich));
            add(new Ciudad("VIE","Viena","0",R.drawable.viena));
            add(new Ciudad("ALC","Alicante","0",R.drawable.alicante));
            add(new Ciudad("LEI","Almeria","0",R.drawable.almeria));
            add(new Ciudad("OVD","Asturias","0",R.drawable.asturias));
            add(new Ciudad("BCN","Barcelona","0",R.drawable.barcelona));
            add(new Ciudad("BIO","Bilbao","0",R.drawable.bilbao));
            add(new Ciudad("FUE","Fuerteventura","0",R.drawable.fuerteventura));
            add(new Ciudad("GRO","Girona","0",R.drawable.girona));
            add(new Ciudad("LPA","Gran Canaria","0",R.drawable.grancanaria));
            add(new Ciudad("GRX","Granada","0",R.drawable.granada));
            add(new Ciudad("IBZ","Ibiza","0",R.drawable.ibiza));
            add(new Ciudad("ACE","Lanzarote","0",R.drawable.lanzarote));
            add(new Ciudad("MAD","Madrid","0",R.drawable.madrid));
            add(new Ciudad("AGP","Malaga","0",R.drawable.malaga));
            add(new Ciudad("MAH","Menorca","0",R.drawable.menorca));
            add(new Ciudad("PMI","Mallorca","0",R.drawable.menorca));
            add(new Ciudad("RMU","Murcia","0",R.drawable.murcia));
            add(new Ciudad("SVQ","Sevilla","0",R.drawable.sevilla));
            add(new Ciudad("TFN","Tenerife","0",R.drawable.tenerife));
            add(new Ciudad("VLC","Valencia","0",R.drawable.valencia));
            add(new Ciudad("BOD","Burdeos","0",R.drawable.burdeos));
            add(new Ciudad("BVA","Paris","0",R.drawable.paris));
            add(new Ciudad("BUD","Budapest","0",R.drawable.budapest));
            add(new Ciudad("DUB","Dublin","0",R.drawable.dublin));
            add(new Ciudad("FLR","Florencia","0",R.drawable.florencia));
            add(new Ciudad("MXP","Milan","0",R.drawable.milan));
            add(new Ciudad("NAP","Napoles","0",R.drawable.napoles));
            add(new Ciudad("CIA","Roma","0",R.drawable.roma));
            add(new Ciudad("TRN","Turin","0",R.drawable.turin));
            add(new Ciudad("VCE","Venecia","0",R.drawable.venecia));
            add(new Ciudad("AMS","Amsterdam","0",R.drawable.amsterdam));
            add(new Ciudad("FAO","Faro","0",R.drawable.faro));
            add(new Ciudad("LIS","Lisboa","0",R.drawable.lisboa));
            add(new Ciudad("EDI","Edimburgo","0",R.drawable.edimburgo));
            add(new Ciudad("LPL","Liverpool","0",R.drawable.liverpool));
            add(new Ciudad("LCY","Londres","0",R.drawable.londres));
            add(new Ciudad("MAN","Manchester","0",R.drawable.manchester));
            add(new Ciudad("DME","Moscu","0",R.drawable.moscu));
            add(new Ciudad("ARN","Estocolmo","0",R.drawable.estoclomo));
            add(new Ciudad("IST","Estambul","0",R.drawable.estambul));
        }};
        return lista;
    }
}
