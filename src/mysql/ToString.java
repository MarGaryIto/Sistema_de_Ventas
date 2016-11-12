/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

/**
 *
 * @author PC-12
 */
public class ToString {
    public byte stringToByte(String num){
        byte resultado = 0;
        if(!"".equals(num)){
            if(num.length()==1){
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    if(num.charAt(0)>127){
                        resultado=127;
                    }else{
                        resultado=(byte) num.charAt(0);
                    }
                }else{
                    resultado=(byte) Integer.parseInt(num);
                }
            }else{
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    resultado = (byte) num.length();
                }else{
                    for(int x=0;x<num.length();x++){
                        if(num.charAt(x)==46){
                            String nuevoNum="";
                            for(int y=0;x>y;y++){
                                num = num.substring(0, x);
                            }
                        }
                    }
                    resultado = (byte) Integer.parseInt(num);
                }
            }
        }
        return resultado;
    }
    public short stringToShort(String num){
        short resultado = 0;
        if(!"".equals(num)){
            if(num.length()==1){
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    if(num.charAt(0)>32767){
                        resultado=32767;
                    }else{
                        resultado=(short) num.charAt(0);
                    }
                }else{
                    resultado=(short) Integer.parseInt(num);
                }
            }else{
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    resultado = (short) num.length();
                }else{
                    for(int x=0;x<num.length();x++){
                        if(num.charAt(x)==46){
                            String nuevoNum="";
                            for(int y=0;x>y;y++){
                                num = num.substring(0, x);
                            }
                        }
                    }
                    resultado = (byte) Integer.parseInt(num);
                }
            }
        }
        return resultado;
    }
    public int stringToInt(String num){
        int resultado = 0;
        if(!"".equals(num)){
            if(num.length()==1){
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    if(num.charAt(0)>100000000){
                        resultado=100000000;
                    }else{
                        resultado=(int) num.charAt(0);
                    }
                }else{
                    resultado=(int) Integer.parseInt(num);
                }
            }else{
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    resultado = (int) num.length();
                }else{
                    for(int x=0;x<num.length();x++){
                        if(num.charAt(x)==46){
                            String nuevoNum="";
                            for(int y=0;x>y;y++){
                                num = num.substring(0, x);
                            }
                        }
                    }
                    resultado = (int) Integer.parseInt(num);
                }
            }
        }
        return resultado;  
    }
    public long stringToLong(String num){
        long resultado = 0;
        if(!"".equals(num)){
            if(num.length()==1){
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    if(num.charAt(0)>100000000){
                        resultado=100000000;
                    }else{
                        resultado=(long) num.charAt(0);
                    }
                }else{
                    resultado=(long) Integer.parseInt(num);
                }
            }else{
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    resultado = (long) num.length();
                }else{
                    for(int x=0;x<num.length();x++){
                        if(num.charAt(x)==46){
                            String nuevoNum="";
                            for(int y=0;x>y;y++){
                                num = num.substring(0, x);
                            }
                        }
                    }
                    resultado = (long) Integer.parseInt(num);
                }
            }
        }
        return resultado;
    }
    public float stringToFloat(String num){
        float resultado = 0;
        if(!"".equals(num)){
            if(num.length()==1){
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    if(num.charAt(0)>100000000){
                        resultado=100000000;
                    }else{
                        resultado=(float) num.charAt(0);
                    }
                }else{
                    resultado=Float.parseFloat(num);
                }
            }else{
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    resultado = (float) num.length();
                }else{
                    for(int x=0;x<num.length();x++){
                        if(num.charAt(x)==46){
                            String nuevoNum="";
                            for(int y=0;x>y;y++){
                                num = num.substring(0, x);
                            }
                        }
                    }
                    resultado = Float.parseFloat(num);
                }
            }
        }
        return resultado;
    }
    public double stringToDouble(String num){
        double resultado = 0;
        if(!"".equals(num)){
            if(num.length()==1){
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    if(num.charAt(0)>100000000){
                        resultado=100000000;
                    }else{
                        resultado=(double) num.charAt(0);
                    }
                }else{
                    resultado=Double.parseDouble(num);
                }
            }else{
                if(num.charAt(0) < 48 || num.charAt(0)>57){
                    resultado = (double) num.length();
                }else{
                    for(int x=0;x<num.length();x++){
                        if(num.charAt(x)==46){
                            String nuevoNum="";
                            for(int y=0;x>y;y++){
                                num = num.substring(0, x);
                            }
                        }
                    }
                    resultado = Double.parseDouble(num);
                }
            }
        }
        return resultado;
    }
}
