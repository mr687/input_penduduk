package com.penduduk.penduduk;

import java.util.ArrayList;
import java.util.List;

public class Penduduk {
    public String id_ruta;
    public String b1_k1a;
    public int b1_k1b;
    public String b1_k2a;
    public int b1_k2b;
    public String b1_k3a;
    public int b1_k3b;
    public String b1_k4a;
    public int b1_k4b;
    public String b1_k5;
    public String b1_k6;
    public String b1_k8;
    public int b1_k9;
    public int b1_k10;
    public int b3_k1a;
    public int b3_k1b;
    public String b3_k2;
    public int b3_k3;
    public int b3_k4a;
    public int b3_k4b;
    public int b3_k5a;
    public int b3_k5b;
    public int b3_k6;
    public int b3_k7;
    public int b3_k8;
    public int b3_k9a;
    public int b3_k9b;
    public int b3_k10;
    public int b3_k11a;
    public int b3_k11b;
    public int b3_k12;
    public int b5_k1a;
    public int b5_k1b;
    public int b5_k1c;
    public int b5_k1d;
    public int b5_k1e;
    public int b5_k1f;
    public int b5_k1g;
    public int b5_k1h;
    public int b5_k1i;
    public int b5_k1j;
    public int b5_k1k;
    public int b5_k1l;
    public int b5_k1m;
    public int b5_k1n;
    public int b5_k1o;
    public int b5_k2a1;
    public int b5_k2a2;
    public int b5_k2b;
    public int b5_k3a;
    public int b5_k3b;
    public int b5_k3c;
    public int b5_k3d;
    public int b5_k3e;
    public int b5_k4a;
    public int b5_k5a;
    public int b5_k5b;
    public int b5_k5c;
    public int b5_k5d;
    public int b5_k5e;
    public int b5_k5f;
    public int b5_k5g;
    public int b5_k5h;
    public int b5_k5i;
    public List<DataART> artData;

    public Penduduk(String id_ruta, String b1_k1a, int b1_k1b, String b1_k2a, int b1_k2b, String b1_k3a, int b1_k3b, String b1_k4a, int b1_k4b, String b1_k5, String b1_k6, String b1_k8, int b1_k9, int b1_k10, int b3_k1a, int b3_k1b, String b3_k2, int b3_k3, int b3_k4a, int b3_k4b, int b3_k5a, int b3_k5b, int b3_k6, int b3_k7, int b3_k8, int b3_k9a, int b3_k9b, int b3_k10, int b3_k11a, int b3_k11b, int b3_k12, int b5_k1a, int b5_k1b, int b5_k1c, int b5_k1d, int b5_k1e, int b5_k1f, int b5_k1g, int b5_k1h, int b5_k1i, int b5_k1j, int b5_k1k, int b5_k1l, int b5_k1m, int b5_k1n, int b5_k1o, int b5_k2a1, int b5_k2a2, int b5_k2b, int b5_k3a, int b5_k3b, int b5_k3c, int b5_k3d, int b5_k3e, int b5_k4a, int b5_k5a, int b5_k5b, int b5_k5c, int b5_k5d, int b5_k5e, int b5_k5f, int b5_k5g, int b5_k5h, int b5_k5i,List<DataART> artData) {
        this.id_ruta = id_ruta;
        this.b1_k1a = b1_k1a;
        this.b1_k1b = b1_k1b;
        this.b1_k2a = b1_k2a;
        this.b1_k2b = b1_k2b;
        this.b1_k3a = b1_k3a;
        this.b1_k3b = b1_k3b;
        this.b1_k4a = b1_k4a;
        this.b1_k4b = b1_k4b;
        this.b1_k5 = b1_k5;
        this.b1_k6 = b1_k6;
        this.b1_k8 = b1_k8;
        this.b1_k9 = b1_k9;
        this.b1_k10 = b1_k10;
        this.b3_k1a = b3_k1a;
        this.b3_k1b = b3_k1b;
        this.b3_k2 = b3_k2;
        this.b3_k3 = b3_k3;
        this.b3_k4a = b3_k4a;
        this.b3_k4b = b3_k4b;
        this.b3_k5a = b3_k5a;
        this.b3_k5b = b3_k5b;
        this.b3_k6 = b3_k6;
        this.b3_k7 = b3_k7;
        this.b3_k8 = b3_k8;
        this.b3_k9a = b3_k9a;
        this.b3_k9b = b3_k9b;
        this.b3_k10 = b3_k10;
        this.b3_k11a = b3_k11a;
        this.b3_k11b = b3_k11b;
        this.b3_k12 = b3_k12;
        this.b5_k1a = b5_k1a;
        this.b5_k1b = b5_k1b;
        this.b5_k1c = b5_k1c;
        this.b5_k1d = b5_k1d;
        this.b5_k1e = b5_k1e;
        this.b5_k1f = b5_k1f;
        this.b5_k1g = b5_k1g;
        this.b5_k1h = b5_k1h;
        this.b5_k1i = b5_k1i;
        this.b5_k1j = b5_k1j;
        this.b5_k1k = b5_k1k;
        this.b5_k1l = b5_k1l;
        this.b5_k1m = b5_k1m;
        this.b5_k1n = b5_k1n;
        this.b5_k1o = b5_k1o;
        this.b5_k2a1 = b5_k2a1;
        this.b5_k2a2 = b5_k2a2;
        this.b5_k2b = b5_k2b;
        this.b5_k3a = b5_k3a;
        this.b5_k3b = b5_k3b;
        this.b5_k3c = b5_k3c;
        this.b5_k3d = b5_k3d;
        this.b5_k3e = b5_k3e;
        this.b5_k4a = b5_k4a;
        this.b5_k5a = b5_k5a;
        this.b5_k5b = b5_k5b;
        this.b5_k5c = b5_k5c;
        this.b5_k5d = b5_k5d;
        this.b5_k5e = b5_k5e;
        this.b5_k5f = b5_k5f;
        this.b5_k5g = b5_k5g;
        this.b5_k5h = b5_k5h;
        this.b5_k5i = b5_k5i;
        this.artData = artData;
    }
}